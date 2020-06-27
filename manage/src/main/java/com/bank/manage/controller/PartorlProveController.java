package com.bank.manage.controller;

import com.bank.core.entity.FileDo;
import com.bank.manage.service.PartorlProveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Andy
 * @Date: 2020/5/12 15:29
 */
@RestController
@RequestMapping("/prove")
@Api(tags = "巡查证明文件")
public class PartorlProveController {

    @Autowired
    private PartorlProveService partorlProveService;

    @ApiOperation(value = "证明文件上传")
    @PostMapping("/upload")
    public FileDo proveFileUpload(@RequestParam(value = "file") MultipartFile file){
        return partorlProveService.proveFileUpload(file);
    }
}
