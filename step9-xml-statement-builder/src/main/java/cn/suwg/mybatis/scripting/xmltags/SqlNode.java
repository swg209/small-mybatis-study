package cn.suwg.mybatis.scripting.xmltags;

/**
 * SQL节点.
 *
 * @Author: suwg
 * @Date: 2025/1/14
 */
public interface SqlNode {

    boolean apply(DynamicContext context);
}
