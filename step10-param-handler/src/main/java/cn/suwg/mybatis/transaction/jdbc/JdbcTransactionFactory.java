package cn.suwg.mybatis.transaction.jdbc;

import cn.suwg.mybatis.session.TransactionIsolationLevel;
import cn.suwg.mybatis.transaction.Transaction;
import cn.suwg.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 事务工厂.
 *
 * @Author: suwg
 * @Date: 2024/12/13
 */
public class JdbcTransactionFactory implements TransactionFactory {
    
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
