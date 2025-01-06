package cn.suwg.mybatis.datasource.pooled;

import cn.suwg.mybatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * 池化的数据源工厂.
 *
 * @Author: suwg
 * @Date: 2024/12/16
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }

}
