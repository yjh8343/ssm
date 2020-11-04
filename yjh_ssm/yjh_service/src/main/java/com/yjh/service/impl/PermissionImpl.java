package com.yjh.service.impl;

import com.yjh.dao.PermissionDao;
import com.yjh.pojo.Permission;
import com.yjh.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionImpl implements IPermissionService {

    @Autowired
    private PermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
       return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
