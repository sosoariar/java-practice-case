package com.soso.sqlSession;

import com.soso.pojo.Configuration;
import com.soso.pojo.MappedStatement;

import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementid, Object... params) {

        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        List<Object> list = simpleExecutor.query(configuration,mappedStatement,params);

        return null;
    }

    @Override
    public <T> T selectOne(String statementid, Object... params) {

        List<Object> objects = selectList(statementid,params);

        if(objects.size() == 1){
            return (T)objects.get(0);
        }else{
            throw new RuntimeException("检查结果为空或者返回结果过多");
        }

    }
}
