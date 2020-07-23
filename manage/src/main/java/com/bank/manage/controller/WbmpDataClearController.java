package com.bank.manage.controller;

import com.bank.manage.service.WbmpDataClearService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 运营看板数据清理类
 * @author zhangfuqiang
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/wbmpDateClear")
@Api(value = "运营看板数据清理类", tags = "运营视图数据清理类")
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



}
