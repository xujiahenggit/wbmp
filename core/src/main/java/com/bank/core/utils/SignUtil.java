package com.bank.core.utils;

import java.security.MessageDigest;
import java.util.SortedMap;
import java.util.TreeMap;

public class SignUtil {

    private static SortedMap<String, Object> hashMap;

    public static String spellString() {
        String string = "";
        for (SortedMap.Entry<String, Object> entry : hashMap.entrySet()) {
            string += entry.getKey() + "=" + entry.getValue() + "&";
        }
        string = string.substring(0, string.length() - 1);
        System.out.print(string);
        String a = encode(string);
        hashMap.clear();
        return a;
    }

    public static SortedMap<String, Object> addString(String key, Object value) {
        if (hashMap == null) {
            hashMap = new TreeMap<String, Object>();
        }
        hashMap.put(key, value);
        return hashMap;
    }

    public static String encode(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                if (hex.length() == 1) {
                    sb.append("0" + hex);
                }
                else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        }
        catch (Exception e) {
            return "";
        }
    }

}
