package cn.suwg.mybatis.builder;

import cn.suwg.mybatis.builder.xml.ParameterExpression;
import cn.suwg.mybatis.builder.xml.StaticSqlSource;
import cn.suwg.mybatis.mapping.ParameterMapping;
import cn.suwg.mybatis.mapping.SqlSource;
import cn.suwg.mybatis.parsing.GenericTokenParser;
import cn.suwg.mybatis.parsing.TokenHandler;
import cn.suwg.mybatis.reflection.MetaObject;
import cn.suwg.mybatis.session.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SQL源码构造器.
 *
 * @Author: suwg
 * @Date: 2025/1/14
 */
public class SqlSourceBuilder extends BaseBuilder {
    private static final String parameterProperties = "javaType,jdbcType,mode,numericScale,resultMap,typeHandler,jdbcTypeName";

    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }

    public SqlSource parse(String originalSql, Class<?> parameterType, Map<String, Object> additionalParameters) {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler(configuration, parameterType, additionalParameters);
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String sql = parser.parse(originalSql);
        // 返回静态 SQL
        return new StaticSqlSource(configuration, sql, handler.getParameterMappings());
    }

    private static class ParameterMappingTokenHandler extends BaseBuilder implements TokenHandler {
        private List<ParameterMapping> parameterMappings = new ArrayList<>();
        private Class<?> parameterType;
        private MetaObject metaParameters;

        public ParameterMappingTokenHandler(Configuration configuration, Class<?> parameterType, Map<String, Object> additionalParameters) {
            super(configuration);
            this.parameterType = parameterType;
            this.metaParameters = configuration.newMetaObject(additionalParameters);
        }

        public List<ParameterMapping> getParameterMappings() {
            return parameterMappings;
        }

        @Override
        public String handleToken(String content) {
            parameterMappings.add(buildParameterMapping(content));
            return "?";
        }

        // 构建参数映射
        private ParameterMapping buildParameterMapping(String content) {
            // 先解析参数映射,就是转化成一个 HashMap | #{favouriteSection,jdbcType=VARCHAR}
            Map<String, String> propertiesMap = new ParameterExpression(content);
            String property = propertiesMap.get("property");
            Class<?> propertyType = parameterType;

            ParameterMapping.Builder builder = new ParameterMapping.Builder(configuration, property, propertyType);
            return builder.build();
        }
    }
}
