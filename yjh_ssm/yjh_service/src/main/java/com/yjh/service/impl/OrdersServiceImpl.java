package com.yjh.service.impl;

import com.github.pagehelper.PageHelper;
import com.yjh.dao.OrdersDao;
import com.yjh.pojo.Orders;
import com.yjh.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private OrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page,int pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String orderId)throws Exception {
        return ordersDao.findByOrderId(orderId);
    }
}
