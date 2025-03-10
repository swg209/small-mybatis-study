package cn.suwg.mybatis.session.defaults;

import cn.suwg.mybatis.executor.Executor;
import cn.suwg.mybatis.mapping.Environment;
import cn.suwg.mybatis.session.Configuration;
import cn.suwg.mybatis.session.SqlSession;
import cn.suwg.mybatis.session.SqlSessionFactory;
import cn.suwg.mybatis.session.TransactionIsolationLevel;
import cn.suwg.mybatis.transaction.Transaction;
import cn.suwg.mybatis.transaction.TransactionFactory;

import java.sql.SQLException;

/**
 * 默认SqlSessionFactory实现类.
 *
 * @Author: suwg
 * @Date: 2024/12/9
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(),
                    TransactionIsolationLevel.READ_COMMITTED,
                    false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            // 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }
}
