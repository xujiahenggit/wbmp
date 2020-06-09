package com.bank.manage.controller;

import com.bank.manage.service.BusinessPanelService;
import com.bank.manage.vo.TellerOnlineInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 运营看板统计 控制器
 *
 * @author zhaozhongyuan
 * @since 2020-06-10
 */
@RestController
@RequestMapping("/businessPanel")
@Api(value = "运营看板统计", tags = "运营看板统计接口")
public class BusinessPanelController {

    @Resource
    private BusinessPanelService businessPanelService;

    /**
     * 删除 运营看板统计
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "删除", notes = "传入id")
    @ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
    public Object getTellerOnlineTime(@PathVariable String id) {
        List<TellerOnlineInfo> list = businessPanelService.getTellerOnlineInfo();
        return list;
    }


}
