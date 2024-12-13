package cn.suwg.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 标准的事务接口.
 *
 * @Author: suwg
 * @Date: 2024/12/13
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;
}
