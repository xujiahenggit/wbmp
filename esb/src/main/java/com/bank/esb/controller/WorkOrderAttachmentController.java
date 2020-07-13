package com.bank.esb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import com.bank.core.entity.PageQueryModel;
import org.springframework.web.bind.annotation.*;
import com.bank.esb.dos.WorkOrderAttachmentDO;
import com.bank.esb.vo.WorkOrderAttachmentVO;

import com.bank.esb.service.WorkOrderAttachmentService;
import javax.annotation.Resource;
/**
 * 处理附件表 控制器
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
@RestController
@RequestMapping("/workOrderAttachment")
@Api(value = "处理附件表", tags = "处理附件表接口")
public class WorkOrderAttachmentController {

	@Resource
	private WorkOrderAttachmentService workOrderAttachmentService;


	/**
	 * 详情
	 */
	@GetMapping("/{id}")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	@ApiOperation(value = "详情", notes = "传入id")
	public Object detail(@PathVariable String id) {
		return workOrderAttachmentService.getById(id);
	}

	/**
	 * 分页 处理附件表
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入PageQueryModel")
	public Object list(@RequestBody PageQueryModel pageQueryModel) {
		return workOrderAttachmentService.listPage(pageQueryModel);
	}

	/**
	 * 新增或修改 处理附件表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增或修改", notes = "传入workOrderAttachment")
	public Object save(@RequestBody WorkOrderAttachmentDO workOrderAttachment) {
		return workOrderAttachmentService.saveOrUpdate(workOrderAttachment);
	}


	/**
	 * 删除 处理附件表
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除", notes = "传入id")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	public Object remove(@PathVariable String id) {
		return workOrderAttachmentService.removeById(id);
	}


}
