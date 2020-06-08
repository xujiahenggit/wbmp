package com.bank.core.utils;

import com.bank.core.entity.FileDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: Andy
 * @Date: 2020/5/12 14:39
 */
@Slf4j
public class FileUploadUtils {

    /**
     * 文件上传
     * @param file 文件
     * @param uploadpath 上传地址
     * @param accessPath 访问路径
     * @param u_fleName  文件上传后的名称
     * @return 上传后的路径path
     */
    public static FileDo FileUpload(MultipartFile file, String uploadpath, String accessPath, String u_fleName) throws Exception{
        FileDo fileDo=new FileDo();
        String path = null;
        /**文件上传开始**/
        File f = new File(uploadpath);
        if(!f.exists()){
            f.mkdirs();
        }
        File fileUp = new File(new File(uploadpath).getAbsolutePath()+"/"+u_fleName);
        try {

            file.transferTo(fileUp);
            path = accessPath+"/"+u_fleName;
            //设置访问路
            fileDo.setAccssPath(path);
            //设置文件大小 单位转M
            fileDo.setFileSize(file.getSize()/1024);
            //设置文件存储路径
            fileDo.setFileSavePath(fileUp.getPath());
            //设置文件 上传名称
            fileDo.setFileLocalName(file.getOriginalFilename());
            //设置文件 存储名称
            fileDo.setFileServerName(u_fleName);
            log.info("***************文件上传临时目录成功**************");
        } catch (IOException e) {
            log.error("***************文件上传临时目录失败："+e.getMessage());
            throw e;
        }
        /**文件上传结束**/
        log.info("**************文件上传返回路劲：{}",path);
        return fileDo;
    }
}
