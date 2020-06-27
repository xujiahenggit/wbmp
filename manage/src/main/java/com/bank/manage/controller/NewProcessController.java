package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.sysConst.ConstFile;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dos.NewProcessDO;
import com.bank.manage.dto.NewProcessDTO;
import com.bank.manage.dto.NewProcessInfoDto;
import com.bank.manage.service.NewProcessService;
import com.bank.manage.vo.NewProcessPassVo;
import com.bank.manage.vo.NewProcessQueryVo;
import com.bank.manage.vo.NewProcessRejectVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.driver.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 流程审批
 * @author
 * @date 2020-4-7
 */
@Api(tags = "流程审批接口")
@RestController
@RequestMapping("/newProcess")
@Slf4j
public class NewProcessController extends BaseController {
    @Autowired
    private NewProcessService newProcessService ;

    @ApiOperation(value = "待办审核数 --首页用")
    @GetMapping("/num")
    public Integer getWaitProcessNum(HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return newProcessService.getWaitProcessNum(tokenUserInfo);
    }


    @ApiOperation(value = "待办审核列表")
    @PostMapping("/waitlist")
    public IPage<NewProcessDO> getWaitProcess(@RequestBody NewProcessQueryVo newProcessQueryVo, HttpServletRequest request){
        //当前登录用户的机构号
        TokenUserInfo tokenUserInfo =getCurrentUserInfo(request);
        IPage<NewProcessDO> listWaitProcess=newProcessService.getWaitProcessList(newProcessQueryVo,tokenUserInfo);
        return listWaitProcess;
    }

    @ApiOperation(value = "已办理审核列表")
    @PostMapping("/passlist")
    public IPage<NewProcessDO> getPassProcess(@RequestBody NewProcessQueryVo newProcessQueryVo,HttpServletRequest request){
        //当前登录用户的机构号
        TokenUserInfo tokenUserInfo =getCurrentUserInfo(request);
        IPage<NewProcessDO> listPassProcess=newProcessService.getPassProcessList(newProcessQueryVo,tokenUserInfo);
        return listPassProcess;
    }

    @ApiOperation(value = "审核通过")
    @SystemLog(logModul = ConstFile.MODULE_PROCESS,logType = ConstFile.TYPE_PASS,logDesc = "审核通过")
    @PutMapping("/pass")
    public boolean passProcess(@RequestBody NewProcessPassVo newProcessPassVo, HttpServletRequest request){
        //获取当前登录用户
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return newProcessService.passPorcess(newProcessPassVo,tokenUserInfo);
    }

    @ApiOperation(value = "驳回审批")
    @SystemLog(logModul = ConstFile.MODULE_PROCESS,logType = ConstFile.TYPE_REJECT,logDesc = "驳回审批")
    @PutMapping("/reject")
    public boolean rejectProcess(@RequestBody @Validated NewProcessRejectVo newProcessRejectVo, HttpServletRequest request){
        //获取当前登录用户
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return newProcessService.rejectProcess(newProcessRejectVo,tokenUserInfo);
    }

    @ApiOperation("获取 审批类型列表")
    @GetMapping("/getmodual")
    public List<String> getModelList(){
        return newProcessService.listProcessModual();
    }

    @ApiOperation("根据ID 获取审批详细信息")
    @ApiImplicitParam(name = "processId", value = "流程编号", required = true, dataType = "int",paramType = "path")
    @GetMapping("/getinfo/{processId}")
    public NewProcessInfoDto getProcessInfoById(@PathVariable Integer processId){
        return newProcessService.getProcessInfo(processId);
    }

    @ApiOperation(value = "我的申请列表")
    @PostMapping("/selflist")
    public IPage<NewProcessDO> getSelfProcess(@RequestBody NewProcessQueryVo newProcessQueryVo,HttpServletRequest request){
        //当前登录用户的机构号
        TokenUserInfo tokenUserInfo =getCurrentUserInfo(request);
        return newProcessService.getSelfList(newProcessQueryVo,tokenUserInfo);
    }

    @ApiOperation(value = "撤销申请")
    @SystemLog(logModul = ConstFile.MODULE_PROCESS,logType = ConstFile.TYPE_REVOKE,logDesc = "撤销申请")
    @ApiImplicitParam(name = "processId", value = "流程编号", required = true, dataType = "int",paramType = "path")
    @PutMapping("/revoke/{processId}")
    public boolean revokeProcess(@PathVariable Integer processId,HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return newProcessService.revokeProcess(processId,tokenUserInfo);
    }

    @ApiOperation(value = "未办结列表")
    @PostMapping("/notbilllist")
    public IPage<NewProcessDO> getMyProcess(@RequestBody NewProcessQueryVo newProcessQueryVo,HttpServletRequest request){
        //当前登录用户的机构号
        TokenUserInfo tokenUserInfo =getCurrentUserInfo(request);
        return newProcessService.getMyProcess(newProcessQueryVo,tokenUserInfo);
    }
}
