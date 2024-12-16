package cn.suwg.mybatis.test;

import cn.suwg.mybatis.io.Resources;
import cn.suwg.mybatis.session.SqlSession;
import cn.suwg.mybatis.session.SqlSessionFactory;
import cn.suwg.mybatis.session.SqlSessionFactoryBuilder;
import cn.suwg.mybatis.test.dao.IUserDao;
import cn.suwg.mybatis.test.po.User;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * @Author: suwg
 * @Date: 2024/12/6
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);


    // 测试SqlSessionFactory
    @Test
    public void testSqlSessionFactory() throws IOException {

        // 1.从xml文件读取mybatis配置项, 从SqlSessionFactory获取SqlSession.
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        // 2.获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4.测试验证
        User user = userDao.queryUserInfoById(1L);
        logger.info("测试结果：{}", JSON.toJSONString(user));

    }


}
