package cn.suwg.mybatis.scripting;

import cn.suwg.mybatis.mapping.SqlSource;
import cn.suwg.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * 脚本语言驱动.
 *
 * @Author: suwg
 * @Date: 2025/1/14
 */
public interface LanguageDriver {

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);
}
