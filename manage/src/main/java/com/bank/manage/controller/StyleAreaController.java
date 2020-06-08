package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.StyleAreaDO;
import com.bank.manage.dto.StyleAreaDTO;
import com.bank.manage.service.StyleAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "样式信息接口")
@RestController
@RequestMapping("/styleArea")
@Slf4j
public class StyleAreaController extends BaseController {

    @Autowired
    private StyleAreaService styleAreaService;


    @PostMapping("/saveStyleArea")
    @ApiOperation(value = "新增样式信息")
    @ApiImplicitParam(name = "styleAreaDTO", value = "样式信息", required = true, paramType = "body", dataType = "StyleAreaDTO")
    public Boolean saveStyleArea(@RequestBody StyleAreaDTO styleAreaDTO, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return styleAreaService.saveStyleArea(styleAreaDTO,tokenUserInfo);
    }


    @GetMapping("/{deviceType}")
    @ApiOperation(value = "根据设备类型查询样式信息")
    @ApiImplicitParam(name = "deviceType", value = "设备类型", required = true, paramType = "path")
    public List<StyleAreaDO> queryStyle(@PathVariable String deviceType){
        return styleAreaService.queryStyle(deviceType);
    }


}
