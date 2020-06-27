package com.bank.manage.controller;

import com.bank.manage.service.WbmpTellerTransactionService;
import com.bank.manage.vo.TellerTransactionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = "运营看板--柜员交易统计模块")
@RestController
@RequestMapping("/tellerTransaction")
public class WbmpTellerTransactionController {

    @Autowired
    private WbmpTellerTransactionService wbmpTellerTransactionService;



    @GetMapping("/queryTransaction/{orgId}/{tellerId}")
    @ApiOperation(value = "柜员：柜员交易分析",notes = "trade_volume: 交易量, tran_name: 交易名称, count: 交易总数, tran_lv: 交易占比")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "机构号", required = true, paramType = "path"),
            @ApiImplicitParam(name = "tellerId", value = "柜员号", required = true, paramType = "path")
    })
    public List<Map<String,Object>> queryTransaction(@PathVariable("orgId") String orgId, @PathVariable("tellerId") String tellerId){
        return wbmpTellerTransactionService.queryTransaction(orgId,tellerId);
    }

}
