package cn.suwg.mybatis.session;

import java.sql.Connection;

/**
 * 事务隔离级别.
 *
 * @Author: suwg
 * @Date: 2024/12/13
 */
public enum TransactionIsolationLevel {


    // 事务隔离级别
    // 无
    NONE(Connection.TRANSACTION_NONE),
    // 读未提交
    READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
    // 读已提交
    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
    // 可重复读
    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
    // 串行化
    SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

    private final int level;

    TransactionIsolationLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
