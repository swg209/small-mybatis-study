package cn.suwg.mybatis.scripting.defaults;

import cn.suwg.mybatis.builder.SqlSourceBuilder;
import cn.suwg.mybatis.mapping.BoundSql;
import cn.suwg.mybatis.mapping.SqlSource;
import cn.suwg.mybatis.scripting.xmltags.DynamicContext;
import cn.suwg.mybatis.scripting.xmltags.SqlNode;
import cn.suwg.mybatis.session.Configuration;

import java.util.HashMap;

/**
 * 原始SQL源码，比DynamicSqlSource动态SQL处理更快.
 *
 * @Author: suwg
 * @Date: 2025/1/14
 */
public class RawSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode, Class<?> parameterType) {
        this(configuration, getSql(configuration, rootSqlNode), parameterType);
    }

    public RawSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null ? Object.class : parameterType;
        sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
    }


    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }

    private static String getSql(Configuration configuration, SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }

}
