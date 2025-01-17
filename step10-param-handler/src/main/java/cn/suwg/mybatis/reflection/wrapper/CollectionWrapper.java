package cn.suwg.mybatis.reflection.wrapper;

import cn.suwg.mybatis.reflection.MetaObject;
import cn.suwg.mybatis.reflection.factory.ObjectFactory;
import cn.suwg.mybatis.reflection.property.PropertyTokenizer;

import java.util.Collection;
import java.util.List;

/**
 * 集合包装器.
 *
 * @Author: suwg
 * @Date: 2025/1/3
 */
public class CollectionWrapper implements ObjectWrapper {


    // 原对象.
    private Collection<Object> object;

    public CollectionWrapper(MetaObject metaObject, Collection<Object> object) {
        this.object = object;
    }

    // get,set都是不允许的,只能添加元素

    @Override
    public Object get(PropertyTokenizer prop) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(PropertyTokenizer prop, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getGetterNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getSetterNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> getSetterType(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> getGetterType(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasSetter(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasGetter(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCollection() {
        return true;
    }

    @Override
    public void add(Object element) {
        object.add(element);
    }

    @Override
    public <E> void addAll(List<E> element) {
        object.addAll(element);

    }
}
