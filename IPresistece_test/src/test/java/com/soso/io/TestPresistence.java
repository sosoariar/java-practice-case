package com.soso.io;

import com.soso.bean.User;
import com.soso.sqlSession.SqlSession;
import com.soso.sqlSession.SqlSessionFactory;
import com.soso.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.InputStream;

public class TestPresistence {

    @Test
    public void test() throws Exception {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user01 = new User();
        user01.setId(1);
        user01.setUsername("zhangsan");

        User user02 = sqlSession.selectOne("user.selectOne",user01);

    }

}
