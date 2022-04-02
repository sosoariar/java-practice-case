package com.soso.mapper;

import com.soso.bean.Order;

public interface IUserMapper {

    // 查询订单及用户
    public Order findOrderAndUser();

}
