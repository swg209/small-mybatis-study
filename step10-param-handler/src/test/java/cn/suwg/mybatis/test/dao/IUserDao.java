package cn.suwg.mybatis.test.dao;

import cn.suwg.mybatis.test.po.User;

/**
 * @Author: suwg
 * @Date: 2024/12/6
 */
public interface IUserDao {

    User queryUserInfoById(Long uid);

}
