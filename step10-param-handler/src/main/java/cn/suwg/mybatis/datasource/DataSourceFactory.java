package cn.suwg.mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @description 数据源工厂
 * @Author: suwg
 * @Date: 2024/12/13
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();
}
