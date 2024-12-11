package cn.suwg.mybatis.test;

import cn.suwg.mybatis.Resources;
import cn.suwg.mybatis.SqlSession;
import cn.suwg.mybatis.SqlSessionFactory;
import cn.suwg.mybatis.SqlSessionFactoryBuilder;
import cn.suwg.mybatis.test.po.User;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

/**
 * @Author: suwg
 * @Date: 2024/3/25
 */
public class ApiTest {

    @Test
    public void test_queryUserInfoById() {
        String resource = "mybatis-config-datasource.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

            SqlSession session = sqlMapper.openSession();
            try {
                User user = session.selectOne("cn.suwg.mybatis.test.dao.IUserDao.queryUserInfoById", 1L);
                System.out.println(JSON.toJSONString(user));
            } finally {
                session.close();
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_queryUserInfoList() {
        String resource = "mybatis-config-datasource.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

            SqlSession session = sqlMapper.openSession();
            try {
                List<User> userList = session.selectList("cn.suwg.mybatis.test.dao.IUserDao.queryUserList",
                        new User("小苏"));
                System.out.println(JSON.toJSONString(userList));
            } finally {
                session.close();
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
