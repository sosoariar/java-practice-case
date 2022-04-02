package com.soso.mapper;

import com.soso.bean.Order;

import java.util.List;

public interface IUserMapper {

    // 查询订单及用户
    public List<Order> findOrderAndUser();

}
