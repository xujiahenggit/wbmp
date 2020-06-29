package com.bank.core.utils;

import cn.hutool.core.util.StrUtil;
import org.springframework.core.env.Environment;

/**
 * 获取配置文件中配置的值
 */
public class YmlUtil {

    public static String getValue(String key){
        Environment env = (Environment) ApplicationContextUtil.getBeanByClass(Environment.class);
        String value = env.getProperty(key);
        if (StrUtil.isBlankIfStr(value)){
                return value;
        }else {
            return "";
        }
    }


}
