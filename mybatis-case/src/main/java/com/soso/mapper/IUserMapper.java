package com.soso.mapper;

import com.soso.bean.Order;
import com.soso.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IUserMapper {

    public List<Order> findOrderAndUser();

    // 方法的参数提供了SQL的入参, 注解提供了执行SQL,相当于xml的配置, #{} 提供了标识,用来识别参数
    @Insert("insert into user values(#{id},#{username})")
    public void addUser(User user);

    @Update("update user set username = #{username} where id = #{id}")
    public void updateUser(User user);

    @Select("select * from user")
    public List<User> selectUser();

    @Delete("delete from user where id = #{id}")
    public void deleteUser(Integer id);

}
