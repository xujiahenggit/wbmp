package com.bank.manage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import com.bank.core.entity.PageQueryModel;
import org.springframework.web.bind.annotation.*;
import com.bank.manage.dos.WbmpOrgBalanceDO;
import com.bank.manage.vo.WbmpOrgBalanceVO;

import com.bank.manage.service.WbmpOrgBalanceService;
import javax.annotation.Resource;
/**
 * 网点机构存款表 控制器
 *
 * @author zhaozhongyuan
 * @since 2020-06-22
 */
@RestController
@RequestMapping("/wbmpOrgBalance")
@Api(value = "网点机构存款表", tags = "网点机构存款表接口")
public class WbmpOrgBalanceController {

	@Resource
	private WbmpOrgBalanceService wbmpOrgBalanceService;


	/**
	 * 详情
	 */
	@GetMapping("/{id}")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	@ApiOperation(value = "详情", notes = "传入id")
	public Object detail(@PathVariable String id) {
		return wbmpOrgBalanceService.getById(id);
	}

	/**
	 * 分页 网点机构存款表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入PageQueryModel")
	public Object list(@RequestBody PageQueryModel pageQueryModel) {
		return wbmpOrgBalanceService.listPage(pageQueryModel);
	}

	/**
	 * 新增或修改 网点机构存款表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增或修改", notes = "传入wbmpOrgBalance")
	public Object save(@RequestBody WbmpOrgBalanceDO wbmpOrgBalance) {
		return wbmpOrgBalanceService.saveOrUpdate(wbmpOrgBalance);
	}


	/**
	 * 删除 网点机构存款表
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除", notes = "传入id")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	public Object remove(@PathVariable String id) {
		return wbmpOrgBalanceService.removeById(id);
	}


}
