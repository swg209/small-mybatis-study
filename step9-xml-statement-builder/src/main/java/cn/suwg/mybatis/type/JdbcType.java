package cn.suwg.mybatis.type;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * JDBC类型枚举.
 *
 * @Author: suwg
 * @Date: 2024/12/13
 */
public enum JdbcType {

    INTEGER(Types.INTEGER),
    FLOAT(Types.FLOAT),
    DOUBLE(Types.DOUBLE),
    DECIMAL(Types.DECIMAL),
    VARCHAR(Types.VARCHAR),
    TIMESTAMP(Types.TIMESTAMP);

    public final int TYPE_CODE;

    private static Map<Integer, JdbcType> codeLookup = new HashMap<>();

    // 初始化数据,将数字对应的枚举型放入 HashMap
    static {
        for (JdbcType type : JdbcType.values()) {
            codeLookup.put(type.TYPE_CODE, type);
        }
    }

    JdbcType(int code) {
        this.TYPE_CODE = code;
    }

    public static JdbcType forCode(int code) {
        return codeLookup.get(code);
    }


}
