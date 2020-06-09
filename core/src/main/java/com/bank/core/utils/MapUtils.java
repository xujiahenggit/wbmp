package com.bank.core.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: Andy
 * @Date: 2020/4/27 9:01
 */
public class MapUtils {


    public static boolean isEmpty(Map<String,Object> map) {
        if (MapUtil.isEmpty(map)) {
            return true;
        }
        for (Map.Entry entry : map.entrySet()) {
           if (!entry.getValue().equals("")){
                return false;
           }
        }
        return true;
    }

    public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    public static Map<String, Object> removeEmptyVal(Map<String, Object> map) {
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            Object value = entry.getValue();
            if (StrUtil.isBlankIfStr(value)){
                iterator.remove();
                map.remove(entry.getKey());
            }
        }
        return map;
    }
}
