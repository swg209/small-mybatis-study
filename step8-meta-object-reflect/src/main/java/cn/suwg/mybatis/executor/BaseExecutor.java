package cn.suwg.mybatis.executor;

import cn.suwg.mybatis.mapping.BoundSql;
import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.session.Configuration;
import cn.suwg.mybatis.session.ResultHandler;
import cn.suwg.mybatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器基类.
 *
 * @Author: suwg
 * @Date: 2024/12/24
 */
public abstract class BaseExecutor implements Executor {

    Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuration;

    protected Transaction transaction;

    protected Executor wrapper;

    private boolean closed;

    protected BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
        this.wrapper = this;
    }

    // 连接关闭处理统一逻辑
    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        if (closed) {
            throw new RuntimeException("Executor was closed.");
        }
        return doQuery(ms, parameter, resultHandler, boundSql);
    }

    protected abstract <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    @Override
    public Transaction getTransaction() {
        if (closed) {
            throw new RuntimeException("Executor was closed.");
        }
        return transaction;
    }

    @Override
    public void commit(boolean required) throws Exception {
        if (closed) {
            throw new RuntimeException("Cannot commit, transaction is already closed");
        }
        if (required) {
            transaction.commit();
        }
    }

    @Override
    public void rollback(boolean required) throws SQLException {
        if (closed) {
            throw new RuntimeException("Cannot rollback, transaction is already closed");
        }
        if (required) {
            transaction.rollback();
        }
    }

    @Override
    public void close(boolean forceRollback) {
        try {
            try {
                rollback(forceRollback);
            } finally {
                transaction.close();
            }
        } catch (SQLException e) {
            logger.warn("Unexpected exception on closing transaction.  Cause: " + e);
        } finally {
            transaction = null;
            closed = true;
        }
    }
}
