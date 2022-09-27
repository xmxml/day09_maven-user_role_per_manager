package com.itheima.case2.web;

import com.itheima.case2.pojo.vo.AddUser;
import com.itheima.case2.pojo.vo.PageResult;
import com.itheima.case2.pojo.vo.Result;
import com.itheima.case2.pojo.vo.UpdateUser;
import com.itheima.case2.service.UserService;
import com.itheima.case2.utils.BaseController;
import com.itheima.case2.utils.BeanFactory;
import com.itheima.case2.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 此Servlet用于处理用户模块的所有功能
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    // 定义全局变量
    private UserService service = (UserService) BeanFactory.getBean("UserService");
    /**
     * 分页查询用户信息
     * @param request
     * @param response
     */
    public void findByPage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
//        String currentPage = request.getParameter("currentPage");
//        String pageSize = request.getParameter("pageSize");
            //1.获取请求参数
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));
            System.out.println(currentPage+" : "+pageSize);
            //2.调用service处理业务
            //UserService service = new UserService();
            // 将分页的查询结果封装到PageResult对象中(总条数,当前页的数据信息)
            PageResult pageResult =  service.findByPage(currentPage,pageSize);
            //3.封装前端需要的结果数据
            Result result = new Result(true,"获取用户列表成功",pageResult);
            //4.将result转成json字符串并写回给浏览器
            BaseController.printResult(response,result);
        } catch (Exception e) {
            Result result = new Result(false,"服务器忙...",null);
            BaseController.printResult(response,result);
            e.printStackTrace();
        }

    }

    /**
     * 添加用户信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void addUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            //1.获取请求参数
            //AddUser addUser = JSON.parseObject(request.getInputStream(), AddUser.class);
            AddUser addUser = BaseController.parseJSON2Object(request, AddUser.class);
            System.out.println(addUser);
            //2.调用service处理业务
//            UserService service = new UserService();
            service.addUser(addUser);
            //3.生成响应结果
            Result result = new Result(true, "添加成功", null);
            BaseController.printResult(response,result);
        } catch (Exception e) {
            Result result = new Result(false, "服务器忙,请稍后重试", null);
            BaseController.printResult(response,result);
            e.printStackTrace();
        }

    }

    /**
     * 根据Id删除用户信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteUserById(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            //1.获取请求携带的id值
            String id = request.getParameter("id");
            //2.调用service处理业务
            //UserService service = new UserService();
            service.deleteUserById(id);
            //3.删除成功,生成响应信息
            Result result = new Result(true, "删除成功", null);
            BaseController.printResult(response,result);
        } catch (Exception e) {Result result = new Result(false, "服务器忙.删除失败", null);
            BaseController.printResult(response,result);
            e.printStackTrace();
        }
    }

    /**
     * 修改用户信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void updateUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            //1.获取请求携带的参数
            UpdateUser updateUser = BaseController.parseJSON2Object(request, UpdateUser.class);
            System.out.println(updateUser);
            //2.调用service处理业务逻辑
            //UserService service = new UserService();
            service.updateUser(updateUser);
            //3.判断执行结果,生成响应
            Result result = new Result(true,"修改成功",null);
            BaseController.printResult(response,result);
        } catch (IOException e) {
            Result result = new Result(false,"服务器忙..修改失败",null);
            BaseController.printResult(response,result);
            e.printStackTrace();
        }

    }
    /**
     * @param request  : 请求信息对象,封装了浏览器携带给服务器的所有请求数据
     * @param response : 响应信息对象,封装了服务器返回给浏览器的所有数据
     * @throws ServletException
     * @throws IOException
     */
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=utf-8");
//        //1.获取请求路径
//        String url = request.getRequestURI();
//        System.out.println(url);
//        //2.截取请求路径的最后一个标记
//        //获取最后一个/出现的索引
//        int lastIndex = url.lastIndexOf('/');
//        //从指定索引位置截取到末尾
//        String methodName = url.substring(lastIndex + 1);
//        System.out.println(methodName);
//        //3.判断请求标记,调用对应的方法执行
//        if ("insert".equals(methodName)){
//            insert(request,response);
//        }else if("update".equals(methodName)){
//            update(request,response);
//        }else if("delete".equals(methodName)){
//            delete(request,response);
//        }else if("select".equals(methodName)){
//            select(request,response);
//        }else {
//            System.out.println("您访问的路径不存在");
//            response.getWriter().print("您访问的路径不存在");
//        }
//    }

    public void select(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("查询用户信息");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("删除用户信息");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("修改用户信息");
    }

    public void insert(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("添加用户信息");
    }

    /**
     * @param request  : 请求信息对象,封装了浏览器携带给服务器的所有请求数据
     * @param response : 响应信息对象,封装了服务器返回给浏览器的所有数据
     * @throws ServletException
     * @throws IOException
     */
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
//    }
}
