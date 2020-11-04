package com.yjh.controller;

import com.github.pagehelper.PageInfo;
import com.yjh.pojo.Orders;
import com.yjh.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //查询全部订单
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView  findAll(@RequestParam(name="page",required = true,defaultValue = "1") Integer page,@RequestParam(name="pageSize",required = true,defaultValue = "4")Integer pageSize)throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        List<Orders> orders = ordersService.findAll(page,pageSize);
        PageInfo pageInfo=new PageInfo(orders);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }


    //查询订单详情
    @RequestMapping("/findById")
    @Secured("ROLE_ADMIN")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String orderId)throws Exception{
        ModelAndView mv=new ModelAndView();
        Orders orders=ordersService.findById(orderId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
