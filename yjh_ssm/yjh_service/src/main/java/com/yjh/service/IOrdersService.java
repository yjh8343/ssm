package com.yjh.service;

import com.yjh.pojo.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page,int pageSize) throws Exception;

    Orders findById(String orderId)throws Exception;
}
