package com.itheima.case2.service.impl;

import com.itheima.case2.dao.UserMapper;
import com.itheima.case2.pojo.po.User;
import com.itheima.case2.pojo.vo.AddUser;
import com.itheima.case2.pojo.vo.PageResult;
import com.itheima.case2.pojo.vo.UpdateUser;
import com.itheima.case2.service.UserService;
import com.itheima.case2.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {
    public PageResult findByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = SqlSessionUtil.getSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //1.查询总条数
        long total = userMapper.getTotal();
        //2.查询当前页的数据信息
        //计算起始索引
        int startIndex = (currentPage-1)*pageSize;
        // 调用dao层的方法执行
        List<User> list = userMapper.findByPage(startIndex,pageSize);
        SqlSessionUtil.commitAndClose(sqlSession);
        //3.封装分页数据信息
        return new PageResult(total,list);
    }

    public void addUser(AddUser addUser) {
        SqlSession sqlSession = SqlSessionUtil.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //1.添加用户基本信息,需要返回主键Id值
        // vo: 与业务相关的实体类对象. addUser,与添加业务相关的对象
        // po: 与数据库表对应的实体类对象
        User user = new User();
        user.setEmail(addUser.getEmail());
        user.setUsername(addUser.getUsername());
        user.setPassword(addUser.getPassword());
        user.setRemark(addUser.getRemark());
        int i = mapper.addUser(user);
        System.out.println(i);
        //2.往中间表中添加信息
        // 给用户添加角色
        // 方式1: 在java代码中遍历
//        List<String> roleIds = addUser.getRoleIds();
//        if (roleIds!=null && roleIds.size()>0){
//            for (String roleId : roleIds) {
//                mapper.addUserRole(user.getId(),roleId);
//            }
//        }
        // 方式2: 动态sql
        mapper.addUserRole1(user.getId(),addUser.getRoleIds());
        SqlSessionUtil.commitAndClose(sqlSession);
    }

    /**
     * 根据Id删除用户信息
     * @param id
     */
    public void deleteUserById(String id) {
        SqlSession sqlSession = SqlSessionUtil.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //1.先删除中间表中的数据
        mapper.deletUserRole(id);
        //2.再删除用户信息
        mapper.deleteUserById(id);
        SqlSessionUtil.commitAndClose(sqlSession);
    }

    /**
     * 修改用户信息
     * @param updateUser
     */
    public void updateUser(UpdateUser updateUser) {
        SqlSession sqlSession = SqlSessionUtil.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //1.修改用户的基本信息
        User user = new User();
        user.setId(updateUser.getId()+"");
        user.setEmail(updateUser.getEmail());
        user.setUsername(updateUser.getUsername());
        user.setPassword(updateUser.getPassword());
        user.setRemark(updateUser.getRemark());
        mapper.updateUser(user);
        //2.修改角色信息
        //2.1 先删除当前用户的角色
        mapper.deletUserRole(updateUser.getId()+"");
        //2.2 添加新的角色
        mapper.addUserRole1(updateUser.getId()+"",updateUser.getRoleIds());
        SqlSessionUtil.commitAndClose(sqlSession);
    }
}
