package com.bank.esb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import com.bank.core.entity.PageQueryModel;
import org.springframework.web.bind.annotation.*;
import com.bank.esb.dos.EsbLogDO;
import com.bank.esb.vo.EsbLogVO;

import com.bank.esb.service.EsbLogService;
import javax.annotation.Resource;
/**
 *  控制器
 *
 * @author 代码自动生成
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/esbLog")
@Api(value = "", tags = "接口")
public class EsbLogController {

	@Resource
	private EsbLogService esbLogService;


	/**
	 * 详情
	 */
	@GetMapping("/{id}")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	@ApiOperation(value = "详情", notes = "传入id")
	public Object detail(@PathVariable String id) {
		return esbLogService.getById(id);
	}

	/**
	 * 分页 
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入PageQueryModel")
	public Object list(@RequestBody PageQueryModel pageQueryModel) {
		return esbLogService.listPage(pageQueryModel);
	}

	/**
	 * 新增或修改 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增或修改", notes = "传入esbLog")
	public Object save(@RequestBody EsbLogDO esbLog) {
		return esbLogService.saveOrUpdate(esbLog);
	}


	/**
	 * 删除 
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除", notes = "传入id")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	public Object remove(@PathVariable String id) {
		return esbLogService.removeById(id);
	}


}
