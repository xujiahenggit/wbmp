package com.bank.manage.controller;

import com.bank.manage.dto.PartorlDto;
import com.bank.manage.service.PartorlModualService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/11 14:34
 */
@RestController
@RequestMapping("/partorl")
@Api(tags = "巡查内容接口")
public class PartorlController {

    @Autowired
    private PartorlModualService partorlModualService;

    @ApiOperation("获取巡查内容列表")
    @GetMapping("/list/{processId}")
    public List<PartorlDto> getList(@PathVariable Integer processId){
        return partorlModualService.getList(processId);
    }

}
