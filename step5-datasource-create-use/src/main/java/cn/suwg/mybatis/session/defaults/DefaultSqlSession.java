package cn.suwg.mybatis.session.defaults;

import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.session.Configuration;
import cn.suwg.mybatis.session.SqlSession;

/**
 * 默认SqlSession实现类.
 *
 * @Author: suwg
 * @Date: 2024/12/9
 */
public class DefaultSqlSession implements SqlSession {

    /**
     * 配置项.
     */
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }


    /**
     * 根据给定的执行SQL获取一条记录的封装对象.
     *
     * @param statement
     * @param <T>
     * @return
     */

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你的操作被代理了," + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("你的操作被代理了！" + "方法：" + statement + " 入参：" + parameter + "\n待执行SQL：" + mappedStatement.getSql());

    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }


}
