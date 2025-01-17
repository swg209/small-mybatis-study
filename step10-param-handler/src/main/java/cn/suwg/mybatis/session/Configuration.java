package cn.suwg.mybatis.session;

import cn.suwg.mybatis.binding.MapperRegistry;
import cn.suwg.mybatis.datasource.druid.DruidDataSourceFactory;
import cn.suwg.mybatis.datasource.pooled.PooledDataSourceFactory;
import cn.suwg.mybatis.datasource.unpooled.UnpooledDataSourceFactory;
import cn.suwg.mybatis.executor.Executor;
import cn.suwg.mybatis.executor.SimpleExecutor;
import cn.suwg.mybatis.executor.resultset.DefaultResultSetHandler;
import cn.suwg.mybatis.executor.resultset.ResultSetHandler;
import cn.suwg.mybatis.executor.statement.PreparedStatementHandler;
import cn.suwg.mybatis.executor.statement.StatementHandler;
import cn.suwg.mybatis.mapping.BoundSql;
import cn.suwg.mybatis.mapping.Environment;
import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.reflection.MetaObject;
import cn.suwg.mybatis.reflection.factory.DefaultObjectFactory;
import cn.suwg.mybatis.reflection.factory.ObjectFactory;
import cn.suwg.mybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import cn.suwg.mybatis.reflection.wrapper.ObjectWrapperFactory;
import cn.suwg.mybatis.scripting.LanguageDriverRegistry;
import cn.suwg.mybatis.scripting.xmltags.XMLLanguageDriver;
import cn.suwg.mybatis.transaction.Transaction;
import cn.suwg.mybatis.transaction.jdbc.JdbcTransactionFactory;
import cn.suwg.mybatis.type.TypeAliasRegistry;
import cn.suwg.mybatis.type.TypeHandlerRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
     * 类型处理器注册机.
     */
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

    /**
     * 脚本驱动注册机.
     */
    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();


    /**
     * 环境.
     */
    protected Environment environment;

    /**
     * 已加载的资源.
     */
    protected final Set<String> loadedResources = new HashSet<>();

    /**
     * 对象工厂和对象包装器工厂.
     */

    protected ObjectFactory objectFactory = new DefaultObjectFactory();
    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

    protected String databaseId;


    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);

        languageRegistry.setDefaultDriverClass(XMLLanguageDriver.class);
    }


    // 创建结果集处理器ResultSetHandler
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    // 创建执行器.
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    // 创建语句处理器.
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter,
                                                ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, resultHandler, boundSql);
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


    public boolean isResourceLoaded(String resource) {
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource) {
        loadedResources.add(resource);
    }


    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }


    /**
     * 创建元对象.
     */
    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object, objectFactory, objectWrapperFactory);
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public LanguageDriverRegistry getLanguageRegistry() {
        return languageRegistry;
    }

}
