package com.itheima.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo2d")
public class Demo2Servlet extends HttpServlet {
    /**
     * @param request  : 请求信息对象,封装了浏览器携带给服务器的所有请求数据
     * @param response : 响应信息对象,封装了服务器返回给浏览器的所有数据
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = request.getCookies();
        int count = 0 ;
        if (cookies!=null&&cookies.length!=0){
            for (Cookie cookie : cookies) {
                if ("history".equals(cookie.getName())){
                    response.getWriter().print(cookie.getValue());
                    cookie = new Cookie("history", "锤子手机");
                    response.addCookie(cookie);
                    count++;
                    break;
                }
            }
            if(count==0){
                Cookie cookie = new Cookie("history", "锤子手机");
                response.addCookie(cookie);
            }
        }else {
            Cookie cookie = new Cookie("history", "锤子手机");
            response.addCookie(cookie);
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
