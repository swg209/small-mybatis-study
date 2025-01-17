package cn.suwg.mybatis.reflection.invoker;

/**
 * 调用者接口.
 *
 * @Author: suwg
 * @Date: 2024/12/27
 */
public interface Invoker {

    // 调用方法
    Object invoke(Object target, Object[] args) throws Exception;

    // 获取类型
    Class<?> getType();
}
