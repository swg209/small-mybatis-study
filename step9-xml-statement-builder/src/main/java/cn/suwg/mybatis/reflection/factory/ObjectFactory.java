package cn.suwg.mybatis.reflection.factory;

import java.util.List;
import java.util.Properties;

/**
 * 对象工厂接口.
 *
 * @Author: suwg
 * @Date: 2024/12/27
 */
public interface ObjectFactory {

    /**
     * 设置属性.
     *
     * @param properties
     */
    void setProperties(Properties properties);


    /**
     * 创建对象.
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> T create(Class<T> type);

    /**
     * 创建对象.使用指定的构造函数和构造函数参数
     *
     * @param type
     * @param constructorArgTypes
     * @param constructorArgs
     * @param <T>
     * @return
     */
    <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs);


    /**
     * 返回这个对象是否是集合，为了支持 Scala collections
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> boolean isCollection(Class<T> type);


}
