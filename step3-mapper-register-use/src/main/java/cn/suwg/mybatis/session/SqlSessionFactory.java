package cn.suwg.mybatis.session;

/**
 * sql会话工厂.
 * @Author: suwg
 * @Date: 2024/12/9
 */
public interface SqlSessionFactory {

    /**
     * 打开一个会话.
     */
    SqlSession openSession();
}
