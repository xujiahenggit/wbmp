package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.sysConst.ConstFile;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dos.StaffDO;
import com.bank.manage.service.StaffService;
import com.bank.manage.vo.StaffQueryVo;
import com.bank.manage.vo.StaffVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Andy
 * @Date: 2020/4/9 9:50
 */
@Api(tags="行员信息管理")
@RestController
@RequestMapping("/staff")
public class StaffController extends BaseController {

    @Autowired
    private StaffService staffService;

    @ApiOperation("查询行员信息列表")
    @PostMapping("/list")
    public IPage<StaffDO> selectPage(@RequestBody StaffQueryVo staffQueryVo){
        return staffService.getPage(staffQueryVo);
    }

    @ApiOperation("添加 行员信息")
    @PostMapping("/add")
    @SystemLog(logModul = ConstFile.MODULE_STAFF,logType = ConstFile.TYPE_ADD,logDesc = "添加行员信息")
    public Boolean add(@RequestBody @Validated StaffVo staffVo, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return staffService.add(staffVo,tokenUserInfo);
    }

    @ApiOperation("更新 行员信息")
    @PutMapping("/update")
    @SystemLog(logModul = ConstFile.MODULE_STAFF,logType = ConstFile.TYPE_UPDATE,logDesc = "更新行员信息")
    public Boolean update(@RequestBody @Validated StaffVo staffVo,HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return staffService.modify(staffVo,tokenUserInfo);
    }

    @ApiOperation("根据ID 获取行员信息")
    @ApiImplicitParam(name = "staffId", value = "行员ID", required = true, dataType = "int",paramType = "path")
    @GetMapping("/{staffId}")
    public StaffDO getStaffInfoById(@PathVariable Integer staffId){
        return staffService.getById(staffId);
    }

    @ApiOperation("根据ID 删除行员信息 --发起删除申请")
    @ApiImplicitParam(name = "staffId", value = "行员ID", required = true, dataType = "int",paramType = "path")
    @DeleteMapping("/{staffId}")
    @SystemLog(logModul =ConstFile.MODULE_STAFF,logType = ConstFile.TYPE_DELETE,logDesc = "根据ID 删除行员信息-发起删除请求")
    public Boolean deleteStaff(@PathVariable Integer staffId,HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return staffService.deleteById(staffId,tokenUserInfo);
    }

    @ApiOperation("根据ID 删除行员信息 --正式删除")
    @ApiImplicitParam(name = "staffId", value = "行员ID", required = true, dataType = "int",paramType = "path")
    @DeleteMapping("/del/{staffId}")
    @SystemLog(logModul =ConstFile.MODULE_STAFF,logType = ConstFile.TYPE_DELETE,logDesc = "根据ID 删除行员信息--真正的删除逻辑")
    public Boolean deleteStaffTrue(@PathVariable Integer staffId){
        return staffService.deleteByIdTrue(staffId);
    }
}
