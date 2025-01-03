package cn.suwg.mybatis.mapping;

/**
 * @description SQL指令类型
 * @Author: suwg
 * @Date: 2024/12/12
 */
public enum SqlCommandType {
    /**
     * 未知
     */
    UNKNOWN,
    /**
     * 插入
     */
    INSERT,
    /**
     * 更新
     */
    UPDATE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 查找
     */
    SELECT;
}
