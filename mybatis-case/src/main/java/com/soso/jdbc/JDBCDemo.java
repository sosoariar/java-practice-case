package com.soso.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCDemo {

    public static void main(String[] args) throws Exception {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding-utf-8","root","123456");
        String sql = "select * from user";
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            System.out.println(resultSet.getInt("id") + " " + resultSet.getString("username"));
        }

    }

}
