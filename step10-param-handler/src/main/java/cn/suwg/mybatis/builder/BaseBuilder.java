package cn.suwg.mybatis.builder;

import cn.suwg.mybatis.session.Configuration;
import cn.suwg.mybatis.type.TypeAliasRegistry;
import cn.suwg.mybatis.type.TypeHandlerRegistry;

/**
 * @description 构建器的基类，建造者模式
 * @Author: suwg
 * @Date: 2024/12/12
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;

    protected final TypeHandlerRegistry typeHandlerRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
        this.typeHandlerRegistry = this.configuration.getTypeHandlerRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    protected Class<?> resolveAlias(String alias) {
        return typeAliasRegistry.resolveAlias(alias);
    }
}
