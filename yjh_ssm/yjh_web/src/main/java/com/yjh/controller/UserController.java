package com.yjh.controller;


import com.yjh.pojo.Role;
import com.yjh.pojo.UserInfo;
import com.yjh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @RequestMapping("/findAll")
    //@Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView findAll(){
        ModelAndView modelAndView=new ModelAndView();
        List<UserInfo> userInfoList=userService.findAll();
        modelAndView.addObject("userList",userInfoList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById")
    @PreAuthorize("authentication.principal.username=='cctv5'")
    public ModelAndView findById(String id){
        UserInfo userInfo=userService.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndOtherRoles(@RequestParam(name="id",required = true)String userid){
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo = userService.findById(userid);
        mv.addObject("user",userInfo);
        //根据用户id查询出用户没有的角色
        List<Role> roleList=userService.findOtherRoles(userid);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name="ids",required = true)String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}
