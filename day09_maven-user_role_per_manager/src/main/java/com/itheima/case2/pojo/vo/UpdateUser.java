package com.itheima.case2.pojo.vo;

import java.io.Serializable;
import java.util.List;

/*
* 对应前端发送修改用户数据的JavaBean
* */
public class UpdateUser implements Serializable {
    private Integer id; //要修改的用户id
    private String username; //用户名称
    private String password;//密码
    private String email; //邮箱
    private String remark; //备注
    private List<String> roleIds; //此用户对应的多个角色id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
