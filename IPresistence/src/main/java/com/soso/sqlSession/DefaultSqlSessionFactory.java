package com.soso.sqlSession;

import com.soso.pojo.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory{

    Configuration configuration = null;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
