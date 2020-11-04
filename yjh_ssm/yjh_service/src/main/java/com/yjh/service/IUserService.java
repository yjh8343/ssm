package com.yjh.service;

import com.yjh.pojo.Role;
import com.yjh.pojo.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findOtherRoles(String userid);

    void addRoleToUser(String userId, String[] roleIds);
}
