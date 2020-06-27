package com.bank.manage.controller;

import com.bank.core.entity.BizException;
import com.bank.core.utils.FileUtil;
import com.bank.manage.service.PartorlDownloadRecordService;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/14 15:17
 */
@RestController
@RequestMapping("/partorl/down")
@Api(tags = "巡查记录下载接口")
public class PartorlRecordDownLoadController {

    @Autowired
    private PartorlDownloadRecordService partorlDownloadRecordService;

    @PostMapping("/file")
    public void downRecord(@RequestBody List<Integer> partorlIds, HttpServletRequest request, HttpServletResponse response) {
        try {
            String filePath = partorlDownloadRecordService.getZpiFile(partorlIds);
            File downloadFile = new File(filePath);
            ServletContext context = request.getServletContext();
            String mimeType = context.getMimeType(filePath);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());
            String headerKey = "Content-Disposition";
            //String fileName = URLEncoder.encode(downloadFile.getName(), "GBK");
            String fileName = new String(downloadFile.getName().getBytes("GBK"),"ISO-8859-1");
            //String fileName=new String(downloadFile.getName().getBytes("UTF-8"),"UTF-8");
            String headerValue = String.format("attachment; filename=\"%s\"",fileName);
            response.setCharacterEncoding("UTF-8");
            //response.setHeader(headerKey, "attachment;filename=" + URLEncoder.encode(downloadFile.getName(),"UTF-8"));
            response.setHeader(headerKey,"attachment;fileName=" + fileName);
            InputStream myStream = new FileInputStream(filePath);
            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
           throw new BizException("文件下载失败");
        }
    }
}
