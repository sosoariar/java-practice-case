package com.soso.sqlSession;

import java.util.List;

public interface SqlSession {

    public <E> List<E> selectList(String statementid,Object ... params);

    public <T> T selectOne(String statementid,Object ... params);
}
