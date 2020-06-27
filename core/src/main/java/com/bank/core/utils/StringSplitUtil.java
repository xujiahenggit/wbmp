package com.bank.core.utils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * 字符串切割工具
 */
public class StringSplitUtil {
    /**
     * 素材路径切割工具
     * @param materialPath 前端传值
     * @param http_path     netUtil.getUrlSuffix("")
     * @return
     */
    public static String splitMaterialPath(String materialPath,String http_path){
        String path = null;

        String[] split = materialPath.split(http_path);
        if(split != null && split.length>0){
            path = split[1];
        }
        return path;
    }

    /**
     * 获取素材大小
     * @param splitMaterialPath 切割后的路径
     * @return
     */
    public static String getMaterialSize(String splitMaterialPath){
        File file = new File(splitMaterialPath);
        String netFileSize = getNetFileSizeDescription(file.length());
        return netFileSize;
    }


    public static String getNetFileSizeDescription(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        } else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        } else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        } else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            } else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }
}
