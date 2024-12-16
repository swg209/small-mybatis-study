package cn.suwg.mybatis.session;

import cn.suwg.mybatis.binding.MapperRegistry;
import cn.suwg.mybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * 会话配置信息.
 *
 * @Author: suwg
 * @Date: 2024/12/12
 */
public class Configuration {

    /**
     * 映射注册机.
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry();

    /**
     * 映射的语句，存在Map.
     */
    protected final Map<String, MappedStatement> mappedStatementMap = new HashMap<>();


    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatementMap.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatementMap.get(id);
    }


}
