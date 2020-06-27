package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dto.PartorlRecordDto;
import com.bank.manage.dto.PartorlRecordHeadDto;
import com.bank.manage.service.PartorlRecordService;
import com.bank.manage.vo.PartorlRecordQueryVo;
import com.bank.manage.vo.partorlRecord.PartorlRecordVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Andy
 * @Date: 2020/5/12 17:11
 */
@Api(tags = "巡查记录接口")
@RestController
@RequestMapping("/partorlrecord")
public class PartorlRecordController extends BaseController {

    @Autowired
    private PartorlRecordService partorlRecordService;

    @ApiOperation("获取巡查记录列表")
    @PostMapping("/list")
    public IPage<PartorlRecordDto> getList(@RequestBody PartorlRecordQueryVo partorlRecordQueryVo){
        return partorlRecordService.getPageRecord(partorlRecordQueryVo);
    }


    @ApiOperation(("保存巡查记录信息"))
    @PostMapping("/save")
    public boolean saveRecord(@RequestBody PartorlRecordVo partorlRecordVo, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return partorlRecordService.saveRecord(partorlRecordVo,tokenUserInfo);
    }

    @ApiOperation(("提交巡查记录信息"))
    @PostMapping("/submit")
    public boolean submitRecord(@RequestBody PartorlRecordVo partorlRecordVo,HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return partorlRecordService.submitPartorlRecord(partorlRecordVo,tokenUserInfo);
    }

    @ApiOperation(("获取大堂经理巡查内容填报头"))
    @GetMapping("/head/{processId}")
    public PartorlRecordHeadDto getHeadInfo(@PathVariable Integer processId,HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return partorlRecordService.getHeadInfo(processId,tokenUserInfo);
    }
}
