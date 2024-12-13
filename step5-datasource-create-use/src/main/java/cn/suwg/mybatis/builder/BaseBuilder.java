package cn.suwg.mybatis.builder;

import cn.suwg.mybatis.session.Configuration;
import cn.suwg.mybatis.type.TypeAliasRegistry;

/**
 * @description 构建器的基类，建造者模式
 * @Author: suwg
 * @Date: 2024/12/12
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
