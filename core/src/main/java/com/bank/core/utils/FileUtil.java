package com.bank.core.utils;


import com.bank.core.entity.BizException;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
public class FileUtil {
    /**
     * 从输入流中获取数据
     *
     * @param inStream 输入流
     * @return 二进制数据
     * @throws Exception 异常
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 将文件写入到磁盘
     *
     * @param fileBt   文件的二进制流
     * @param fullPath 文件的完整路径，类似/xxx/xxx.png
     */
    public static void writeFileToDisk(byte[] fileBt, String fullPath) {
        try {
            String filePath = fullPath.substring(0, fullPath.lastIndexOf("/"));
            File folder = new File(filePath);
            //文件夹路径不存在
            if (!folder.exists() && !folder.isDirectory()) {
                folder.mkdirs();
            }
            FileOutputStream fops = new FileOutputStream(fullPath);
            fops.write(fileBt);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            throw new BizException("将文件写入磁盘出错");
        }
    }

    /**
     * 文件下载
     * @param filePath 文件路径
     * @param res      response对象
     */
    public static void download(String filePath, HttpServletResponse res) {
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        byte[] buff = new byte[1024];
        try {
            // 发送给客户端的数据
            res.getOutputStream();
            // 读取filename
            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            int i = bis.read(buff);
            while (i != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            throw new BizException("下载异常");
        }finally {
            try{
                if(null!=bis){
                    bis.close();
                }
                if(null!=outputStream){
                    outputStream.close();
                }
            }catch (Exception e){
                throw new BizException("文件压缩失败");
            }
        }
    }
}

