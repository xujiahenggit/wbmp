package com.bank.quartz.utils;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/2 10:25
 */
public enum  StringUtil {
    ;
    //获取List参数值
    public static String getListString(List<String> list) {
        StringBuilder result = new StringBuilder();
        for (String s : list) {
            result.append(s).append(" ");
        }
        return result.toString();
    }
}
