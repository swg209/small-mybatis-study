package cn.suwg.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import cn.suwg.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 映射器注册机.
 * @Author: suwg
 * @Date: 2024/12/9
 */
public class MapperRegistry {


    /**
     * 将已注册的映射器代理加入到 HashMap.
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();


    /**
     * 获取映射器代理.
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        MapperProxyFactory<?> mapperProxyFactory = knownMappers.get(type);
        if (mapperProxyFactory == null){
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        try{
            return (T) mapperProxyFactory.newInstance(sqlSession);
        }catch (Exception e){
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    /**
     * 扫描指定路径,注册映射器代理.
     */
    public <T> void addMapper(String packageName){
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for(Class<?> mapperClass : mapperSet){
            addMapper(mapperClass);
        }
    }

    /**
     * 注册映射器代理.
     */
    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    /**
     * 判断是否已经注册.
     * @param type
     * @return
     * @param <T>
     */
    public <T> boolean hasMapper(Class<T> type){
        return knownMappers.containsKey(type);
    }

}
