package com.yjh.dao;

import com.yjh.pojo.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    @Select("select * from permission where id in(select permissionid from role_permission where roleid=#{roleId})")
    List<Permission> findByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(id,permissionName,url) values(role_seq.nextval,#{permissionName},#{url})")
    void save(Permission permission);
}
