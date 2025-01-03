package cn.suwg.mybatis.builder.xml;

import cn.suwg.mybatis.builder.BaseBuilder;
import cn.suwg.mybatis.datasource.DataSourceFactory;
import cn.suwg.mybatis.io.Resources;
import cn.suwg.mybatis.mapping.BoundSql;
import cn.suwg.mybatis.mapping.Environment;
import cn.suwg.mybatis.mapping.MappedStatement;
import cn.suwg.mybatis.mapping.SqlCommandType;
import cn.suwg.mybatis.session.Configuration;
import cn.suwg.mybatis.transaction.TransactionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
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

            // 解析环境配置.
            environmentsElement(root.element("environments"));

            // 解析映射器,读取XML文件中的mappers标识.
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }


    /**
     * <environments default="development">
     * <environment id="development">
     * <transactionManager type="JDBC">
     * <property name="..." value="..."/>
     * </transactionManager>
     * <dataSource type="POOLED">
     * <property name="driver" value="${driver}"/>
     * <property name="url" value="${url}"/>
     * <property name="username" value="${username}"/>
     * <property name="password" value="${password}"/>
     * </dataSource>
     * </environment>
     * </environments>
     */
    private void environmentsElement(Element context) throws Exception {
        //获取环境
        String environment = context.attributeValue("default");

        //获取environments标签下的所有environment标签.
        List<Element> environmentList = context.elements("environment");
        for (Element e : environmentList) {
            //获取environment标签的id属性.
            String id = e.attributeValue("id");
            if (environment.equals(id)) {
                //创建事务管理器.
                TransactionFactory txFactory = (TransactionFactory) typeAliasRegistry.resolveAlias(e.element("transactionManager")
                        .attributeValue("type")).newInstance();

                // 数据源.
                Element dataSourceElement = e.element("dataSource");
                DataSourceFactory dataSourceFactory = (DataSourceFactory) typeAliasRegistry.resolveAlias(dataSourceElement.attributeValue("type")).newInstance();
                List<Element> propertyList = dataSourceElement.elements("property");
                Properties props = new Properties();
                for (Element property : propertyList) {
                    props.setProperty(property.attributeValue("name"), property.attributeValue("value"));
                }

                dataSourceFactory.setProperties(props);
                DataSource dataSource = dataSourceFactory.getDataSource();

                //构建环境
                Environment.Builder environmentBuilder = new Environment.Builder(id)
                        .transactionFactory(txFactory)
                        .dataSource(dataSource);


                configuration.setEnvironment(environmentBuilder.build());
            }
        }


    }


    /**
     * XMLmapper 格式.
     * <mappers>
     * <mapper resource="mapper/User_Mapper.xml"/>
     * </mappers>
     *
     *
     * <mapper namespace="cn.suwg.mybatis.test.dao.IUserDao">
     *
     * <select id="queryUserInfoById" parameterType="java.lang.Long" resultType="cn.suwg.mybatis.test.po.User">
     * SELECT id, user_id, user_head, create_time
     * FROM user
     * where id = #{id}
     * </select>
     *
     * </mapper>
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
            List<Element> selectNodes = root.elements("select");
            for (Element node : selectNodes) {
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

                // 构建BoundSql.
                BoundSql boundSql = new BoundSql(sql, parameter, parameterType, resultType);

                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, msId, sqlCommandType,
                        boundSql).build();
                // 添加解析SQL
                configuration.addMappedStatement(mappedStatement);
            }

            // 注册Mapper映射器.
            configuration.addMapper(Resources.classForName(namespace));
        }
    }


}
