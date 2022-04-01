package com.soso.sqlSession;

import com.soso.pojo.Configuration;
import com.soso.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    private Executor simpleExecutor = new SimpleExecutor();

    @Override
    public <E> List<E> selectList(String statementid, Object... params) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        List<E>  query = SimpleExecutor.query(configuration,mappedStatement,params);

        return query;
    }

    @Override
    public <T> T selectOne(String statementid, Object... params) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        List<Object> objects = selectList(statementid,params);

        if(objects.size() == 1){
            return (T)objects.get(0);
        }else{
            throw new RuntimeException("检查结果为空或者返回结果过多");
        }

    }
}
