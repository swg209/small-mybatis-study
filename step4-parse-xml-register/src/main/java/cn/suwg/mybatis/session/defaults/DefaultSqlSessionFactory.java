package cn.suwg.mybatis.session.defaults;

import cn.suwg.mybatis.session.Configuration;
import cn.suwg.mybatis.session.SqlSession;
import cn.suwg.mybatis.session.SqlSessionFactory;

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
        return new DefaultSqlSession(configuration);
    }
}
