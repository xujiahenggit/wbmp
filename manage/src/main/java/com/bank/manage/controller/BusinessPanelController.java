package com.bank.manage.controller;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.service.BusinessPanelService;
import com.bank.manage.vo.AbsTellerInfo;
import com.bank.manage.vo.TellerOnlineInfo;
import com.bank.manage.vo.TransCntInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
     *  运营看板统计
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "删除", notes = "传入id")
    @ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
    public Object getTellerOnlineTime(@PathVariable String id) {
        List<TellerOnlineInfo> list = businessPanelService.getTellerOnlineInfo();
        return list;
    }

    @GetMapping("/deviceStatusInfo/{orgId}")
    @ApiOperation(value = "自助设备在线状态",notes = "返回值：deviceTotal：设备总数；deviceTypeTotal：种类")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path")
    public Object deviceStatus(@PathVariable String orgId) {
        List<Map<String,Integer>> list = businessPanelService.deviceStatus(orgId);
        return list;
    }

    @GetMapping("/deviceTotalInfo/{orgId}")
    @ApiOperation(value = "某一机构下，设备总数，智能设备总类数",notes = "返回值：deviceTotal：设备总数；deviceTypeTotal：种类")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path")
    public Object deviceStatusInfo(@PathVariable String orgId) {
        Map<String,Integer> list = businessPanelService.deviceTotal(orgId);
        return list;
    }

    @GetMapping("/transCnfTop5/{orgId}")
    @ApiOperation(value = "自助设备交易量Top5")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path")
    public Object transCnfTop5(@PathVariable String orgId) {
        List<TransCntInfo> list = businessPanelService.devicetransCnfTop5(orgId);
        return list;
    }

    @GetMapping("/tradeVolumeTop5/{orgId}")
    @ApiOperation(value = "柜面设备交易量Top5")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path")
    public Object tradeVolumeTop5(@PathVariable String orgId) {
        List<TransCntInfo> list = businessPanelService.tradeVolumeTop5(orgId);
        return list;
    }

    @GetMapping("/tellertPageList/{orgId}")
    @ApiOperation(value = "柜面设备交易量Top5")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path")
    public Object tellertPageList(@PathVariable String orgId, PageQueryModel pageQueryModel) {
        IPage<AbsTellerInfo> list = businessPanelService.tellertPageList(orgId,pageQueryModel);
        return list;
    }


    @GetMapping("/queryOperation/{branch}")
    @ApiOperation(value = "运营服务查询")
    @ApiImplicitParam(name = "branch", value = "机构号", required = true, paramType = "path")
    public List<Map<String,Object>> queryOperation(@PathVariable String branch){
        return businessPanelService.queryOperation(branch);
    }


}
