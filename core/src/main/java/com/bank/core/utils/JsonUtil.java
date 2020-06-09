package com.bank.core.utils;

import com.bank.core.entity.BizException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * author:zhaozhongyuan
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String str2JsonStr(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new BizException("转json报错");
        }
    }


}