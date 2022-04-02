package com.soso;

import com.soso.bean.Order;
import com.soso.bean.User;
import com.soso.mapper.IUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    private SqlSession sqlSession = null;
    private IUserMapper userMapper = null;

    @Test
    public void selectUser() throws IOException {

        List<User> users = sqlSession.selectList("user.findAll");
        for(User user:users){
            Tools.log.info(user);
        }
        sqlSession.close();
    }

    @Test
    public void insertUser() throws IOException {

        User user = new User();
        user.setId(5);
        user.setUsername("jerry");

        sqlSession.insert("user.insertUser",user);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void updateUser() throws IOException {

        User user = new User();
        user.setId(5);
        user.setUsername("jerry");

        sqlSession.update("user.updateUser",user);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void deleteUser() throws IOException {

        sqlSession.delete("user.deleteUser",5);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void selectByCondition() throws IOException {

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);

        User userTemp = new User();
        userTemp.setUsername("tom");

        List<User> all = mapper.findByCondition(userTemp);

        for(User user:all){
            Tools.log.info(user);
        }

    }

    @Test
    public void selectByCollection() throws IOException {

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);

        int[] arr = {1,2};

        List<User> all = mapper.findByIds(arr);

        for(User user:all){
            Tools.log.info(user);
        }

    }

    @Test
    public void selectByOrder() throws IOException{


        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

        List<Order> orderAndUser = mapper.findOrderAndUser();
        for(Order order:orderAndUser){
            Tools.log.info(order);
        }

    }

    @Test
    public void addUser(){

        User user = new User();

        for(int i=11; i<20; i++){
            user.setId(i);
            user.setUsername("mybatis"+i);
            userMapper.addUser(user);
        }

    }

    @Test
    public void updateUserByAnnotation(){
        User user = new User();
        for(int i=1; i<10; i++){
            user.setId(i);
            user.setUsername("mybatis"+i);
            userMapper.updateUser(user);
        }
    }

    @Before
    public void before() throws IOException{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession(true);
        userMapper = sqlSession.getMapper(IUserMapper.class);
    }

}
