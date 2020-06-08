package com.bank.manage.controller;

import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.ConfigFileReader;
import com.bank.manage.dos.VersionsDO;
import com.bank.manage.service.VersionsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 应用版本维护 控制器
 *
 * @author zhaozhongyuan
 * @since 2020-06-01
 */
@Slf4j
@RestController
@RequestMapping("/versions")
@Api(value = "应用版本维护", tags = "应用版本维护接口")
public class VersionsController {

	@Resource
	private VersionsService versionsService;


	/**
	 * 详情
	 */
	@GetMapping("/{id}")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	@ApiOperation(value = "详情", notes = "传入id")
	public Object detail(@PathVariable String id) {
		return versionsService.getById(id);
	}

	/**
	 * 分页 应用版本维护
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入PageQueryModel")
	public Object list(@RequestBody PageQueryModel pageQueryModel) {
		return versionsService.listPage(pageQueryModel);
	}

	/**
	 * 新增或修改 应用版本维护
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增或修改", notes = "传入versions")
	public Object save(@RequestBody VersionsDO versions) {
		return versionsService.saveOrUpdate(versions);
	}


	/**
	 * 删除 应用版本维护
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除", notes = "传入id")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	public Object remove(@PathVariable String id) {
		return versionsService.removeById(id);
	}

	@Resource
	ConfigFileReader configFileReader;


	@PostMapping("/getLastVersion")
	@ApiOperation(value = "根据应用编号获取最新的版本号",notes = "必传参数：appNo;返回值为-1是没有查询到信息，否则为最新的版本号")
	@ApiImplicitParam(name = "appNo", value = "应用编号", required = true)
	public Object getMaxVersion(String appNo) {
		List<VersionsDO> list = versionsService.list(new LambdaQueryWrapper<VersionsDO>().eq(VersionsDO::getAppNo, appNo));
		Optional<VersionsDO> first = list.stream().sorted(Comparator.comparingInt(VersionsDO::getAppVersion).reversed()).findFirst();
		if (first.isPresent()){
			VersionsDO versionsDO = first.get();
			versionsDO.setAppSavePath(configFileReader.getTomcatBaseIp()+versionsDO.getAppSavePath());
			return versionsDO;
		}
		return Optional.empty();
	}


}
