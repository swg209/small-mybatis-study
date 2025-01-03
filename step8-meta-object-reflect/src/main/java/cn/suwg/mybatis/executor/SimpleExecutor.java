package cn.suwg.mybatis.executor;

import cn.suwg.mybatis.executor.statement.StatementHandler;
import cn.suwg.mybatis.mapping.BoundSql;
import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.session.Configuration;
import cn.suwg.mybatis.session.ResultHandler;
import cn.suwg.mybatis.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 简单执行器实现.
 *
 * @Author: suwg
 * @Date: 2024/12/24
 */
public class SimpleExecutor extends BaseExecutor {


    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        //处理查询逻辑.
        try {
            //获取配置信息
            Configuration configuration = ms.getConfiguration();
            //获取StatementHandler
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, resultHandler, boundSql);
            //获取连接
            Connection connection = transaction.getConnection();
            Statement stmt = handler.prepare(connection);
            handler.parameterize(stmt);
            return handler.query(stmt, resultHandler);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
