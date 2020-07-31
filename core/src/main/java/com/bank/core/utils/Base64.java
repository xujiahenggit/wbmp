package com.bank.core.utils;

/**
 * 应用连接服务器/数据库加解密码机制
 * ClassName: Base64
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/20 19:14:05
 */
public class Base64 {

    private static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String byteArraytoHexString(byte[] b) {
        String res = "";
        for (int i = 0; i < b.length; i++) {
            res = res + byteToHexString(b[i]);
        }
        return res;
    }

    public static String byteArrayToBase64String(byte[] b, int len) {
        String s = "";
        int n = len / 3;
        int m = len % 3;
        for (int i = 0; i < n; i++) {
            int j = i * 3;
            s = s + toBase64(b[j], b[(j + 1)], b[(j + 2)]);
        }
        if (m == 1) {
            s = s + toBase64(b[(len - 1)]);
        }
        else if (m == 2) {
            s = s + toBase64(b[(len - 2)], b[(len - 1)]);
        }
        String result = "";
        int slen = s.length();
        n = slen / 64;
        m = slen % 64;
        for (int i = 0; i < n; i++) {
            result = result + s.substring(i * 64, (i + 1) * 64);
        }
        if (m > 0) {
            result = result + s.substring(n * 64, slen);
        }
        return result;
    }

    public static String byteArrayToBase64String(byte[] b) {
        return byteArrayToBase64String(b, b.length);
    }

    private static String toBase64(byte b1, byte b2, byte b3) {
        int[] digit = new int[4];
        digit[0] = ((b1 & 0xFC) >>> 2);
        digit[1] = ((b1 & 0x3) << 4);
        digit[1] |= (b2 & 0xF0) >> 4;
        digit[2] = ((b2 & 0xF) << 2);
        digit[2] |= (b3 & 0xC0) >> 6;
        digit[3] = (b3 & 0x3F);
        String res = "";
        for (int i = 0; i < digit.length; i++) {
            res = res + base64Digit(digit[i]);
        }
        return res;
    }

    private static String toBase64(byte b1, byte b2) {
        int[] digit = new int[3];
        digit[0] = ((b1 & 0xFC) >>> 2);
        digit[1] = ((b1 & 0x3) << 4);
        digit[1] |= (b2 & 0xF0) >> 4;
        digit[2] = ((b2 & 0xF) << 2);
        String res = "";
        for (int i = 0; i < digit.length; i++) {
            res = res + base64Digit(digit[i]);
        }
        res = res + "=";
        return res;
    }

    private static String toBase64(byte b1) {
        int[] digit = new int[2];
        digit[0] = ((b1 & 0xFC) >>> 2);
        digit[1] = ((b1 & 0x3) << 4);
        String res = "";
        for (int i = 0; i < digit.length; i++) {
            res = res + base64Digit(digit[i]);
        }
        res = res + "==";
        return res;
    }

    private static char base64Digit(int i) {
        if (i < 26) {
            return (char) (65 + i);
        }
        if (i < 52) {
            return (char) (97 + (i - 26));
        }
        if (i < 62) {
            return (char) (48 + (i - 52));
        }
        if (i == 62) {
            return '+';
        }
        return '/';
    }

    static void pBinInt(String s, char i) {
        System.out.println("int: ");
        for (int j = 15; j >= 0; j--) {
            if ((1 << j & i) != 0) {
                System.out.print("1");
            }
            else {
                System.out.print("0");
            }
        }
        System.out.println();
    }

    public static byte[] base64StringToByteArray(String s) throws Exception {
        String t = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c != '\n') && (c != '\r')) {
                if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || ((c >= '0') && (c <= '9')) || (c == '+') || (c == '/')) {
                    t = t + c;
                }
                else {
                    if (c == '=') {
                        break;
                    }
                    throw new Exception("FORMATEXCEPTION");
                }
            }
        }
        int len = t.length();
        int n = 3 * (len / 4);
        switch (len % 4) {
            case 1:
                throw new Exception("FORMATEXCEPTION");
            case 2:
                len += 2;
                n++;
                t = t + "==";
                break;
            case 3:
                len++;
                n += 2;
                t = t + "=";
        }
        byte[] b = new byte[n];
        for (int i = 0; i < len / 4; i++) {
            byte[] temp = base64ToBytes(t.substring(4 * i, 4 * (i + 1)));
            for (int j = 0; j < temp.length; j++) {
                b[(3 * i + j)] = temp[j];
            }
        }
        return b;
    }

    private static byte[] base64ToBytes(String s) {
        int len = 0;
        for (int ii = 0; ii < s.length(); ii++) {
            if (s.charAt(ii) != '=') {
                len++;
            }
        }
        int[] digit = new int[len];
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if ((c >= 'A') && (c <= 'Z')) {
                digit[i] = (c - 'A');
            }
            else if ((c >= 'a') && (c <= 'z')) {
                digit[i] = (c - 'a' + 26);
            }
            else if ((c >= '0') && (c <= '9')) {
                digit[i] = (c - '0' + 52);
            }
            else if (c == '+') {
                digit[i] = 62;
            }
            else if (c == '/') {
                digit[i] = 63;
            }
        }
        byte[] b = new byte[len - 1];
        switch (len) {
            case 4:
                b[2] = ((byte) ((digit[2] & 0x3) << 6 | digit[3]));
            case 3:
                b[1] = ((byte) ((digit[1] & 0xF) << 4 | (digit[2] & 0x3C) >>> 2));
            case 2:
                b[0] = ((byte) (digit[0] << 2 | (digit[1] & 0x30) >>> 4));
        }
        return b;
    }
}
