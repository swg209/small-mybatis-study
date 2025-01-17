package cn.suwg.mybatis.executor.statement;

import cn.suwg.mybatis.executor.Executor;
import cn.suwg.mybatis.mapping.BoundSql;
import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 简单语句处理器.
 *
 * @Author: suwg
 * @Date: 2024/12/24
 */
public class SimpleStatementHandler extends BaseStatementHandler {


    public SimpleStatementHandler(Executor executor, MappedStatement mapperStatement, Object parameter,
                                  ResultHandler resultHandler,
                                  BoundSql boundSql) {
        super(executor, mapperStatement, parameter, resultHandler, boundSql);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {
        // N/A
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        String sql = boundSql.getSql();
        statement.execute(sql);
        return resultSetHandler.handleResultSets(statement);
    }
}
