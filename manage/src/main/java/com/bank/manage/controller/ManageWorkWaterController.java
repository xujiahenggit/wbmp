package com.bank.manage.controller;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.WorkWaterDO;
import com.bank.manage.service.ManageWorkWaterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * 工单流水表 控制器
 * @author 代码自动生成
 * @since 2020-07-13
 */
@RestController
@RequestMapping("/manageWorkWater")
@Api(value = "工单流水表", tags = "工单流水表接口")
public class ManageWorkWaterController {

	@Resource
	private ManageWorkWaterService manageWorkWaterService;


	/**
	 * 详情
	 */
	@GetMapping("/{id}")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	@ApiOperation(value = "详情", notes = "传入id")
	public Object detail(@PathVariable String id) {
		return manageWorkWaterService.getById(id);
	}

	/**
	 * 分页 工单流水表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入PageQueryModel")
	public Object list(@RequestBody PageQueryModel pageQueryModel) {
		return manageWorkWaterService.listPage(pageQueryModel);
	}

	/**
	 * 新增或修改 工单流水表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增或修改", notes = "传入workWater")
	public Object save(@RequestBody WorkWaterDO workWater) {
		return manageWorkWaterService.saveOrUpdate(workWater);
	}


	/**
	 * 删除 工单流水表
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除", notes = "传入id")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	public Object remove(@PathVariable String id) {
		return manageWorkWaterService.removeById(id);
	}


}
