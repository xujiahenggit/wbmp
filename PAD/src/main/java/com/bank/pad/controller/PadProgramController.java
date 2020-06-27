package com.bank.pad.controller;

import com.bank.manage.dos.ProgramDO;
import com.bank.manage.dto.Progame.PadProgramDto;
import com.bank.manage.service.ProgramService;
import com.bank.manage.vo.ProgramQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: Andy
 * @Date: 2020/4/28 9:33
 */
@Api(tags = "PAD查询可用节目用")
@RestController
@RequestMapping("/padprogram")
public class PadProgramController {

    @Resource
    private ProgramService programService;

    @ApiOperation(value = "查询所有的可用节目列表")
    @PostMapping("/programlist")
    public IPage<ProgramDO> listProgram(@RequestBody ProgramQueryVo programQueryVo){
        IPage<ProgramDO> list=programService.selectUseProgramList(programQueryVo);
        return list;
    }

    @ApiOperation(value = "查询节目详情")
    @GetMapping("/{programId}/{deviceId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programId", value = "节目ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "deviceId", value = "设备编号", required = true, dataType = "String", paramType = "path")})
    public PadProgramDto selectPadProgram(@PathVariable Integer programId,@PathVariable String deviceId){
        return programService.selectPadProgram(programId,deviceId);
    }
}
