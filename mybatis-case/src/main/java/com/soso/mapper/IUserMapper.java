package com.soso.mapper;

import com.soso.bean.Order;
import com.soso.bean.User;
import org.apache.ibatis.annotations.*;

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

    //查询所有用户、同时查询每个用户关联的订单信息
    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "orderList",column = "id",javaType = List.class,
                    many=@Many(select = "com.soso.mapper.IOrderMapper.findOrderByUid"))
    })

    public List<User> findAll();

}
