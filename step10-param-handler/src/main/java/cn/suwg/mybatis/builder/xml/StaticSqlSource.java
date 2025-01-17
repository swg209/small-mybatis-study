package cn.suwg.mybatis.builder.xml;

import cn.suwg.mybatis.mapping.BoundSql;
import cn.suwg.mybatis.mapping.ParameterMapping;
import cn.suwg.mybatis.mapping.SqlSource;
import cn.suwg.mybatis.session.Configuration;

import java.util.List;

/**
 * 静态SQL源码.
 *
 * @Author: suwg
 * @Date: 2025/1/16
 */
public class StaticSqlSource implements SqlSource {


    private String sql;

    private List<ParameterMapping> parameterMappings;

    private Configuration configuration;

    public StaticSqlSource(Configuration configuration, String sql) {
        this(configuration, sql, null);
    }

    public StaticSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }


    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration, sql, parameterMappings, parameterObject);
    }
}
