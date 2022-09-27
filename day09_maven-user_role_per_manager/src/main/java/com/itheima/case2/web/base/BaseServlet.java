package com.itheima.case2.web.base;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {
    /**
     * @param request  : 请求信息对象,封装了浏览器携带给服务器的所有请求数据
     * @param response : 响应信息对象,封装了服务器返回给浏览器的所有数据
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.截取请求路径中的标记
            String url = request.getRequestURI();
            //获取最后一个/出现的索引
            int lastIndex = url.lastIndexOf('/');
            //从指定索引位置截取到末尾
            String methodName = url.substring(lastIndex + 1);
            //2.获取调用者对象的字节码
            Class clazz =  this.getClass();
            System.out.println(this);
            //3.获取对应方法的字节码
            Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //4.反射调用方法执行
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param request  : 请求信息对象,封装了浏览器携带给服务器的所有请求数据
     * @param response : 响应信息对象,封装了服务器返回给浏览器的所有数据
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
