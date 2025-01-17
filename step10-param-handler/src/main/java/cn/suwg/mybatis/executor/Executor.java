package cn.suwg.mybatis.executor;

import cn.suwg.mybatis.mapping.BoundSql;
import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.session.ResultHandler;
import cn.suwg.mybatis.transaction.Transaction;

import java.util.List;

/**
 * 执行器接口.
 *
 * @Author: suwg
 * @Date: 2024/12/24
 */
public interface Executor {
    //暂时先不处理结果集.
    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws Exception;

    void rollback(boolean required) throws Exception;

    void close(boolean forceRollback);
    

}
