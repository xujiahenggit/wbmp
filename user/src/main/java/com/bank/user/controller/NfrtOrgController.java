package com.bank.user.controller;

import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.BigDataFileReader;
import com.bank.user.dos.NfrtOrgDO;
import com.bank.user.dto.OrgNftDto;
import com.bank.user.service.NfrtOrgService;
import com.bank.user.utils.GetDataUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/3 16:00
 */
@Api(tags = "分支行/网点接口")
@RestController
@RequestMapping("/nfrtorg")
@Slf4j
public class NfrtOrgController extends BaseUserController{

    @Autowired
    private NfrtOrgService nfrtOrgService;

    @ApiOperation("获取分支行列表")
    @GetMapping("/nftlist")
    public List<OrgNftDto> getNftOrgList(){
        return nfrtOrgService.getNftOrgList();
    }

    @GetMapping("/outlets/{orgId}/{type}")
    @ApiOperation("获取网点列表")
    public List<OrgNftDto> getOutletsList(@PathVariable(value = "orgId") String orgId,@PathVariable(value = "type") String type){
        return nfrtOrgService.getOutletsList(orgId,type);
    }

    @ApiOperation("分行列表----用户所有的分行，如果是总行则返回全部，如果是分行 则放回所有的分行")
    @GetMapping("/usernftlist")
    public List<OrgNftDto> getOrgListByUser(HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return nfrtOrgService.getOrgListByUser(tokenUserInfo);
    }

    @ApiOperation("分行列表----用户所有的分行，如果是总行则返回全部，如果是分行 则放回所有的分行")
    @GetMapping("/useroutletslist/{orgId}/{type}")
    public List<OrgNftDto> getOutSitListByUser(@PathVariable(value = "orgId") String orgId,@PathVariable(value = "type") String type,HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return nfrtOrgService.getOutlegetOutSitListByUsertsList(orgId,type,tokenUserInfo);
    }
}
