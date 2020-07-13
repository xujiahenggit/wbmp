package com.bank.esb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import com.bank.core.entity.PageQueryModel;
import org.springframework.web.bind.annotation.*;
import com.bank.esb.dos.WorkOrderDO;
import com.bank.esb.vo.WorkOrderVO;

import com.bank.esb.service.WorkOrderService;
import javax.annotation.Resource;
/**
 * 工单表 控制器
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
@RestController
@RequestMapping("/workOrder")
@Api(value = "工单表", tags = "工单表接口")
public class WorkOrderController {

	@Resource
	private WorkOrderService workOrderService;


	/**
	 * 详情
	 */
	@GetMapping("/{id}")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	@ApiOperation(value = "详情", notes = "传入id")
	public Object detail(@PathVariable String id) {
		return workOrderService.getById(id);
	}

	/**
	 * 分页 工单表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入PageQueryModel")
	public Object list(@RequestBody PageQueryModel pageQueryModel) {
		return workOrderService.listPage(pageQueryModel);
	}

	/**
	 * 新增或修改 工单表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增或修改", notes = "传入workOrder")
	public Object save(@RequestBody WorkOrderDO workOrder) {
		return workOrderService.saveOrUpdate(workOrder);
	}


	/**
	 * 删除 工单表
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除", notes = "传入id")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	public Object remove(@PathVariable String id) {
		return workOrderService.removeById(id);
	}


}
