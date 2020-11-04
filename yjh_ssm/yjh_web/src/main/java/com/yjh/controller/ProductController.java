package com.yjh.controller;


import com.yjh.pojo.Product;
import com.yjh.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Product> list = productService.findAll();
        mv.addObject("productList",list);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Product product)throws Exception{
        productService.save(product);
        return "redirect:findAll.do";
    }
}
