package cn.suwg.mybatis.test;

import cn.suwg.mybatis.binding.MapperProxyFactory;
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
        //创建MapperProxyFactory
        MapperProxyFactory<IUserDao> mapperProxyFactory = new MapperProxyFactory<>(IUserDao.class);

        //模拟sqlSession, 方法名和方法调用响应.
        Map<String, Object> sqlSession = new HashMap<>();
        sqlSession.put("cn.suwg.mybatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名:小苏");
        sqlSession.put("cn.suwg.mybatis.test.dao.IUserDao.queryUserAge", 28);
        IUserDao userDao = mapperProxyFactory.newInstance(sqlSession);

        //调用方法
        String result = userDao.queryUserName("10001");

        Integer age = userDao.queryUserAge("10001");
        logger.info("测试结果：{}, 年龄：{}",result, age);

    }


}
