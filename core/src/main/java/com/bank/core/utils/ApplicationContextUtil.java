package com.bank.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: Andy
 * @Date: 2020/4/7 18:53
 */
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        context = arg0;
    }
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }
    public static <T> Object getBeanByClass(Class<T> c){
        return context.getBean(c);
    }
}
