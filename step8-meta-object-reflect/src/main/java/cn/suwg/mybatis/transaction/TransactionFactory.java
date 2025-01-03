package cn.suwg.mybatis.transaction;

import cn.suwg.mybatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 事务工厂.
 *
 * @Author: suwg
 * @Date: 2024/12/13
 */
public interface TransactionFactory {


    /**
     * 根据连接Connection创建一个事务Transaction.
     */
    Transaction newTransaction(Connection conn);


    /**
     * 根据数据源和事务隔离级别创建Transaction.
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);
}
