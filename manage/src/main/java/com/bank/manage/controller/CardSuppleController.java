package com.bank.manage.controller;

import cn.hutool.core.util.IdUtil;
import com.bank.auth.base.BaseController;
import com.bank.core.entity.BizException;
import com.bank.core.entity.FileDo;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.FileUploadUtils;
import com.bank.core.utils.StringSplitUtil;
import com.bank.manage.dos.CardSuppleDO;
import com.bank.manage.dto.InfoMessageDto;
import com.bank.manage.service.CardSuppleService;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dto.CardSuppleDto;
import com.bank.manage.vo.CardSuppleDeleteVo;
import com.bank.manage.vo.CardSupplePassRejectVo;
import com.bank.manage.vo.CardSuppleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cardSupple")
@Api(tags = "网点引导员——外包人员接口")
public class CardSuppleController extends BaseController {

    @Autowired
    private CardSuppleService cardSuppleService;

    @ApiOperation(value = "获取待办列表")
    @PostMapping("/list")
    public IPage<CardSuppleDto> getList(@RequestBody CardSuppleQueryVo cardSuppleQueryVo){
        return cardSuppleService.getList(cardSuppleQueryVo);
    }
    @Autowired
    private ConfigFileReader configFileReader;

    @ApiOperation(value = "获取消息通知列表")
    @PostMapping("/infomationList")
    public IPage<InfoMessageDto> getInfomationList(CardSuppleQueryVo cardSuppleQueryVo, HttpServletRequest request){
        return cardSuppleService.getInfomationList(cardSuppleQueryVo);
    }

    @ApiOperation(value = "审核通过")
    @PostMapping("/pass")
    public boolean passProcess(@RequestBody CardSupplePassRejectVo cardSupplePassRejectVo,HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return cardSuppleService.passProcess(cardSupplePassRejectVo,tokenUserInfo);
    }
    @PostMapping("/saveCardSupple")
    @ApiOperation(value = "新增外包人员补卡申请")
    public Boolean saveCardSupple(@RequestBody CardSuppleDO cardSuppleDO){
        String path =  StringSplitUtil.splitMaterialPath(cardSuppleDO.getCardSuppleImg(),configFileReader.getHTTP_PATH());
        cardSuppleDO.setCardSuppleState("10");
        cardSuppleDO.setCardSuppleCreatetime(LocalDateTime.now());
        cardSuppleDO.setCardSuppleDeleteFlag("0");
        cardSuppleDO.setCardSuppleImg(path);
        return cardSuppleService.save(cardSuppleDO);
    }

    @ApiOperation(value = "驳回审批")
    @PostMapping("/reject")
    public boolean rejectProcess(@RequestBody CardSupplePassRejectVo cardSupplePassRejectVo,HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return  cardSuppleService.rejectProcess(cardSupplePassRejectVo,tokenUserInfo);
    }

    @ApiOperation(value = "删除消息通知")
    @DeleteMapping("/delete/")
    public boolean deleteInfomation(@RequestBody List<CardSuppleDeleteVo> list){
        return  cardSuppleService.deleteInfomation(list);
    }

    @ApiOperation(value = "通过ID获取详细信息")
    @GetMapping("/info/{cardSuppleId}")
    public CardSuppleDto getInfo(@PathVariable Integer cardSuppleId){
        return cardSuppleService.getInfo(cardSuppleId);
    }
    @PostMapping("/uploadCardSuppleFile")

    @ApiOperation(value = "外包人员文件上传")
    public FileDo upFileForShare(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request){
        return cardSuppleService.upLoadFile(file);
    }
}