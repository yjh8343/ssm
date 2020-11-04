package com.yjh.service.impl;

import com.yjh.dao.UserDao;
import com.yjh.pojo.Role;
import com.yjh.pojo.UserInfo;
import com.yjh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> rolesList) {
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role : rolesList) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }


    //查询全部用户
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    //查询用户角色和权限
    @Override
    public UserInfo findById(String id) {
        return userDao.findByUserId(id);
    }

    @Override
    public List<Role> findOtherRoles(String userid) {
        return userDao.findOtherRoles(userid);

    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId: roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
