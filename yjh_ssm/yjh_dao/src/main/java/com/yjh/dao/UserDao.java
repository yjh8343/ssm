package com.yjh.dao;

import com.yjh.pojo.Role;
import com.yjh.pojo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {

    //根据用户名查询用户
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select="com.yjh.dao.RoleDao.findRoleByUserId")),
    })
    UserInfo findByUsername(String username);


    @Select("select * from users")
    List<UserInfo> findAll();


    @Insert("insert into users(username,password,email,phoneNum,status)values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);


    @Select("select * from users where id=#{userid}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.yjh.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findByUserId(String userid);


    @Select("select * from role where id not in(select roleid from users_role where userid=#{userid})")
    List<Role> findOtherRoles(String userid);

    @Insert("insert into users_role(userid,roleid) values(#{userIds},#{roleIds})")
    void addRoleToUser(@Param("userIds") String userId,@Param("roleIds") String roleId);
}
