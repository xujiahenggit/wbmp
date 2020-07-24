package com.bank.manage.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dto.CountModuleDTO;
import com.bank.manage.service.CountModuleService;
import com.bank.manage.vo.CountModuleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *  统计模块主表 控制器
 *
 */
@RestController
@RequestMapping("/countModule")
@Api(value = "统计模块", tags = "快乐服务-统计模块接口")
public class CountModuleController extends BaseController {

    @Autowired
    private CountModuleService countModuleService;

    @PostMapping("/queryCountModulePage")
    @ApiOperation(value = "统计模块信息分页查询")
    public IPage<CountModuleDTO> queryCountModulePage(@RequestBody PageQueryModel pageQueryModel) {
        return countModuleService.queryCountModulePage(pageQueryModel);
    }

    @PostMapping("/saveCountModule")
    @ApiOperation(value = "新增统计模块信息")
    public Boolean saveCountModule(@RequestBody @ApiParam(value = "countModuleVoList列表") List<CountModuleVo> countModuleVo, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return countModuleService.saveCountModule(countModuleVo, tokenUserInfo);
    }

    @DeleteMapping("/delCountModule/{moduleYear}")
    @ApiOperation(value = "删除统计模块信息")
    @ApiImplicitParam(name = "moduleYear", value = "统计模块年份", required = true, paramType = "path")
    public Boolean delCountModule(@PathVariable String moduleYear) {
        return countModuleService.delCountModule(moduleYear);
    }

    @GetMapping("/getCountModule/{moduleYear}")
    @ApiOperation(value = "查询统计模块信息")
    @ApiImplicitParam(name = "moduleYear", value = "统计模块年份", required = true, paramType = "path")
    public List<CountModuleVo> getCountModule(@PathVariable String moduleYear) {
        return countModuleService.getCountModule(moduleYear);
    }

    @PutMapping("/putCountModule")
    @ApiOperation(value = "更新统计模块信息")
    public Boolean updateCountModule(@RequestBody @ApiParam(value = "countModuleVoList列表") List<CountModuleVo> countModuleVo, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return countModuleService.updateCountModule(countModuleVo, tokenUserInfo);
    }

    @GetMapping("/queryCountModule")
    @ApiOperation(value = "考核数据分析图统计模块接口")
    public List<Map<String, Object>> queryCountModule() {
        return countModuleService.queryCountModule("");
    }

}
