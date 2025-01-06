package cn.suwg.mybatis.executor.resultset;

import cn.hutool.core.util.StrUtil;
import cn.suwg.mybatis.executor.Executor;
import cn.suwg.mybatis.mapping.BoundSql;
import cn.suwg.mybatis.mapping.MappedStatement;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 默认结果集处理器.
 *
 * @Author: suwg
 * @Date: 2024/12/24
 */
public class DefaultResultSetHandler implements ResultSetHandler {

    private final BoundSql boundSql;

    public DefaultResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        this.boundSql = boundSql;
    }


    @Override
    public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
        ResultSet resultSet = stmt.getResultSet();
        try {
            //将结果集转换为对象
            return resultSet2Obj(resultSet, Class.forName(boundSql.getResultType()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            //获取结果集元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //获取列数
            int columnCount = metaData.getColumnCount();
            // 每次遍历行值
            while (resultSet.next()) {
                //创建对象
                T obj = (T) clazz.newInstance();
                //遍历列
                for (int i = 1; i <= columnCount; i++) {
                    //获取列值
                    Object value = resultSet.getObject(i);
                    //获取列名
                    String columnName = metaData.getColumnName(i);
                    //拼接set方法
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + StrUtil.toCamelCase(columnName.substring(1));
                    //获取set方法
                    Method method;
                    //判断是否是时间类型
                    if (value instanceof Timestamp) {
                        method = clazz.getMethod(setMethod, Date.class);
                    } else {
                        method = clazz.getMethod(setMethod, value.getClass());
                    }
                    //执行set方法
                    method.invoke(obj, value);
                }
                //添加到集合
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
