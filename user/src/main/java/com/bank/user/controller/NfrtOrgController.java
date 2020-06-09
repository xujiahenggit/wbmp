package com.bank.user.controller;

import com.bank.core.entity.BizException;
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

import java.io.File;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/3 16:00
 */
@RestController
@RequestMapping("/nfrtorg")
@Slf4j
@Api(tags = "分支行/网点接口")
public class NfrtOrgController {

    @Autowired
    private NfrtOrgService nfrtOrgService;

    @ApiOperation("获取分支行列表")
    @GetMapping("/nftlist")
    public List<OrgNftDto> getNftOrgList(){
        return nfrtOrgService.getNftOrgList();
    }

    @GetMapping("/outlets/{orgId}")
    @ApiOperation("获取网点列表")
    public List<OrgNftDto> getOutletsList(@PathVariable String orgId){
        return nfrtOrgService.getOutletsList(orgId);
    }
}
