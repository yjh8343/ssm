package com.yjh.controller;



import com.yjh.pojo.Permission;
import com.yjh.pojo.Role;
import com.yjh.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {


    @Autowired
    private IRoleService roleService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Role> roleList=roleService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name="id",required = true)String roleId){
        ModelAndView mv=new ModelAndView();
        mv.addObject("roleId",roleId);
        List<Permission> permissionList=roleService.findOtherPermissions(roleId);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name="roleId",required = true)String roleid,@RequestParam(name = "ids",required = true)String[] permissionids){
        roleService.addPermissionToRole(roleid,permissionids);
        return "redirect:findAll.do";
    }
}
