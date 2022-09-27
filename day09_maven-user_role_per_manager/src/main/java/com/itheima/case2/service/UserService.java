package com.itheima.case2.service;

import com.itheima.case2.pojo.vo.AddUser;
import com.itheima.case2.pojo.vo.PageResult;
import com.itheima.case2.pojo.vo.UpdateUser;

public interface UserService {
    PageResult findByPage(int currentPage, int pageSize);

    void addUser(AddUser addUser) ;

    /**
     * 根据Id删除用户信息
     * @param id
     */
    void deleteUserById(String id);

    /**
     * 修改用户信息
     * @param updateUser
     */
    void updateUser(UpdateUser updateUser);
}
