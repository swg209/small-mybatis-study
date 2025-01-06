package cn.suwg.mybatis.reflection.wrapper;

import cn.suwg.mybatis.reflection.MetaObject;

/**
 * 默认对象包装工厂.
 *
 * @Author: suwg
 * @Date: 2025/1/3
 */
public class DefaultObjectWrapperFactory implements ObjectWrapperFactory {
    @Override
    public boolean hasWrapperFor(Object object) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        throw new RuntimeException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }
}
