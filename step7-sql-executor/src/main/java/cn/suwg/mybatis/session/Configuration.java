package cn.suwg.mybatis.session;

import cn.suwg.mybatis.binding.MapperRegistry;
import cn.suwg.mybatis.datasource.druid.DruidDataSourceFactory;
import cn.suwg.mybatis.datasource.pooled.PooledDataSourceFactory;
import cn.suwg.mybatis.datasource.unpooled.UnpooledDataSourceFactory;
import cn.suwg.mybatis.mapping.Environment;
import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.transaction.jdbc.JdbcTransactionFactory;
import cn.suwg.mybatis.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 会话配置信息.
 *
 * @Author: suwg
 * @Date: 2024/12/12
 */
public class Configuration {

    /**
     * 映射注册机.
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry();

    /**
     * 映射的语句，存在Map.
     */
    protected final Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

    /**
     * 类型别名注册机.
     */
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    /**
     * 环境.
     */
    protected Environment environment;


    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatementMap.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatementMap.get(id);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
