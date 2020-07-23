package com.bank.manage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.core.entity.BizException;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.manage.dto.CheckWorkAttendDto;
import com.bank.manage.dto.CheckWorkRejectDto;
import com.bank.manage.service.CheckWorkAttendService;
import com.bank.manage.vo.CheckWorkAttendQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: Andy
 * @Date: 2020/6/2 14:37
 */
@RestController
@RequestMapping("/checkattend")
@Api(tags = "网点引导员-考勤记录")
public class CheckWorkAttendController {

    @Autowired
    private CheckWorkAttendService checkWorkAttendService;

    @ApiOperation("月度考勤数据")
    @GetMapping("/data")
    public CheckWorkAttendDto getList(@RequestParam String date) {
        return checkWorkAttendService.getCheckWorkAttendData(date);
    }

    @ApiOperation("月度考勤数据下载Excel")
    @GetMapping("/down")
    public void down(@RequestParam String date, HttpServletResponse response, HttpServletRequest request) {
        try {
            String filePath = checkWorkAttendService.getDownFile(date);
            File downloadFile = new File(filePath);
            ServletContext context = request.getServletContext();
            String mimeType = context.getMimeType(filePath);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());
            String headerKey = "Content-Disposition";
            String fileName = new String(downloadFile.getName().getBytes("GBK"), "ISO-8859-1");
            response.setCharacterEncoding("UTF-8");
            response.setHeader(headerKey, "attachment;fileName=" + fileName);
            InputStream myStream = new FileInputStream(filePath);
            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();
        }
        catch (Exception e) {
            throw new BizException("月度考勤记录下载失败！");
        }
    }

    @ApiOperation("月度考勤 驳回人数")
    @PostMapping("/monthreject")
    public IPage<CheckWorkRejectDto> getRejectList(@RequestBody CheckWorkAttendQueryVo checkWorkAttendQueryVo) {
        return checkWorkAttendService.getRejectList(checkWorkAttendQueryVo, Integer.parseInt(NewProcessStatusFile.PROCESS_REJECT));
    }

    @ApiOperation("月度考勤 未完成人数")
    @PostMapping("/monthwait")
    public IPage<CheckWorkRejectDto> getWaitMonthPerple(@RequestBody CheckWorkAttendQueryVo checkWorkAttendQueryVo) {
        return checkWorkAttendService.getRejectList(checkWorkAttendQueryVo, Integer.parseInt(NewProcessStatusFile.PROCESS_WAIT));
    }

    @ApiOperation("月满意度 未完成人数")
    @PostMapping("/satisfactwait")
    public IPage<CheckWorkRejectDto> getSasifactWaitPerple(@RequestBody CheckWorkAttendQueryVo checkWorkAttendQueryVo) {
        return checkWorkAttendService.getSasifactWaitPerple(checkWorkAttendQueryVo);
    }
}
