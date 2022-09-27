package com.itheima.case2.dao;


import com.itheima.case2.pojo.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("select count(*) from t_user")
    int getTotal();

    //@Select("select * from t_user limit #{startIndex},#{pageSize}")
    List<User> findByPage(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);

    @Insert("insert into t_user (username,password,remark,email) values (#{username},#{password},#{remark},#{email})")
    @Options(useGeneratedKeys=true,keyColumn = "id",keyProperty = "id")
    int addUser(User user);

    @Insert("insert into t_user_role values(#{userId},#{roleId})")
    int addUserRole(@Param("userId") String userId,@Param("roleId") String roleId);

    void addUserRole1(@Param("userId") String userId,@Param("roleIds") List<String> roleIds);

    @Delete("delete from t_user_role where user_id = #{id}")
    void deletUserRole(String id);

    @Delete("delete from t_user where id = #{id}")
    void deleteUserById(String id);

    @Update("update t_user set username=#{username},password=#{password},remark=#{remark},email=#{email} where id=#{id}")
    void updateUser(User user);
}
