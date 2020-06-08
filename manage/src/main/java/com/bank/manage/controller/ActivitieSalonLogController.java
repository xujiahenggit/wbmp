package com.bank.manage.controller;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.ActivitieSalonLogDO;
import com.bank.manage.service.ActivitieSalonLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * 活动沙龙流水  控制器
 *
 */
@RestController
@RequestMapping("/activitieSalonLog")
@Api(value = "活动沙龙流水 ", tags = "活动沙龙流水 接口")
public class ActivitieSalonLogController {

	@Resource
	private ActivitieSalonLogService activitieSalonLogService;




	/**
	 * 分页 活动沙龙流水 
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入PageQueryModel")
	public Object list(@RequestBody PageQueryModel pageQueryModel) {
		return activitieSalonLogService.listPage(pageQueryModel);
	}

	/**
	 * 新增 活动沙龙流水
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入activitieSalonLog")
	public Boolean save(@RequestBody ActivitieSalonLogDO activitieSalonLog) {
		return activitieSalonLogService.saveActivitieSalonLog(activitieSalonLog);
	}


}
