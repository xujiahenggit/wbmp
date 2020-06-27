package com.bank.manage.controller;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.WbmpCommonCalcMethodDO;
import com.bank.manage.service.WbmpCommonCalcMethodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * 网点视图计算公式参数表 控制器
 *
 * @author zhaozhongyuan
 * @since 2020-06-18
 */
@RestController
@RequestMapping("/wbmpCommonCalcMethod")
@Api(value = "网点视图计算公式参数表", tags = "网点视图计算公式参数表接口")
public class WbmpCommonCalcMethodController {

	@Resource
	private WbmpCommonCalcMethodService wbmpCommonCalcMethodService;


	/**
	 * 详情
	 */
	@GetMapping("/{id}")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	@ApiOperation(value = "详情", notes = "传入id")
	public Object detail(@PathVariable String id) {
		return wbmpCommonCalcMethodService.getById(id);
	}

	/**
	 * 分页 网点视图计算公式参数表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入PageQueryModel")
	public Object list(@RequestBody PageQueryModel pageQueryModel) {
		return wbmpCommonCalcMethodService.listPage(pageQueryModel);
	}

	/**
	 * 新增或修改 网点视图计算公式参数表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入wbmpCommonCalcMethod")
	public Object save(@RequestBody WbmpCommonCalcMethodDO wbmpCommonCalcMethod) {
		return wbmpCommonCalcMethodService.save(wbmpCommonCalcMethod);
	}

	/**
	 * 修改 网点视图计算公式参数表
	 */
	@PostMapping("/update")
	@ApiOperation(value = "更新", notes = "传入wbmpCommonCalcMethod")
	public Object update(@RequestBody WbmpCommonCalcMethodDO wbmpCommonCalcMethod) {
		return wbmpCommonCalcMethodService.updateById(wbmpCommonCalcMethod);
	}


	/**
	 * 删除 网点视图计算公式参数表
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除", notes = "传入id")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	public Object remove(@PathVariable String id) {
		return wbmpCommonCalcMethodService.removeById(id);
	}


}
