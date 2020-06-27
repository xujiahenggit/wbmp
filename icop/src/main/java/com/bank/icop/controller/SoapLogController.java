package com.bank.icop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import com.bank.core.entity.PageQueryModel;
import org.springframework.web.bind.annotation.*;
import com.bank.icop.dos.SoapLogDO;
import com.bank.icop.vo.SoapLogVO;

import com.bank.icop.service.SoapLogService;
import javax.annotation.Resource;
/**
 * SOAP调用第三方接口日志 控制器
 *
 * @author zhaozhongyuan
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/soapLog")
@Api(value = "SOAP调用第三方接口日志", tags = "SOAP调用第三方接口日志接口")
public class SoapLogController {

	@Resource
	private SoapLogService soapLogService;


	/**
	 * 详情
	 */
	@GetMapping("/{id}")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	@ApiOperation(value = "详情", notes = "传入id")
	public Object detail(@PathVariable String id) {
		return soapLogService.getById(id);
	}

	/**
	 * 分页 SOAP调用第三方接口日志
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入PageQueryModel")
	public Object list(@RequestBody PageQueryModel pageQueryModel) {
		return soapLogService.listPage(pageQueryModel);
	}

	/**
	 * 新增或修改 SOAP调用第三方接口日志
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增或修改", notes = "传入soapLog")
	public Object save(@RequestBody SoapLogDO soapLog) {
		return soapLogService.saveOrUpdate(soapLog);
	}


	/**
	 * 删除 SOAP调用第三方接口日志
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除", notes = "传入id")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	public Object remove(@PathVariable String id) {
		return soapLogService.removeById(id);
	}


}
