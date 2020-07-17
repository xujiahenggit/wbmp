package com.bank.core.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bank.core.entity.ImagePlatformFile;
import com.bank.core.entity.ImagePlatformRequest;
import com.bank.core.entity.ImagePlatformResponse;
import com.bank.core.utils.ImagePlatformUtils;
import com.bank.core.utils.NetUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 影像平台相关API
 * ClassName: ImagePlatformController
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/13 18:48:13
 */
@RestController
@RequestMapping("/imagePlatform")
@Api(tags = "影像平台相关接口")
@Slf4j
public class ImagePlatformController {

    @Resource
    NetUtil netUtil;

    private String imagePlatformPath = "/video/imagePlatform";

    @PostMapping("/localUpload")
    @ApiOperation("影像文件本地上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（现场检查-xcjc；预警监测-yjjc；）等", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pathId", value = "文件目录主键", required = true, dataType = "String")
    })
    public ImagePlatformFile localUpload(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "pathId") String pathId) {
        String filename = file.getOriginalFilename();
        String name = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));

        String dir = imagePlatformPath + "/" + type + "/" + pathId;
        //判断目录是否存在
        File f = new File(dir);
        if (!f.exists()) {
            f.mkdirs();
        }
        File fileUp = new File(new File(dir).getAbsolutePath() + "/" + name);

        ImagePlatformFile imagePlatformFile = new ImagePlatformFile();
        try {
            file.transferTo(fileUp);
            imagePlatformFile.setFileType("1");
            imagePlatformFile.setFilePath(dir);
            imagePlatformFile.setFileName(name);
            imagePlatformFile.setHttpFilePath(netUtil.getUrlSuffix("") + dir + "/" + name);
        }
        catch (IOException e) {
            e.printStackTrace();
            log.error("影像文件本地上传失败" + e.getMessage());
        }
        return imagePlatformFile;
    }

    @PostMapping("/localDelete")
    @ApiOperation("影像文件本地删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filePath", value = "本地文件目录", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fileName", value = "本地文件名称", required = true, dataType = "String")
    })
    public boolean localDelete(@RequestParam(value = "filePath") String filePath, @RequestParam(value = "fileName") String fileName) {
        File fileDelete = new File(new File(filePath + "/" + fileName).getAbsolutePath());
        if (fileDelete.isFile()) {
            fileDelete.delete();
        }

        File dir = new File(filePath);
        File[] listFiles = dir.listFiles();
        //文件夹不存在文件则删除文件夹
        if (listFiles.length == 0) {
            dir.delete();
        }
        return true;
    }

    @PostMapping("/imagePlatformUpload")
    @ApiOperation("影像文件上传（包含首次上传、追加、删除逻辑）")
    @ApiImplicitParam(name = "imagePlatformRequest", value = "影像平台上传请求", required = true, dataType = "ImagePlatformRequest", paramType = "body")
    public ImagePlatformResponse imagePlatformUpload(@RequestBody ImagePlatformRequest imagePlatformRequest) {
        File dir = new File(imagePlatformPath + "/" + imagePlatformRequest.getType() + "/" + imagePlatformRequest.getPathId());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        ImagePlatformResponse response = new ImagePlatformResponse();
        response.setBusiSerialNo(imagePlatformRequest.getBusiSerialNo());
        response.setBusiStartDate(imagePlatformRequest.getBusiStartDate());
        response.setContentId(imagePlatformRequest.getContentId());

        ImagePlatformUtils imagePlatformUtils = new ImagePlatformUtils(imagePlatformRequest.getType());
        //先处理删除逻辑
        if (imagePlatformRequest.getDeleteFileNames() != null && imagePlatformRequest.getDeleteFileNames().size() > 0) {
            imagePlatformUtils.delete(imagePlatformRequest.getContentId(), imagePlatformRequest.getBusiStartDate(), imagePlatformRequest.getDeleteFileNames());
        }

        File[] listFiles = dir.listFiles();
        if (listFiles.length == 0) {
            log.info("当前本地目录[" + dir.getAbsolutePath() + "]未存在文件，无须上传影像平台");
        }
        else {
            //追加
            if (StringUtils.isNotBlank(imagePlatformRequest.getContentId())) {
                imagePlatformUtils.update(imagePlatformRequest.getContentId(), imagePlatformRequest.getBusiStartDate(), listFiles);
            }
            //上传
            else {
                response = imagePlatformUtils.upload(listFiles);
            }
        }

        //删除本地文件
        for (int i = 0; i < listFiles.length; i++) {
            File fileDelete = listFiles[i];
            if (fileDelete.isFile()) {
                fileDelete.delete();
            }
        }
        return response;
    }

    @GetMapping("/imagePlatformQuery")
    @ApiOperation("影像平台文件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（现场检查-xcjc；预警监测-yjjc；）等", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pathId", value = "文件目录主键（上传时必传、查看时可以不用传）", required = false, dataType = "String"),
            @ApiImplicitParam(name = "contentId", value = "影像ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "busiStartDate", value = "影像时间", required = true, dataType = "String")
    })
    public ImagePlatformResponse imagePlatformQuery(@RequestParam(value = "type") String type, @RequestParam(value = "pathId", required = false, defaultValue = "") String pathId, @RequestParam(value = "contentId", defaultValue = "") String contentId, @RequestParam(value = "busiStartDate",
            defaultValue = "") String busiStartDate) {
        //首先列出本地文件
        List<ImagePlatformFile> imagePlatformFileList = new ArrayList<ImagePlatformFile>();
        //文件目录主键 不传入时不载入本地文件
        if (StringUtils.isNotBlank(pathId)) {
            String dir = imagePlatformPath + "/" + type + "/" + pathId;
            //判断目录是否存在
            File f = new File(dir);
            if (!f.exists()) {
                f.mkdirs();
            }
            File fileDir = new File(dir);
            File[] listFiles = fileDir.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                ImagePlatformFile imagePlatformFile = new ImagePlatformFile();
                String name = listFiles[i].getName();
                imagePlatformFile.setFileType("1");
                imagePlatformFile.setFilePath(dir);
                imagePlatformFile.setFileName(name);
                imagePlatformFile.setHttpFilePath(netUtil.getUrlSuffix("") + dir + "/" + name);
                imagePlatformFileList.add(imagePlatformFile);
            }
        }

        ImagePlatformUtils imagePlatformUtils = new ImagePlatformUtils(type);
        //影像ID没有时不调用影像平台查询接口
        if (StringUtils.isBlank(contentId)) {
            ImagePlatformResponse response = new ImagePlatformResponse();
            response.setContentId(contentId);
            response.setBusiStartDate(busiStartDate);
            response.setAmount(0);
            response.setImagePlatformFileList(imagePlatformFileList);
            return response;
        }

        //整合影像文件与本地文件
        ImagePlatformResponse response = imagePlatformUtils.query(contentId, busiStartDate);
        response.getImagePlatformFileList().addAll(imagePlatformFileList);
        return response;
    }

}
