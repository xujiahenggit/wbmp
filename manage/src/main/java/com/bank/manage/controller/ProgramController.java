package com.bank.manage.controller;

import com.bank.core.sysConst.ConstFile;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.NetUtil;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dto.MaterialDTO;
import com.bank.manage.dto.Progame.ProgramPreviewDTO;
import com.bank.manage.dto.ProgramListDTO;
import com.bank.manage.dto.ProgramUpdate.ProgramStyleDto;
import com.bank.manage.dto.ProgramUpdate.ProgramUpdateDto;
import com.bank.manage.service.ProgramService;
import com.bank.manage.vo.ProgramQueryByDeviceVo;
import com.bank.manage.vo.ProgramUpdateVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/24 14:53
 */
@Api(tags = "节目管理")
@RestController
@RequestMapping("/program")
public class ProgramController {

    @Resource
    private ProgramService programService;
    @Resource
    private ConfigFileReader configFileReader;
    @Resource
    NetUtil netUtil;
    @ApiOperation(value = "节目预览")
    @GetMapping("/{programId}/{deviceId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programId", value = "节目ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "deviceId", value = "设备编号", required = true, dataType = "String", paramType = "path")})
    public ProgramPreviewDTO getPreView(@PathVariable Integer programId,@PathVariable String deviceId) {
        ProgramPreviewDTO programPreviewDTO = programService.getPreView(programId,deviceId);
        return programPreviewDTO;
    }

    @ApiOperation(value = "获取节目详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programId", value = "节目ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "deviceId", value = "设备编号", required = true, dataType = "String", paramType = "path"),
    })
    @GetMapping("/info/{programId}/{deviceId}")
    public ProgramUpdateDto getProgramInfo(@PathVariable Integer programId, @PathVariable String deviceId){
        ProgramUpdateDto programUpdateDto=programService.getProgramInfo(programId,deviceId);
        return programUpdateDto;
    }


    //@ApiOperation(value = "获取节目已选素材列表")
//    @GetMapping("/material/{programId}/{deviceId}/{areaId}")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "programId", value = "节目ID", required = true, dataType = "int", paramType = "path"),
//            @ApiImplicitParam(name = "deviceId", value = "设备编号", required = true, dataType = "String", paramType = "path"),
//            })
    public List<MaterialDTO> getMaterialList(@PathVariable Integer programId, @PathVariable String deviceId) {
        List<MaterialDTO> list = programService.getMaterialList(programId,deviceId);
        return list;
    }

    @ApiOperation("通过设备编号 获取节目列表")
    @PostMapping("/programlist")
    public IPage<ProgramListDTO> getProgramList(@RequestBody ProgramQueryByDeviceVo programQueryByDeviceVo){
        IPage<ProgramListDTO> page=programService.getProgramList(programQueryByDeviceVo);
        return page;
    }

    @ApiOperation("通过节目/设备编号 获取样式")
    @GetMapping("/style/{programId}/{deviceId}")
    public ProgramStyleDto getStyle(@PathVariable Integer programId, @PathVariable String deviceId){
        ProgramStyleDto styleAreaDO=programService.getStyle(programId,deviceId);
        if( styleAreaDO != null){
            styleAreaDO.setStylePath(netUtil.getUrlSuffix("")+styleAreaDO.getStylePath());
        }
        return styleAreaDO;
    }

    @ApiOperation("更新节目")
    @SystemLog(logModul = ConstFile.MODULE_PROGRAM,logType = ConstFile.TYPE_UPDATE,logDesc = "更新节目")
    @PostMapping("/update")
    public Boolean updateProgram(@RequestBody ProgramUpdateVo programUpdateVo){
        return programService.updatePrograme(programUpdateVo);
    }

    @ApiOperation("切换节目")
    @SystemLog(logModul = ConstFile.MODULE_PROGRAM,logType = ConstFile.TYPE_CHANGE,logDesc = "切换节目")
    @PutMapping("/change/{programId}/{deviceId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "programId", value = "节目ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "deviceId", value = "设备编号", required = true, dataType = "String", paramType = "path")})
    public Boolean changeProgram(@PathVariable Integer programId,@PathVariable String deviceId){
        return programService.changeProgram(programId,deviceId);
    }
}
