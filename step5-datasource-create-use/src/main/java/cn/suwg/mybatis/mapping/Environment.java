package cn.suwg.mybatis.mapping;

import cn.suwg.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;

/**
 * @description 环境.
 * @Author: suwg
 * @Date: 2024/12/13
 */
public final class Environment {


    /**
     * 环境id.
     */
    private final String id;

    /**
     * 事务工厂.
     */
    private final TransactionFactory transactionFactory;

    /**
     * 数据源.
     */
    private final DataSource dataSource;

    public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
        this.id = id;
        this.dataSource = dataSource;
        this.transactionFactory = transactionFactory;
    }

    public String getId() {
        return id;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }


    public static class Builder {


        private String id;

        private TransactionFactory transactionFactory;

        private DataSource dataSource;

        public Builder(String id) {
            this.id = id;
        }

        public Builder transactionFactory(TransactionFactory transactionFactory) {
            this.transactionFactory = transactionFactory;
            return this;
        }

        public Builder dataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public Environment build() {
            return new Environment(this.id, this.transactionFactory, this.dataSource);
        }

        public String id() {
            return this.id;
        }


    }


}
