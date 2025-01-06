package cn.suwg.mybatis.mapping;

import java.util.Map;

/**
 * 绑定的SQL, 是从SqlSource而来，将动态内容都处理完成得到的SQL语句字符串，其中包括?,还有绑定的参数.
 *
 * @Author: suwg
 * @Date: 2024/12/13
 */
public class BoundSql {

    private String sql;
    private Map<Integer, String> parameterMappings;
    private String parameterType;
    private String resultType;

    public BoundSql(String sql, Map<Integer, String> parameterMappings, String parameterType, String resultType) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterType = parameterType;
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public String getParameterType() {
        return parameterType;
    }

    public Map<Integer, String> getParameterMappings() {
        return parameterMappings;
    }

    public String getResultType() {
        return resultType;
    }
}
