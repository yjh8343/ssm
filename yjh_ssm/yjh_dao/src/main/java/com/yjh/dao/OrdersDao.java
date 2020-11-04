package com.yjh.dao;

import com.yjh.pojo.Member;
import com.yjh.pojo.Orders;
import com.yjh.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one=@One(select = "com.yjh.dao.ProductDao.findById")),
    })
    List<Orders> findAll() throws Exception;


    @Select("select * from orders where id=#{orderId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one=@One(select = "com.yjh.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one=@One(select = "com.yjh.dao.MemberDao.findByMemberId")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many=@Many(select = "com.yjh.dao.TravellerDao.findByOrderId")),
    })
    Orders findByOrderId(String orderId)throws Exception;
}
