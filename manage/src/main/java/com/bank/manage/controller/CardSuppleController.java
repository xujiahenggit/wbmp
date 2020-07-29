package com.bank.manage.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.bank.core.entity.BizException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.FileDo;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.NetUtil;
import com.bank.core.utils.StringSplitUtil;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dos.CardSuppleDO;
import com.bank.manage.dto.CardSuppleDto;
import com.bank.manage.dto.InfoMessageDto;
import com.bank.manage.service.CardSuppleService;
import com.bank.manage.vo.CardSuppleDeleteVo;
import com.bank.manage.vo.CardSupplePassRejectVo;
import com.bank.manage.vo.CardSuppleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cardSupple")
@Api(tags = "网点引导员-外包人员接口")
public class CardSuppleController extends BaseController {

    @Autowired
    private CardSuppleService cardSuppleService;

    @Resource
    NetUtil netUtil;

    @SystemLog(logModul = "补卡申请", logType = "查询", logDesc = "补卡申请待办列表")
    @ApiOperation(value = "获取待办列表")
    @PostMapping("/list")
    public IPage<CardSuppleDto> getList(@RequestBody CardSuppleQueryVo cardSuppleQueryVo) {
        return cardSuppleService.getList(cardSuppleQueryVo);
    }

    @SystemLog(logModul = "补卡申请", logType = "查询", logDesc = "补卡申请已办列表")
    @ApiOperation(value = "获取已办列表")
    @PostMapping("/areadylist")
    public IPage<CardSuppleDto> getAreadyList(@RequestBody CardSuppleQueryVo cardSuppleQueryVo) {
        return cardSuppleService.getAreadyList(cardSuppleQueryVo);
    }

    @SystemLog(logModul = "补卡申请", logType = "查询", logDesc = "获取消息通知")
    @ApiOperation(value = "获取消息通知列表")
    @PostMapping("/infomationList")
    public IPage<InfoMessageDto> getInfomationList(CardSuppleQueryVo cardSuppleQueryVo, HttpServletRequest request) {
        return cardSuppleService.getInfomationList(cardSuppleQueryVo);
    }

    @SystemLog(logModul = "补卡申请", logType = "查询", logDesc = "补卡审核通过")
    @ApiOperation(value = "审核通过")
    @PostMapping("/pass")
    public boolean passProcess(@RequestBody CardSupplePassRejectVo cardSupplePassRejectVo, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return cardSuppleService.passProcess(cardSupplePassRejectVo, tokenUserInfo);
    }

    @SystemLog(logModul = "补卡申请", logType = "新增", logDesc = "新增补卡申请")
    @PostMapping("/saveCardSupple")
    @ApiOperation(value = "新增外包人员补卡申请")
    public Boolean saveCardSupple(@RequestBody CardSuppleDO cardSuppleDO) {

        QueryWrapper<CardSuppleDO> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("USHER_ID",cardSuppleDO.getUsherId());
        queryWrapper.eq("CARD_SUPPLE_DATE",cardSuppleDO.getCardSuppleDate());
        CardSuppleDO cardSuppleDOExist=cardSuppleService.getOne(queryWrapper);
        if(cardSuppleDOExist!=null){
            throw new BizException("补卡申请审批中，不能重复申请！");
        }

        String path = "";
        if (StrUtil.isNotBlank(cardSuppleDO.getCardSuppleImg())) {
            path = StringSplitUtil.splitMaterialPath(cardSuppleDO.getCardSuppleImg(), netUtil.getUrlSuffix(""));
        }
        cardSuppleDO.setCardSuppleState("10");
        cardSuppleDO.setCardSuppleCreatetime(LocalDateTime.now());
        cardSuppleDO.setCardSuppleDeleteFlag("0");
        cardSuppleDO.setCardSuppleImg(path);
        return cardSuppleService.save(cardSuppleDO);
    }

    @SystemLog(logModul = "补卡申请", logType = "驳回", logDesc = "驳回补卡审批")
    @ApiOperation(value = "驳回审批")
    @PostMapping("/reject")
    public boolean rejectProcess(@RequestBody CardSupplePassRejectVo cardSupplePassRejectVo, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return cardSuppleService.rejectProcess(cardSupplePassRejectVo, tokenUserInfo);
    }

    @SystemLog(logModul = "补卡申请", logType = "删除", logDesc = "删除消息通知")
    @ApiOperation(value = "删除消息通知")
    @DeleteMapping("/delete/")
    public boolean deleteInfomation(@RequestBody List<CardSuppleDeleteVo> list) {
        return cardSuppleService.deleteInfomation(list);
    }

    @SystemLog(logModul = "补卡申请", logType = "获取详情", logDesc = "通过ID获取详细信息")
    @ApiOperation(value = "通过ID获取详细信息")
    @GetMapping("/info/{cardSuppleId}")
    public CardSuppleDto getInfo(@PathVariable Integer cardSuppleId) {
        return cardSuppleService.getInfo(cardSuppleId);
    }

    @PostMapping("/uploadCardSuppleFile")

    @SystemLog(logModul = "补卡申请", logType = "获取详情", logDesc = "外包人员文件上传")
    @ApiOperation(value = "外包人员文件上传")
    public FileDo upFileForShare(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        return cardSuppleService.upLoadFile(file);
    }
}
