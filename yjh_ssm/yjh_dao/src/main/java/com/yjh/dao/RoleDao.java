package com.yjh.dao;

import com.yjh.pojo.Permission;
import com.yjh.pojo.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    @Select("select * from role where id in (select roleid from users_role where userid=#{userid})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.yjh.dao.PermissionDao.findByRoleId"))
    })
    List<Role> findRoleByUserId(String userid);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(id,rolename,roledesc) values(role_seq.nextval,#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from permission where id not in(select permissionid from role_permission where roleid=#{roleId})")
    List<Permission> findOtherPermissions(String roleId);


    @Insert("insert into role_permission(roleid,permissionid)values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId")String roleid, @Param("permissionId")String permissionid);
}
