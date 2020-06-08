package com.bank.core.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Author: Andy
 * @Date: 2020/4/9 17:33
 */
public class Md5Utils {
    /**
     * MD5 加密
     * @param crdentials 明文
     * @return
     */
    public static String getEnCode(String crdentials ){
        //加密方式
        String hashAlgorithmName = Md5Hash.ALGORITHM_NAME;
        //盐值
        Object salt = null;
        //加密1024次
        int hashIterations = 32;
        Object result = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
        return result.toString();
    }
}
