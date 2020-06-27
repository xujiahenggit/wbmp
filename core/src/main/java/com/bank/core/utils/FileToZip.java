package com.bank.core.utils;

import com.bank.core.entity.BizException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

/**
 * @Author: Andy
 * @Date: 2020/5/15 9:58
 */
@Slf4j
public class FileToZip {
    /**
     * 文件列表 压缩到zip
     * @param srcFiles 文件列表
     * @param zipFilePath zip 存放路径
     * @param zipFileName zip 文件名
     * @return 文件路径
     * @throws RuntimeException
     */
    public static String toZip(List<String> srcFiles,String zipFilePath,String zipFileName) {
        List<File> files=new ArrayList<>();
        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            for (String item:srcFiles){
                files.add(new File(item));
            }
            File zipFile = new File(zipFilePath + zipFileName + ".zip");
            log.info("zip打包地址："+zipFile.getPath());
            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(new BufferedOutputStream(fos));;
            for (File item : files) {
                log.info("压缩文件名称："+item.getPath()+"  "+item.getName());
                byte[] buf = new byte[BUFFER_SIZE];
                //创建ZIP实体，并添加进压缩包
                ZipEntry zipEntry = new ZipEntry(item.getName());
                zos.putNextEntry(zipEntry);
                //读取待压缩的文件并写进压缩包里
                fis = new FileInputStream(item);
                bis = new BufferedInputStream(fis, BUFFER_SIZE);
                int read = 0;
                while ((read = bis.read(buf, 0, BUFFER_SIZE)) != -1) {
                    zos.write(buf, 0, read);
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
            return zipFile.getPath();
        } catch (Exception e) {
            log.info("文件压缩异常："+e);
            throw new BizException("压缩文件失败！");
        } finally {
            //关闭流
            try {
                if(null !=bis){
                    bis.close();
                }
                if (null != zos) {
                    zos.close();
                }
                if (null != fos) {
                    fos.close();
                }
                if (null != fis) {
                    fis.close();
                }
            } catch (Exception e) {
                log.info("压缩文件异常信息："+e);
                throw new BizException("文件压缩失败");
            }
        }
    }
}
