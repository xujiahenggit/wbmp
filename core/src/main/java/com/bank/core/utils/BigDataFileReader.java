package com.bank.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Andy
 * @Date: 2020/3/31 17:36
 * 大数据文件读取工具类
 */
@Slf4j
public class BigDataFileReader {

    public static List<List<Object>> parseText(File file, String pattern) throws Exception {
        String fileName = file.getName();
        if (!StringUtils.isNotEmpty(fileName)) {
            log.info("未找到文件");
            return null;
        }
        // 获取后缀名 如果后缀名没有 .则返回""
        String fileStuffix = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
        //用于返回使用
        List<List<Object>> listRows = null;
        if ("dat".equals(fileStuffix)) {
            BufferedReader bf = null;
            String temp = null;
            try {
                //读取文件流
                FileInputStream fis = new FileInputStream(new File(file, ""));
                //文件编码格式
                bf = new BufferedReader(new InputStreamReader(fis, "GBK"));
                //用于返回使用
                listRows = new ArrayList<List<Object>>();
                while ((temp = bf.readLine()) != null) {
                    String[] arr = StringUtils.splitPreserveAllTokens(temp, pattern);
                    List<Object> row = new ArrayList<Object>();
                    //循环遍历上面的数组
                    for (String string : arr) {
                        row.add(string);
                    }
                    listRows.add(row);
                }
                fis.close();
            }
            catch (Exception e) {
               log.info("文件读取失败");
               throw new Exception("文件读取失败");
            }
            finally {
                //在返回之前关闭流
                if (bf != null) {
                    try {
                        bf.close();
                    }
                    catch (IOException e2) {
                        //在这里添加 相关日志
                    }
                }
            }
        }
        else {
            log.info("发生异常: 文件的后缀名异常");
            throw new Exception("发生异常: 文件的后缀名异常");
        }
        return listRows;
    }


    public static List<List<Object>> parseNfrtOrgText(File file, String pattern) throws Exception {
        String fileName = file.getName();
        if (!StringUtils.isNotEmpty(fileName)) {
            log.info("未找到文件");
            return null;
        }
        //用于返回使用
        List<List<Object>> listRows = null;
        BufferedReader bf = null;
        String temp = null;
        try {
            //读取文件流
            FileInputStream fis = new FileInputStream(new File(file, ""));
            //文件编码格式
            bf = new BufferedReader(new InputStreamReader(fis, "GBK"));
            //用于返回使用
            listRows = new ArrayList<List<Object>>();
            while ((temp = bf.readLine()) != null) {
                String[] arr = StringUtils.splitPreserveAllTokens(temp, pattern);
                List<Object> row = new ArrayList<Object>();
                //循环遍历上面的数组
                for (String string : arr) {
                    row.add(string);
                }
                listRows.add(row);
            }
            fis.close();
        }
        catch (Exception e) {
            log.info("文件读取失败");
            throw new Exception("文件读取失败");
        }
        finally {
            //在返回之前关闭流
            if (bf != null) {
                try {
                    bf.close();
                }
                catch (IOException e2) {
                    //在这里添加 相关日志
                }
            }
        }
        return listRows;
    }
}
