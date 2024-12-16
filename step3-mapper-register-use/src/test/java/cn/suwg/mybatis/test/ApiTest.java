package cn.suwg.mybatis.test;

import cn.suwg.mybatis.binding.MapperProxyFactory;
import cn.suwg.mybatis.binding.MapperRegistry;
import cn.suwg.mybatis.session.SqlSession;
import cn.suwg.mybatis.session.defaults.DefaultSqlSessionFactory;
import cn.suwg.mybatis.test.dao.IUserDao;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: suwg
 * @Date: 2024/12/6
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);


    // 测试mapperProxyFactory
    @Test
    public void testMapperProxyFactory(){
        // 1.注册Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMapper("cn.suwg.mybatis.test.dao");

        // 2.从SqlSessionFactory获取Session
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

         // 3.获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4.测试验证
        String result = userDao.queryUserName("10001");
        logger.info("测试结果：{}, 年龄：{}",result, 28);

    }


}
