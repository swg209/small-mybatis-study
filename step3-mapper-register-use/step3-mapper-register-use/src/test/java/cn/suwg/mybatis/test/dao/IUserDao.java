package cn.suwg.mybatis.test.dao;

/**
 * @Author: suwg
 * @Date: 2024/12/6
 */
public interface IUserDao {

    String queryUserName(String uid);

    Integer queryUserAge(String uid);
}
