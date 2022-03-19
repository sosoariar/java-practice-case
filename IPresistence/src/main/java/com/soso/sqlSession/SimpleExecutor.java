package com.soso.sqlSession;

import com.soso.config.BoundSql;
import com.soso.pojo.Configuration;
import com.soso.pojo.MappedStatement;
import com.soso.utils.GenericTokenParser;
import com.soso.utils.ParameterMapping;
import com.soso.utils.ParameterMappingTokenHandler;
import com.soso.utils.TokenHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SimpleExecutor implements Executor{

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException {

        Connection connection = configuration.getDataSource().getConnection();
        String sql = mappedStatement.getSql();

        BoundSql boundSql = getBoundSql(sql);

        return null;
    }

    private BoundSql getBoundSql(String sql) {

        // 占位符处理
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{","}",parameterMappingTokenHandler);

        String parse = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();

        BoundSql boundSql = new BoundSql(parse,parameterMappings);

        return boundSql;
    }


}
