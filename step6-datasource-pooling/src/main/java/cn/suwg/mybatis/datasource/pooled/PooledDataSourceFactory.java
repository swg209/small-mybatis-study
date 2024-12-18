package cn.suwg.mybatis.datasource.pooled;

import cn.suwg.mybatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;

/**
 * 池化的数据源工厂.
 *
 * @Author: suwg
 * @Date: 2024/12/16
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {


    @Override
    public DataSource getDataSource() {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver(props.getProperty("driver"));
        pooledDataSource.setUrl(props.getProperty("url"));
        pooledDataSource.setUsername(props.getProperty("username"));
        pooledDataSource.setPassword(props.getProperty("password"));
        return pooledDataSource;
    }
}
