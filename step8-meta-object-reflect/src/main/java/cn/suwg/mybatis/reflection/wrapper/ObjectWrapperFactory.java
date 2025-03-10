package cn.suwg.mybatis.reflection.wrapper;

import cn.suwg.mybatis.reflection.MetaObject;

/**
 * 对象包装工厂.
 *
 * @Author: suwg
 * @Date: 2024/12/30
 */
public interface ObjectWrapperFactory {

    /**
     * 判断有没有包装器
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);
}
