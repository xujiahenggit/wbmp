package com.bank.user.controller;

import com.bank.user.dos.OrgDetailinfoDO;
import com.bank.user.dto.OrgDetailDto;
import com.bank.user.service.OrgDetailinfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Andy
 * @Date: 2020/5/7 16:08
 */
@Api(tags = "机构其他信息维护")
@RestController
@RequestMapping("/orgdetail")
public class OrgDetailinfoController {

    @Autowired
    private OrgDetailinfoService orgDetailinfoService;

    @ApiOperation("用户机构号获取机构信息")
    @GetMapping("/{orgId}")
    @ApiImplicitParam(name = "orgId", value = "机构编号", required = true, paramType = "path")
    public OrgDetailDto getInfo(@PathVariable String orgId){
        return orgDetailinfoService.getInfoByOrgId(orgId);
    }

    @ApiOperation("跟新取机构信息")
    @PutMapping("/update")
    public boolean update(@RequestBody OrgDetailinfoDO orgDetailinfoDO){
        return orgDetailinfoService.updateOrgDetail(orgDetailinfoDO);
    }
}
