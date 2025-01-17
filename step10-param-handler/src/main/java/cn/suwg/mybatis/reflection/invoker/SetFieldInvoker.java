package cn.suwg.mybatis.reflection.invoker;

import java.lang.reflect.Field;

/**
 * setter调用者
 *
 * @Author: suwg
 * @Date: 2024/12/27
 */
public class SetFieldInvoker implements Invoker {

    // 字段
    private Field field;

    public SetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        field.set(target, args[0]);
        return null;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
