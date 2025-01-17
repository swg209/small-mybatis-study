package cn.suwg.mybatis.scripting.xmltags;

import java.util.List;

/**
 * 混合SQL节点.
 *
 * @Author: suwg
 * @Date: 2025/1/15
 */
public class MixedSqlNode implements SqlNode {

    // 组合模式，SQL节点集合
    private List<SqlNode> contents;

    public MixedSqlNode(List<SqlNode> contents) {
        this.contents = contents;
    }

    @Override
    public boolean apply(DynamicContext context) {
        // 依次调用list里每个元素的apply
        contents.forEach(node -> node.apply(context));
        return false;
    }
}
