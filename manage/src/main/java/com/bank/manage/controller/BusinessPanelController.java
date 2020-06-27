package com.bank.manage.controller;

import com.bank.manage.service.BusinessPanelService;
import com.bank.manage.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @GetMapping("/deviceStatusInfo/{orgId}")
    @ApiOperation(value = "自助设备在线状态", notes = "返回值：deviceTotal：设备总数；deviceTypeTotal：种类")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path")
    public Object deviceStatus(@PathVariable String orgId) {
        List<Map<String, Integer>> list = this.businessPanelService.deviceStatus(orgId);
        return list;
    }

    @GetMapping("/deviceTotalInfo/{orgId}")
    @ApiOperation(value = "某一机构下，设备总数，智能设备总类数", notes = "返回值：deviceTotal：设备总数；deviceTypeTotal：种类")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path")
    public Object deviceStatusInfo(@PathVariable String orgId) {
        Map<String, Integer> list = this.businessPanelService.deviceTotal(orgId);
        return list;
    }

    @GetMapping("/transCnfTop5/{orgId}")
    @ApiOperation(value = "自助设备交易量Top5")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path")
    public Object transCnfTop5(@PathVariable String orgId) {
        List<TransCntInfo> list = this.businessPanelService.devicetransCnfTop5(orgId);
        return list;
    }

    @GetMapping("/tradeVolumeTop5/{orgId}")
    @ApiOperation(value = "柜面设备交易量Top5")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path")
    public Object tradeVolumeTop5(@PathVariable String orgId) {
        List<TransCntInfo> list = this.businessPanelService.tradeVolumeTop5(orgId);
        return list;
    }

    @GetMapping("/tellerList/{orgId}")
    @ApiOperation(value = "柜面,柜员详情信息")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path")
    public Object tellertPageList(@PathVariable String orgId) {
        List<AbsTellerInfo> absTellerInfos = this.businessPanelService.tellertPageList(orgId);
        return absTellerInfos;
    }

    @GetMapping("/RankInfo/{tellerId}")
    @ApiOperation(value = "柜面：柜员排名信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "机构号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "tellerId", value = "柜员号", required = true, paramType = "path")
    })
    public RankInfo rankInfo(@RequestParam(value = "orgId", required = false) String orgId, @PathVariable String tellerId) {
        return this.businessPanelService.rankInfo(orgId, tellerId);
    }

    @GetMapping("/queryOperation/{branch}")
    @ApiOperation(value = "运营服务查询", notes = "bargraphMap: { " +
            " abandoned_cnt: 弃号总数, " +
            " take_cnt: 取号总数," +
            " progress_handle_cnt: 办理中总数," +
            " already_handle_cnt: 已办理总数," +
            " queue_cnt: 排队总数" +
            "    }," +
            " panelMap: {" +
            " abandoned_lv: 弃号率," +
            "  wait_time: 预计等待时间," +
            " queue_cnt: 当前排队人数" +
            " }")
    @ApiImplicitParam(name = "branch", value = "机构号", required = true, paramType = "path")
    public Map<String, Object> queryOperation(@PathVariable String branch) {
        return this.businessPanelService.queryOperation(branch);
    }

    @GetMapping("/orgDeviceList/{orgId}")
    @ApiOperation(value = "网点设备列表")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path")
    public  List<DeviceInfoVo> orgDeviceList(@PathVariable String orgId) {
        List<DeviceInfoVo> list = new ArrayList<>();
        list = businessPanelService.getOrgDeviceList(orgId);
        return list;
    }

    @GetMapping("/deviceDetail/{deviceId}")
    @ApiOperation(value = "设备详情")
    @ApiImplicitParam(name = "deviceId", value = "设备id", required = true, paramType = "path")
    public DeviceDetailInfoVo deviceDetail(@PathVariable String deviceId) {
        DeviceDetailInfoVo deviceDetail = businessPanelService.findByDeviceId(deviceId);
        return deviceDetail;
    }

    @GetMapping("/deviceTradeList/{termNo}/{queryType}")
    @ApiOperation(value = "自助设备交易趋势")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "机构号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "termNo", value = "终端编号", required = true, paramType = "path"),
            @ApiImplicitParam(name = "queryType", value = "按月查询-01，按年查询-02", required = true, paramType = "path")
    })
    public List<DeviceTradeTrendVo> deviceTradeList(@RequestParam(value = "orgId", required = false) String orgId,@PathVariable String termNo, @PathVariable String queryType) {
        List<DeviceTradeTrendVo> list = businessPanelService.deviceTradeList(orgId,termNo,queryType);
        return list;
    }



}
