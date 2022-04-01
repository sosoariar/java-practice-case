package com.soso.sqlSession;

import com.soso.config.BoundSql;
import com.soso.pojo.Configuration;
import com.soso.pojo.MappedStatement;
import com.soso.utils.GenericTokenParser;
import com.soso.utils.ParameterMapping;
import com.soso.utils.ParameterMappingTokenHandler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.List;

public class SimpleExecutor implements Executor{

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException {
        // 注册驱动,获取链接
        Connection connection = configuration.getDataSource().getConnection();
        // 获取SQL语句: select * from user where id = #{id} and username = #{username}
        String sql = mappedStatement.getSql();
        // 转换SQL语句: SELECT * FROM USER WHERE id = ? and username = ?
        BoundSql boundSql = getBoundSql(sql);
        // 获取预处理对象: preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        String parameterType = mappedStatement.getParameterType();
        Class<?> parametertypeClass = getClassType(parameterType);
        
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for(int i=0; i<parameterMappingList.size();i++){
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            // 反射
            Field declaredField = parametertypeClass.getDeclaredField(content);
            // 暴力访问私有变量
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);

            preparedStatement.setObject(i+1,o);
        }

        // 执行SQL
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType)

        // 封装返回结果集
        while(resultSet.next()){
            // 元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for(int i=1; i<=metaData.getColumnCount();i++){
                // 字段名
                String columnName = metaData.getColumnName(i);
                // 字段的值
                Object object = resultSet.getObject(columnName);
                // 使用反射,根据数据库表和实体的对应关系,完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName,resultTypeClass);
                Method writMethod = propertyDescriptor.getWriteMethod();
                writMethod.invoke(o,value);
            }
        }

        return null;
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if(parameterType!=null){
            Class<?> clazz = Class.forName(parameterType);
            return clazz;
        }
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
