package cn.suwg.mybatis.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 映射器代理类(代理Dao).
 * @Author: suwg
 * @Date: 2024/12/6
 * 公众号: 趣研
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {


    // sql会话.
    private Map<String, Object> sqlSession;

    // 映射器接口.
    private final Class<T> mapperInterface;

    public MapperProxy(Map<String,Object> sqlSession, Class<T> mapperInterface){
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }




    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
        } else {
            return sqlSession.get(mapperInterface.getName()+"."+method.getName());
        }
    }
}
