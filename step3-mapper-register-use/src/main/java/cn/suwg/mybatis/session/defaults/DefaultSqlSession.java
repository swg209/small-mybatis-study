package cn.suwg.mybatis.session.defaults;

import cn.suwg.mybatis.binding.MapperRegistry;
import cn.suwg.mybatis.session.SqlSession;

/**
 * 默认SqlSession实现类.
 * @Author: suwg
 * @Date: 2024/12/9
 */
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射器注册机.
     * @param statement
     * @return
     * @param <T>
     */
    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }


    /**
     * 根据给定的执行SQL获取一条记录的封装对象.
     * @param statement
     * @return
     * @param <T>
     */

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你的操作被代理了,"+statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你的操作被代理了！" + "方法：" + statement + " 入参：" + parameter);

    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
