package com.bank.manage.controller;

import cn.hutool.core.util.IdUtil;
import com.bank.auth.base.BaseController;
import com.bank.core.entity.BizException;
import com.bank.core.entity.FileDo;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.FileUploadUtils;
import com.bank.manage.dos.ActivitieSalonDO;
import com.bank.manage.service.ActivitieSalonService;
import com.bank.manage.vo.CutActivitieSalonVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 活动沙龙  控制器
 *
 */
@RestController
@RequestMapping("/activitieSalon")
@Api(value = "活动沙龙 ", tags = "活动沙龙 接口")
public class ActivitieSalonController extends BaseController {

	@Resource
	private ActivitieSalonService activitieSalonService;


	@Autowired
	private ConfigFileReader configFileReader;

	/**
	 * 详情
	 */
	@GetMapping("/{id}")
	@ApiImplicitParam(name = "id", value = "唯一标识", required = true, paramType = "path")
	@ApiOperation(value = "详情", notes = "传入id")
	public Object detail(@PathVariable String id) {
		return activitieSalonService.getById(id);
	}

	/**
	 * 分页 活动沙龙 
	 */
	@PostMapping("/list")
	@ApiOperation(value = "分页", notes = "传入PageQueryModel")
	public Object list(@RequestBody PageQueryModel pageQueryModel) {
		return activitieSalonService.listPage(pageQueryModel);
	}

	/**
	 * 新增 活动沙龙
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入activitieSalon")
	public Boolean save(@RequestBody ActivitieSalonDO activitieSalon,HttpServletRequest request) {
		TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
		return activitieSalonService.saveActivitieSalon(activitieSalon,tokenUserInfo);
	}

	/**
	 * 更新 活动沙龙
	 */
	@PutMapping("/update")
	@ApiOperation(value = "更新", notes = "传入activitieSalon")
	public Boolean update(@RequestBody ActivitieSalonDO activitieSalon,HttpServletRequest request) {
		TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
		ActivitieSalonDO salonDO = new ActivitieSalonDO();
		salonDO.setId(activitieSalon.getId());
		salonDO.setActivitieName(activitieSalon.getActivitieName());
		salonDO.setActivitieType(activitieSalon.getActivitieType());
		salonDO.setActivitieDesc(activitieSalon.getActivitieDesc());
		salonDO.setActivitieStatus(activitieSalon.getActivitieStatus());
		salonDO.setUpdatedBy(tokenUserInfo.getUserId());
		salonDO.setUpdatedTime(LocalDateTime.now());
		return activitieSalonService.updateById(salonDO);
	}

	/**
	 * 删除 活动沙龙 
	 */
	@DeleteMapping("/delete")
	@ApiOperation(value = "删除", notes = "传入id")
	public Boolean remove(@RequestBody @ApiParam(value = "活动沙龙主键列表") List<Integer> ids) {
		return activitieSalonService.removeActivitieSalonByIds(ids);
	}


	@PostMapping("/queryActivitieSalon")
	@ApiOperation(value = "PAD端查询活动沙龙数据",notes = "orgId:机构号")
	@ApiImplicitParam(name = "pageQueryModel", value = "活动沙龙数据分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
	public IPage<ActivitieSalonDO> queryActivitieSalon(@RequestBody PageQueryModel pageQueryModel){
		return activitieSalonService.queryActivitieSalon(pageQueryModel);
	}



	@PostMapping("/cutActivitieSalon")
	@ApiOperation("PAD端活动沙龙切换")
	@ApiImplicitParam(name = "cutActivitieSalonVo", value = "PAD端活动沙龙切换模型", required = true, paramType = "body", dataType = "CutActivitieSalonVo")
	public void cutActivitieSalon(@RequestBody CutActivitieSalonVo cutActivitieSalonVo){
		activitieSalonService.cutActivitieSalon(cutActivitieSalonVo);
	}


	@PostMapping("/uploadFile")
	@ApiOperation(value = "活动沙龙-文件上传")
	public FileDo upFileForShare(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request){
		FileDo fileDo=new FileDo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//第一层目录 按 日期创建
		String fist_tab=sdf.format(new Date());
		//上传路径
		String uploadPath=configFileReader.getACTIVITIE_FILE_PATH()+"/"+fist_tab;
		//访问路径
		String accessPath=configFileReader.getACTIVITIE_ACCESS_PATH()+"/"+fist_tab;
		//原文件名称
		String filename = file.getOriginalFilename();
		//用UUID
		String c_fileName= IdUtil.randomUUID()+filename.substring(filename.lastIndexOf("."));;
		try {
			fileDo = FileUploadUtils.FileUpload(file,uploadPath,accessPath,c_fileName);
		} catch (Exception e) {
			throw new BizException("活动沙龙-文件上传！");
		}
		return fileDo;
	}

}
