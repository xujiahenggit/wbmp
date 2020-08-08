package com.bank.core.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 应用连接服务器/数据库加解密码机制 长行密码加密解密工具
 * ClassName: ThreeDes
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/20 19:04:38
 */
public class ThreeDes {

    private static final String ALGORITHM = "DESede";

    private static final String KEY = "CS8KDsJ36/6DES/ADLJuhkl23236SD+A";

    public static final String TAG = "encrypt:";

    private static byte[] encrypt(byte[] keybyte, byte[] src) {
        try {
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(1, new SecretKeySpec(keybyte, "DESede"));
            return c1.doFinal(src);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decrypt(byte[] keybyte, byte[] src) {
        try {
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(2, new SecretKeySpec(keybyte, "DESede"));
            return c1.doFinal(src);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String enPassword(String password) {
        try {
            byte[] keyBytes = Base64.base64StringToByteArray("CS8KDsJ36/6DES/ADLJuhkl23236SD+A");
            byte[] encBytes = encrypt(keyBytes, password.getBytes());
            return Base64.byteArrayToBase64String(encBytes);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("长行加密工具加密失败，失败原因：" + e.getMessage());
        }
    }

    public static String deTagPassword(String encPassword) {
        if (encPassword.indexOf("encrypt:") == 0) {
            encPassword = encPassword.replace("encrypt:", "");
            return dePassword(encPassword);
        }
        return encPassword;
    }

    public static String dePassword(String encPassword) {
        try {
            byte[] keyBytes = Base64.base64StringToByteArray("CS8KDsJ36/6DES/ADLJuhkl23236SD+A");
            byte[] encSrcBytes = Base64.base64StringToByteArray(encPassword);
            byte[] decBytes = decrypt(keyBytes, encSrcBytes);
            return new String(decBytes);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("长行加密工具j解密失败，失败原因：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("输入加解密的密钥,按Enter键或者输入end字符则结束加密");
            Scanner sc = new Scanner(System.in);

            PrintWriter pw = new PrintWriter(new FileOutputStream("password.ini"));
            for (;;) {
                System.out.print("请输入要加密的字符串：");
                String src = sc.nextLine();
                if (("".equals(src)) || ("end".equals(src))) {
                    System.out.println("已结束");
                    break;
                }
                System.out.println("加密前的字符串:" + src);
                String enc = enPassword(src);
                System.out.println("加密后的字符串:" + enc);
                String dec = dePassword(enc);
                System.out.println("解密后的字符串:" + dec);
                String tagEnc = "encrypt:".concat(enc);
                System.out.println("加密后的字符串(加标签):" + tagEnc);
                dec = deTagPassword(tagEnc);
                System.out.println("解密后的字符串(加标签):" + dec);

                pw.println("加密前：" + src + ",加密后：" + enc + ",加标签密文：" + tagEnc);
            }
            pw.flush();
            pw.close();
        }
        catch (Exception e) {
            System.out.println("解密失败");
            e.printStackTrace();
        }
    }
}