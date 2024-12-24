package cn.suwg.mybatis.session.defaults;

import cn.hutool.core.util.StrUtil;
import cn.suwg.mybatis.mapping.BoundSql;
import cn.suwg.mybatis.mapping.Environment;
import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.session.Configuration;
import cn.suwg.mybatis.session.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 默认SqlSession实现类.
 *
 * @Author: suwg
 * @Date: 2024/12/9
 */
public class DefaultSqlSession implements SqlSession {

    /**
     * 配置项.
     */
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }


    /**
     * 根据给定的执行SQL获取一条记录的封装对象.
     *
     * @param statement
     * @param <T>
     * @return
     */

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你的操作被代理了," + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        try {
            //映射语句
            MappedStatement mappedStatement = configuration.getMappedStatement(statement);
            //环境
            Environment environment = configuration.getEnvironment();

            //连接
            Connection connection = environment.getDataSource().getConnection();

            BoundSql boundSql = mappedStatement.getBoundSql();
            PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
            preparedStatement.setLong(1, Long.parseLong(((Object[]) parameter)[0].toString()));
            //执行查询
            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> objectList = resultSet2Obj(resultSet, Class.forName(boundSql.getResultType()));
            return objectList.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 每次遍历行值
            while (resultSet.next()) {
                T obj = (T) clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + StrUtil.toCamelCase(columnName.substring(1));
                    java.lang.reflect.Method method;
                    if (value instanceof java.sql.Timestamp) {
                        method = clazz.getMethod(setMethod, java.util.Date.class);
                    } else {
                        method = clazz.getMethod(setMethod, value.getClass());
                    }
                    method.invoke(obj, value);
                }
                list.add(obj);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }


}
