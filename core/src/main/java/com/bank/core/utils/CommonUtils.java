package com.bank.core.utils;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/14 15:29
 */
public class CommonUtils {

    /**
     * 列表 转字符串
     * @param list 列表
     * @param splitChar 分割符
     * @return
     */
    public static String listToArray(List<Object> list,String splitChar){
        String r_str="";
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                r_str=r_str+list.get(i).toString()+splitChar;
            }
        }
        //取掉最后的 都好
        r_str=r_str.substring(0,r_str.length()-1);
        return r_str;
    }
}
