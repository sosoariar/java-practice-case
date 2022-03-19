package com.soso.sqlSession;

import com.soso.pojo.Configuration;
import com.soso.pojo.MappedStatement;
import java.util.List;

public interface Executor {
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object ... params);
}
