package com.soso.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.soso.io.Resources;
import com.soso.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XMLConfigBuilder {

    private Configuration configuration;

    public XMLConfigBuilder() {
        configuration = new Configuration();
    }


    public Configuration parseConfig(InputStream inputStream) throws Exception {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();

        for(Element element:list){
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        configuration.setDataSource(comboPooledDataSource);

        // mapper.xml 解析
        List<Element> mapperList = rootElement.selectNodes("//mapper");
        for(Element element:mapperList){
            String mapperPath = element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperPath);
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);
        }

        return null;
    }

}
