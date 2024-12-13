package cn.suwg.mybatis.builder.xml;

import cn.suwg.mybatis.builder.BaseBuilder;
import cn.suwg.mybatis.io.Resources;
import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.mapping.SqlCommandType;
import cn.suwg.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description XML配置构建器, 建造者模式，继承BaseBuilder.
 * @Author: suwg
 * @Date: 2024/12/12
 */
public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 1. 调用父类初始化Configuration
        super(new Configuration());
        // 2. dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    //从xml文件解析Configuration.
    public Configuration parse() {
        try {
            // 解析映射器,读取XML文件中的mappers标识.
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }


    /**
     * XMLmapper 格式.
     * <mappers>
     * <mapper resource="mapper/User_Mapper.xml"/>
     * </mappers>
     *
     * @param mappers
     * @throws Exception
     */
    private void mapperElement(Element mappers) throws Exception {
        //获取mappers标签下的所有mapper标签.
        List<Element> mapperList = mappers.elements("mapper");
        for (Element mapper : mapperList) {
            //获取mapper标签的resource属性.
            String resource = mapper.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element root = document.getRootElement();

            //命名空间
            String namespace = root.attributeValue("namespace");

            //SELECT  解析语句.
            List<Element> selectList = root.elements("select");
            for (Element node : selectList) {
                String id = node.attributeValue("id");
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getText();


                // ?匹配
                Map<Integer, String> parameter = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                //匹配到的参数替换为?
                for (int i = 1; matcher.find(); i++) {
                    String g1 = matcher.group(1);
                    String g2 = matcher.group(2);
                    parameter.put(i, g2);
                    sql = sql.replace(g1, "?");
                }

                String msId = namespace + "." + id;
                String nodeName = node.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, msId, sqlCommandType,
                        parameterType, resultType, sql, parameter).build();
                // 添加解析SQL
                configuration.addMappedStatement(mappedStatement);
            }

            // 注册Mapper映射器.
            configuration.addMapper(Resources.classForName(namespace));
        }
    }


}
