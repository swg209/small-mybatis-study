package cn.suwg.mybatis.reflection.invoker;

import java.lang.reflect.Method;

/**
 * 方法调用者.
 *
 * @Author: suwg
 * @Date: 2024/12/27
 */
public class MethodInvoker implements Invoker {

    // 类名.
    private Class<?> type;

    // 方法
    private Method method;


    public MethodInvoker(Method method) {
        this.method = method;

        // 如果只有一个参数，返回参数类型，否则返回 return 类型
        if (method.getParameterTypes().length == 1) {
            type = method.getParameterTypes()[0];
        } else {
            type = method.getReturnType();
        }
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return method.invoke(target, args);
    }

    @Override
    public Class<?> getType() {
        return type;
    }
}
