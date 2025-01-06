package cn.suwg.mybatis.session;

import cn.suwg.mybatis.builder.xml.XMLConfigBuilder;
import cn.suwg.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * @description 构建SqlSessionFactory的工厂
 * @Author: suwg
 * @Date: 2024/12/12
 */
public class SqlSessionFactoryBuilder {


    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }


}
