package cn.suwg.mybatis.mapping;

import cn.suwg.mybatis.session.Configuration;

/**
 * 映射语句类.
 *
 * @Author: suwg
 * @Date: 2024/12/12
 */
public class MappedStatement {

    // 配置信息
    private Configuration configuration;

    // 唯一标识
    private String id;

    // SQL类型
    private SqlCommandType sqlCommandType;
    

    // Sql源码
    private SqlSource sqlSource;

    // 结果类型
    Class<?> resultType;

    MappedStatement() {
        // constructor disabled
    }

    /**
     * 建造者.
     */
    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, SqlSource sqlSource, Class<?> resultType) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.sqlSource = sqlSource;
            mappedStatement.resultType = resultType;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            return mappedStatement;
        }
    }

    // 生成所有属性的get set 方法
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public Class<?> getResultType() {
        return resultType;
    }
}
