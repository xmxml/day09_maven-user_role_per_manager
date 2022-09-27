package com.itheima;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/datatime")
public class Demo3Servlet extends HttpServlet {
    /**
     * @param request  : 请求信息对象,封装了浏览器携带给服务器的所有请求数据
     * @param response : 响应信息对象,封装了服务器返回给浏览器的所有数据
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = request.getCookies();
        int count = 0;
        if (cookies!=null&&cookies.length!=0){
            for (Cookie cookie : cookies) {
                if ("LastTime".equals(cookie.getName())){
                    String value = cookie.getValue();
                    String decode = URLDecoder.decode(value, "utf-8");
                    response.getWriter().print("您上次访问的时间是："+decode);
                    System.out.println(decode);
                    count++;
                    break;
                }
            }
            if (count==0){
                response.getWriter().print("欢迎您第一次光临");
            }
        }else {
            response.getWriter().print("欢迎您第一次光临");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
        String date = dateFormat.format(new Date());
        String s = URLEncoder.encode(date,"utf-8");
        Cookie cookie = new Cookie("LastTime",s);
        response.addCookie(cookie);
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
