package com.bank.esb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import com.bank.core.entity.PageQueryModel;
import org.springframework.web.bind.annotation.*;
import com.bank.esb.dos.WorkWaterDO;
import com.bank.esb.vo.WorkWaterVO;

import com.bank.esb.service.WorkWaterService;
import javax.annotation.Resource;
/**
 * 工单流水表 控制器
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
@RestController
@RequestMapping("/workWater")
@Api(value = "工单流水表", tags = "工单流水表接口")
public class WorkWaterController {

	@Resource
	private WorkWaterService workWaterService;


	/**
	 * 详情
	 */
	@GetMapping("/{id}")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	@ApiOperation(value = "详情", notes = "传入id")
	public Object detail(@PathVariable String id) {
		return workWaterService.getById(id);
	}

	/**
	 * 分页 工单流水表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入PageQueryModel")
	public Object list(@RequestBody PageQueryModel pageQueryModel) {
		return workWaterService.listPage(pageQueryModel);
	}

	/**
	 * 新增或修改 工单流水表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增或修改", notes = "传入workWater")
	public Object save(@RequestBody WorkWaterDO workWater) {
		return workWaterService.saveOrUpdate(workWater);
	}


	/**
	 * 删除 工单流水表
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除", notes = "传入id")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	public Object remove(@PathVariable String id) {
		return workWaterService.removeById(id);
	}


}
