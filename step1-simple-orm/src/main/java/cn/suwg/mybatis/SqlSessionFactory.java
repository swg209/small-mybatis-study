package cn.suwg.mybatis;

/**
 * 会话工厂.
 *
 * @Author: suwg
 * @Date: 2024/3/25
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
