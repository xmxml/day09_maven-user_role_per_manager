package com.itheima;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookida")
public class Demo2Servlet extends HttpServlet {
    /**
     * @param request  : 请求信息对象,封装了浏览器携带给服务器的所有请求数据
     * @param response : 响应信息对象,封装了服务器返回给浏览器的所有数据
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println(name+ ":"+value);
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
