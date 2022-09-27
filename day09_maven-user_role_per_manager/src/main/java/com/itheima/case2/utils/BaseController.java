package com.itheima.case2.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
*   FastJson
* */
public class BaseController {
    /**
     * 将返回值对象转成json并写回给浏览器
     * @param response
     * @param obj
     * @throws IOException
     */
    public static void printResult(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json;charset=utf-8");// 设置返回类型
        // 将obj转成json字符串,使用响应输出流将转完后的json字符串写回给浏览器
        JSON.writeJSONString(response.getWriter(),obj);
//        String jsonStr = JSON.toJSONString(obj);
//        response.getWriter().print(jsonStr);
    }

    /**
     * 解析请求携带的json数据,并将数据封装到java对象中
     * @param request
     * @param tClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public static  <T> T parseJSON2Object(HttpServletRequest request, Class<T> tClass) throws IOException{
        // 把json格式的表单数据直接转成T类型的对象
        return JSON.parseObject(request.getInputStream(),tClass);
    }
}



















