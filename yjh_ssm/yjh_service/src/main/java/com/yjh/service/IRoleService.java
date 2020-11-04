package com.yjh.service;


import com.yjh.pojo.Permission;
import com.yjh.pojo.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    void save(Role role);

    List<Permission> findOtherPermissions(String roleId);

    void addPermissionToRole(String roleid, String[] permissionids);
}
