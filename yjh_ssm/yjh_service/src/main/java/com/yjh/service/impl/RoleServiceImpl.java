package com.yjh.service.impl;

import com.yjh.dao.RoleDao;
import com.yjh.pojo.Permission;
import com.yjh.pojo.Role;
import com.yjh.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {


    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();

    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId) {
        return roleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(String roleid, String[] permissionids) {
        for (String permissionid : permissionids) {
            roleDao.addPermissionToRole(roleid,permissionid);
        }
    }
}
