package com.bank.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 操作数据源
 * @author zzy
 */
@Slf4j
@Component
public class DynamicDataSourceSwitcher {


    public static final String Mater = "master";
    public static final String Slave = "esb";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String name){
        log.info("-------- 设置数据源数据源为 ：{} ", name);
        contextHolder.set(name);
    }

    public static String getDataSource(){
        return contextHolder.get();
    }

    public static void cleanDataSource(){
        contextHolder.remove();
    }

}
