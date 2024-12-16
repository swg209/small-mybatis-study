package cn.suwg.mybatis.session.defaults;

import cn.suwg.mybatis.binding.MapperRegistry;
import cn.suwg.mybatis.session.SqlSession;
import cn.suwg.mybatis.session.SqlSessionFactory;

/**
 * 默认SqlSessionFactory实现类.
 * @Author: suwg
 * @Date: 2024/12/9
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
