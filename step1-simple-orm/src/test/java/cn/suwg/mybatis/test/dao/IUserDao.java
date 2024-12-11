package cn.suwg.mybatis.test.dao;

import cn.suwg.mybatis.test.po.User;

import java.util.List;

/**
 * @Author: suwg
 * @Date: 2024/3/25
 */
public interface IUserDao {

    User queryUserInfoById(Long id);


    List<User> queryUserList(User user);


}
