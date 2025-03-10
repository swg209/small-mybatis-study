package cn.suwg.mybatis.mapping;

import cn.suwg.mybatis.session.Configuration;
import cn.suwg.mybatis.type.JdbcType;

/**
 * 参数映射 #{property,javaType=int,jdbcType=NUMERIC}.
 *
 * @Author: suwg
 * @Date: 2024/12/13
 */
public class ParameterMapping {

    private Configuration configuration;

    // property
    private String property;

    // javaType = int
    private Class<?> javaType = Object.class;

    // jdbcType=NUMERIC
    private JdbcType jdbcType;


    private ParameterMapping() {
    }

    public static class Builder {
        private ParameterMapping parameterMapping = new ParameterMapping();

        public Builder(Configuration configuration, String property, Class<?> javaType) {
            parameterMapping.configuration = configuration;
            parameterMapping.property = property;
            parameterMapping.javaType = javaType;
        }

        public Builder javaType(Class<?> javaType) {
            parameterMapping.javaType = javaType;
            return this;
        }

        public Builder jdbcType(JdbcType jdbcType) {
            parameterMapping.jdbcType = jdbcType;
            return this;
        }

        public ParameterMapping build() {
            return parameterMapping;
        }
    }


    public Configuration getConfiguration() {
        return configuration;
    }

    public String getProperty() {
        return property;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }


}
