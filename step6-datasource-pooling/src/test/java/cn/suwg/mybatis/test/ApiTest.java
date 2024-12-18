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


    // 测试无池化的数据源.
    // 每次都会创建
    @Test
    public void testUnpooledDataSource() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        for (int i = 0; i < 50; i++) {
            User user = userDao.queryUserInfoById(1L);
            logger.info("测试结果：{}", JSON.toJSONString(user));
        }

    }


    // 测试池化的数据源.
    // 连接的获取从池子获取.
    @Test
    public void testPooledDataSource() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        for (int i = 0; i < 100; i++) {
            User user = userDao.queryUserInfoById(1L);
            logger.info("测试结果：{}", JSON.toJSONString(user));
        }

    }


}
