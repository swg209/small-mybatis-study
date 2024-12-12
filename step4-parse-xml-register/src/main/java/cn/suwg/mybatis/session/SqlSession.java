package cn.suwg.mybatis.session;

/**
 * SqlSession 用来执行SQL，获取映射器，管理事务.
 * 通常情况下，我们在应用程序中使用的Mybatis的API就是这个接口定义的方法。
 *
 * @Author: suwg
 * @Date: 2024/12/9
 */
public interface SqlSession {

    /**
     * 获取配置信息Configuration.
     */
    Configuration getConfiguration();


    /**
     * 根据给定的执行SQL获取一条记录的封装对象.
     *
     * @param statement
     * @param <T>
     * @return
     */
    <T> T selectOne(String statement);


    /**
     * 根据给定的执行SQL获取一条记录的封装对象，带参数.
     *
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * 获取映射器.
     * 这里使用了泛型，使得类型安全.
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> type);
}
