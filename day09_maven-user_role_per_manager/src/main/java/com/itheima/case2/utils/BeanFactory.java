package com.itheima.case2.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class BeanFactory {
    // 创建一个map容器,用于存放解析完的配置信息
    private static Map<String,String> beanDefinition = new HashMap<>();
    // 创建一个map容器,用于存放创建好的bean对象
    // 键: id,配置文件中key
    // 值: 创建的bean对象
    private static Map<String,Object> singleBeanMap = new HashMap<>();
    static {
        // 解析配置文件,获取需要创建的类对象的全限定名
        // ResourceBundle: 专门用于解析properties配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("beans");
        // 解析配置文件,获取配置文件中所有的key
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()){
            // 获取key的值
            String key =  keys.nextElement();
            // 获取value的值: 类的全限定名
            String value = bundle.getString(key);
            System.out.println(key+" : "+value);
            beanDefinition.put(key,value);
        }
    }

    /**
     * 根据唯一标记ID获取创建的bean对象(java类对象)
     * @param id
     * @return
     */
    public static Object getBean(String id) {
        Object obj = null;
        try {
            // 先从singleBeanMap集合中获取bean对象
            obj = singleBeanMap.get(id);
            if (obj==null){
                // 根据id获取对应的类的全限定名
                String classPath = beanDefinition.get(id);
                // 反射创建bean对象
                obj = Class.forName(classPath).newInstance();
                // 将创建的bean对象存放到singleBeanMap容器中,便于下次使用
                singleBeanMap.put(id,obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
