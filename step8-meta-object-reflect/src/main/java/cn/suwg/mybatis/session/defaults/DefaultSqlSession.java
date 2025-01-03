package cn.suwg.mybatis.session.defaults;

import cn.suwg.mybatis.executor.Executor;
import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.session.Configuration;
import cn.suwg.mybatis.session.SqlSession;

import java.util.List;

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

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
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
        return this.selectOne(statement, null);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        //映射语句
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        //使用执行器.
        List<T> list = executor.query(mappedStatement, parameter, Executor.NO_RESULT_HANDLER, mappedStatement.getBoundSql());
        return list.get(0);
    }
    

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }


}
