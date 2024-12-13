package cn.suwg.mybatis.binding;

import cn.suwg.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 映射器代理类(代理Dao).
 *
 * @Author: suwg
 * @Date: 2024/12/6
 * 公众号: 趣研
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {


    // sql会话.
    private SqlSession sqlSession;

    // 映射器接口.
    private final Class<T> mapperInterface;

    // 映射方法缓存.
    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            final MapperMethod mapperMethod = cacheMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }
    }

    /**
     * 在缓存中查找mapperMethod.
     */
    private MapperMethod cacheMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null) {
            //找不到才去new
            mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
