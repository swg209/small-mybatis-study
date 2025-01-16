package cn.suwg.mybatis.scripting.xmltags;

import cn.suwg.mybatis.mapping.SqlSource;
import cn.suwg.mybatis.scripting.LanguageDriver;
import cn.suwg.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * XML语言驱动器.
 *
 * @Author: suwg
 * @Date: 2025/1/15
 */
public class XMLLanguageDriver implements LanguageDriver {
    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }
}
