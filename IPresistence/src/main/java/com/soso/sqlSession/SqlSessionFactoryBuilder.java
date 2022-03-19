package com.soso.sqlSession;

import com.soso.config.XMLConfigBuilder;
import com.soso.pojo.Configuration;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) throws Exception {
        // 使用 dom4j 解析配置文件,将解析出来的内容封装到 Configuration中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);

        // 创建 sqlSessionFactory 对象

    }

}
