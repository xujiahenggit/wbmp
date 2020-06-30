package com.bank.manage.controller;

import com.bank.core.enums.ConstantEnum;
import com.bank.core.sysConst.WbmpConstFile;
import com.bank.manage.dto.CustomerAvgDto;
import com.bank.manage.dto.HouseRaceDto;
import com.bank.manage.dto.WbmpAbsAtmTranInfoDto;
import com.bank.manage.dto.WbmpOperateBqmsQueueAvgDto;
import com.bank.manage.service.*;
import com.bank.manage.util.Tools;
import com.bank.manage.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "运营看板首页接口")
@RestController
@RequestMapping("/operateBoardHome")
public class WbmpOperateHomeController {

    @Autowired
    private WbmpOperateBqmsQueueAvgService wbmpOperateBqmsQueueAvgService;

    @Autowired
    private WbmpOperateHomeService wbmpOperateHomeService;

    @Autowired
    private WbmpAbsTransinfoService wbmpAbsTransinfoService;

    //运营
    @Autowired
    private WbmpMangementScoreService wbmpMangementScoreService;

    //经营
    @Autowired
    private WbmpOperateScoreService wbmpOperateScoreService;

    //客群
    @Autowired
    private WbmpOperateCustService wbmpOperateCustService;

    //赛马制
    @Autowired
    private WbmpOperateRacingIndexMService wbmpOperateRacingIndexMService;

    @ApiOperation("运营曲线图")
    @PostMapping("/operateCurve")
    public Map<String, Object> operateCurve(@RequestBody @Validated OperateCurveVo operateCurveVo) {
        Map<String, Object> resutlMap = new HashMap<String, Object>();
        //折线图Map
        Map<String, Object> charts = new HashMap<String, Object>();
        charts.put("yAxisName", "分数");
        //X轴时间数组
        List<String> xAxis = new ArrayList<String>();
        //X轴具体时间
        List<String> xDetailDate = new ArrayList<String>();
        //经营数据
        List<String> engagedData = new ArrayList<String>();
        //运营数据
        List<String> operationData = new ArrayList<String>();

        if (operateCurveVo.getQueryType() != null) {
            String queryType = operateCurveVo.getQueryType();
            resutlMap.put("queryType", queryType);
            switch (queryType) {
                case WbmpConstFile.DATE_TYPE_YEAR:
                    resutlMap.put("cycleName", "年");
                    charts.put("xAxisName", "年");
                    break;
                case WbmpConstFile.DATE_TYPE_JIDU:
                    resutlMap.put("cycleName", "季度");
                    charts.put("xAxisName", "季度");
                    break;
                case WbmpConstFile.DATE_TYPE_MONTH:
                    resutlMap.put("cycleName", "月");
                    charts.put("xAxisName", "月");
                    break;
            }
            //x轴周期数组
            xAxis = Tools.getXAxis(queryType);

            xDetailDate = Tools.getXDateDetail(queryType);
            charts.put("xAxis", xAxis);
        }
        //运营
        operationData = this.wbmpMangementScoreService.calcManageScore(operateCurveVo.getOrgId(), xDetailDate, operateCurveVo.getQueryType());
        charts.put("operationData", operationData);
        //经营
        engagedData = this.wbmpOperateScoreService.calcOperateScore(operateCurveVo.getOrgId(), xDetailDate, operateCurveVo.getQueryType());
        charts.put("engagedData", engagedData);
        resutlMap.put("charts", charts);
        return resutlMap;
    }

    @ApiOperation(value = "运营红灰榜Top5-全行当月数据", notes = "Map数据返回，红榜数据字段redRank，灰榜数据字段garyRank")
    @GetMapping("/operateRedGrayRank")
    public Map<String, List<OperateRankVO>> operateRedGrayRank() {
        Map<String, List<OperateRankVO>> resultData = new HashMap<>();
        List<OperateRankVO> redRank = this.wbmpMangementScoreService.findRedTop();
        List<OperateRankVO> garyRank = this.wbmpMangementScoreService.findGreyTop();
        resultData.put("redRank", redRank);
        resultData.put("garyRank", garyRank);
        return resultData;
    }

    @ApiOperation("客户满意度信息")
    @GetMapping("/customSatisfaction/{orgId}")
    public WbmpOperateBqmsQueueAvgDto customSatisfaction(@PathVariable String orgId) {
        return this.wbmpOperateBqmsQueueAvgService.getOperraInfo(orgId);
    }

    @ApiOperation("柜面自助交易占比")
    @GetMapping("/counterDealProportion/{orgId}")
    public WbmpAbsAtmTranInfoDto counterDealProportion(@PathVariable String orgId) {
        return this.wbmpAbsTransinfoService.counterDealProportion(orgId);
    }

    @ApiOperation("赛马制考核指标")
    @GetMapping("/racingAssessIndex/{orgId}")
    public HouseRaceDto racingAssessIndex(@PathVariable String orgId) {
        HouseRaceDto houseRaceDto = this.wbmpOperateRacingIndexMService.racingAssessIndex(orgId);
        return houseRaceDto;
    }

    @ApiOperation("网点存款时点余额")
    @GetMapping("/branchDepositBalance/{orgId}/{depositTypeCode}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path"),
            @ApiImplicitParam(name = "depositTypeCode", value = "存款类型(00:储蓄存款;01:一般性存款;02:对公存款;)", defaultValue = "00", required = true, paramType = "path")
    })
    public BranchDepositBalanceVO branchDepositBalance(@PathVariable String orgId, @PathVariable String depositTypeCode) {
        Map<String, Object> result = this.wbmpOperateHomeService.queryBranchDepositBalance(orgId, depositTypeCode);

        OperateChartsData charts = OperateChartsData.builder()
                .xAxisName("时间")
                .yAxisName("亿元")
                .data((List<BigDecimal>) result.get("data"))
                .xAxis((List<String>) result.get("xAxis"))
                .build();

        BranchDepositBalanceVO balanceVO = BranchDepositBalanceVO.builder()
                .deposit((String) result.get("deposit"))
                .depositTypeCode(depositTypeCode)
                .depositTypeName(ConstantEnum.valueOf("DEPOSIT_TYPE_" + depositTypeCode).getDesc())
                .charts(charts)
                .build();

        return balanceVO;
    }

    @ApiOperation("客群增长")
    @PostMapping("/customerIncrease")
    public CustomerAvgDto customerIncrease(@RequestBody CustomerAvgVo customerAvgVo) {
        return this.wbmpOperateCustService.getCustomerImg(customerAvgVo);
    }

    @ApiOperation("获取默认机构以及机构层级信息")
    @GetMapping("/queryOrgTierInfo/{orgId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path")
    })
    public Map<String, Object> queryOrgTierInfo(@PathVariable String orgId) {
        return this.wbmpOperateHomeService.queryOrgTierInfo(orgId);
    }
}
