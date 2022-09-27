package com.itheima.case2.dao;

import com.itheima.case2.pojo.po.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {


    @Select("select * from t_role")
    List<Role> findAll();
}
