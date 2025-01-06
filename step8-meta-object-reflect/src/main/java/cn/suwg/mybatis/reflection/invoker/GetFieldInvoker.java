package cn.suwg.mybatis.reflection.invoker;

import java.lang.reflect.Field;

/**
 * getter 调用者.
 *
 * @Author: suwg
 * @Date: 2024/12/27
 */
public class GetFieldInvoker implements Invoker {

    private Field field;

    public GetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        // 获取字段值
        return field.get(target);
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
