package com.itheima.case2.web;

import com.alibaba.fastjson.JSON;
import com.itheima.case2.pojo.po.Role;
import com.itheima.case2.service.RoleService;
import com.itheima.case2.utils.BeanFactory;
import com.itheima.case2.web.base.BaseServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 此Servlet用于处理角色模块的所有功能
 */
@WebServlet("/role/*")
public class RoleServlet extends BaseServlet {

    /**
     * 查询所有角色信息
     */
    public void findAll(HttpServletRequest request,HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        //System.out.println("findAll方法执行了");
//        RoleService service = new RoleService();
        RoleService service = (RoleService)BeanFactory.getBean("RoleService");
        List<Role> list = service.findAll();
        // 将查询结果转成json写回给浏览器
        String listjson = JSON.toJSONString(list);
        System.out.println(listjson);
        response.getWriter().print(listjson);
//        BaseController.printResult(response,list);
    }

//    /**
//     * @param request  : 请求信息对象,封装了浏览器携带给服务器的所有请求数据
//     * @param response : 响应信息对象,封装了服务器返回给浏览器的所有数据
//     * @throws ServletException
//     * @throws IOException
//     */
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
        System.out.println("查询角色信息");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("删除角色信息");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("修改角色信息");
    }

    public void insert(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("添加角色信息");
    }

//    /**
//     * @param request  : 请求信息对象,封装了浏览器携带给服务器的所有请求数据
//     * @param response : 响应信息对象,封装了服务器返回给浏览器的所有数据
//     * @throws ServletException
//     * @throws IOException
//     */
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
//    }
}
