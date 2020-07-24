package com.bank.manage.controller;

import com.bank.manage.service.WbmpDataClearService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * 运营看板数据清理类
 * @author zhangfuqiang
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/wbmpDateClear")
@Api(value = "运营看板数据清理类", tags = "运营视图数据清理类")
@Slf4j
public class WbmpDataClearController {

	@Resource
	private WbmpDataClearService wbmpDataClearService;


	/**
	 * 备份柜面交易明细表
	 */
	@GetMapping("/bakWbmpAbsTransinfo/{date}")
	@ApiImplicitParam(name = "date", value = "日期", required = true, paramType = "path")
	@ApiOperation(value = "date", notes = "日期")
	public Object bakWbmpAbsTransinfo(@PathVariable String date) {
		return wbmpDataClearService.bakWbmpAbsTransinfo(date);
	}


	@GetMapping("/clearData")
	public Object clearData() {
		String now = LocalDate.now().toString();
		log.info("******************日期"+now+"开始清理数据*********************");
        Boolean flag = false;
		//一天前
		String oneDate = LocalDate.now().minusDays(1).toString();
		//三天前
		String threeDate = LocalDate.now().minusDays(3).toString();
		//上个月
		String oneMonth = LocalDate.now().minusMonths(1).toString();
		//三个月
		String threeMonth = LocalDate.now().minusMonths(3).toString();
		//六个月
		String sixMonth = LocalDate.now().minusMonths(6).toString();
		//一年前
		String oneYear = LocalDate.now().minusYears(1).toString();
		//三年前
		String threeYear = LocalDate.now().minusYears(1).toString();

		//备份wbmp_abs_transinfo  date-上一日的日期【柜面交易明细表1】
		wbmpDataClearService.bakWbmpAbsTransinfo(oneDate);
		//删除上一日的数据 date-上一日的日期 【柜面交易明细表2】
		wbmpDataClearService.deleteWbmpAbsTransinfo(oneDate);
		//删除历史表小于一个月前的数据 date-上一日的日期 【柜面交易明细表3】
		wbmpDataClearService.deleteWbmpAbsTransinfoH(oneMonth);

		//删除wbmp_abs_teller_online_time 3天前的数据【柜员在线时长信息表】
		wbmpDataClearService.deleteWbmpAbsTellerOnlineTime(threeDate);

		//删除wbmp_abs_trade_time 3天前的数据【柜员交易耗时表】
		wbmpDataClearService.deleteWbmpAbsTradeTime(threeDate);

		//备份wbmp_abs_transinfo  date-上一日的日期【自助设备交易表1】
		wbmpDataClearService.bakWbmpAtmpTranInfo(oneDate);

		//删除上一日的数据 date-上一日的日期 【自助设备交易表2】
		wbmpDataClearService.deleteWbmpAtmpTranInfo(oneDate);

		//删除历史表小于一年前的数据 date-上一年的数据 【自助设备交易表3】
		wbmpDataClearService.deleteWbmpAtmpTranInfoH(oneYear);

		//删除上一日的数据 date-上一日的日期 【排队机】
		wbmpDataClearService.deleteWbmpBqmsQueue(threeDate);

		//删除三个月前的数据 date-三月前的日期 【客户满意度参数表T+1】
		wbmpDataClearService.deleteWbmpOperateBqmsQueueAvg(threeMonth);

		//删除上个月的数据 date-上个月的日期 【网点AUM表T+1】
		wbmpDataClearService.deleteWbmpOperateIndexAum(threeMonth);

		//删除三个月前的数据 date-三月前的日期 【赛马制表T+1】
		wbmpDataClearService.deleteWbmpOperateRacingIndexM(sixMonth);

		//删除三天前的数据 date-三天前的日期 【网点实时余额】
		wbmpDataClearService.deleteWbmpOrgBalance(oneMonth);

		//删除一个月前的数据 date-一月前的日期 【网点历史离线余额】
		wbmpDataClearService.deleteWbmpOrgBatchBalance(sixMonth);


		//备份wbmp_operate_cust  date-上一月的日期【客群指标表 T+1】1
		wbmpDataClearService.bakWbmpOperateCust(oneMonth);

		//删除wbmp_operate_cust  date-上一月的日期【客群指标表 T+1】2
		wbmpDataClearService.deleteWbmpOperateCust(oneMonth);

		//删除wbmp_operate_cust_h  date-三年前的日期【客群指标表 T+1】3
		wbmpDataClearService.deleteWbmpOperateCustH(threeYear);
		log.info("******************日期"+now+"结束清理数据*********************");
		flag = true;
		return flag;
	}
}
