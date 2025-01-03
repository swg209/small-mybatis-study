package cn.suwg.mybatis.executor.statement;

import cn.suwg.mybatis.executor.Executor;
import cn.suwg.mybatis.mapping.BoundSql;
import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Author: suwg
 * @Date: 2024/12/24
 */
public class PreparedStatementHandler extends BaseStatementHandler {
    public PreparedStatementHandler(Executor executor, MappedStatement mapperStatement, Object parameter,
                                    ResultHandler resultHandler,
                                    BoundSql boundSql) {
        super(executor, mapperStatement, parameter, resultHandler, boundSql);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        String sql = boundSql.getSql();
        return connection.prepareStatement(sql);
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.setLong(1, Long.parseLong(((Object[]) parameter)[0].toString()));
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.execute();
        return resultSetHandler.<E>handleResultSets(ps);
    }
}
