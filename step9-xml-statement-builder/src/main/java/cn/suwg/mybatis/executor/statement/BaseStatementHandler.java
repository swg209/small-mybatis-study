package cn.suwg.mybatis.executor.statement;

import cn.suwg.mybatis.executor.Executor;
import cn.suwg.mybatis.executor.resultset.ResultSetHandler;
import cn.suwg.mybatis.mapping.BoundSql;
import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.session.Configuration;
import cn.suwg.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 语句处理器基类.
 *
 * @Author: suwg
 * @Date: 2024/12/24
 */
public abstract class BaseStatementHandler implements StatementHandler {


    protected final Configuration configuration;

    protected final Executor executor;

    protected final MappedStatement mapperStatement;

    protected final Object parameter;

    protected final ResultSetHandler resultSetHandler;

    protected BoundSql boundSql;

    protected BaseStatementHandler(Executor executor, MappedStatement mapperStatement, Object parameter,
                                   ResultHandler resultHandler,
                                   BoundSql boundSql) {
        this.executor = executor;
        this.configuration = mapperStatement.getConfiguration();
        this.mapperStatement = mapperStatement;
        this.parameter = parameter;
        this.boundSql = boundSql;
        this.resultSetHandler = configuration.newResultSetHandler(executor, mapperStatement, boundSql);
    }


    @Override
    public Statement prepare(Connection connection) throws SQLException {
        Statement statement = null;
        try {
            statement = instantiateStatement(connection);
            statement.setQueryTimeout(350);
            statement.setFetchSize(10000);
            return statement;
        } catch (Exception e) {
            throw new RuntimeException("Error preparing statement.  Cause: " + e, e);
        }
    }


    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;

}
