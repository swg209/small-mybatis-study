package cn.suwg.mybatis.mapping;

/**
 * SQL源码.
 *
 * @Author: suwg
 * @Date: 2025/1/14
 */
public interface SqlSource {

    //获取绑定SQL,替换好参数后的SQL
    BoundSql getBoundSql(Object parameterObject);

}
