package com.soso.bean;

import com.soso.io.Resources;
import com.soso.sqlSession.SqlSession;
import com.soso.sqlSession.SqlSessionFactory;
import com.soso.sqlSession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class UserDaoImpl implements IUserMapper{

    @Override
    public List<User> findAll() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> users = sqlSession.selectList("user.selectList");
        for(User user1:users){
            System.out.println(user1);
        }
        return users;
    }

    @Override
    public User findByCondition(User user) throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user2 = sqlSession.selectOne("user.selectOne",user);
        System.out.println(user2);
        return null;
    }

}
