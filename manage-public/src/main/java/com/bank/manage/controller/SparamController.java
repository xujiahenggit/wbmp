package com.bank.manage.controller;

import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dto.SparamDTO;
import com.bank.manage.dto.TaccountingSubjectDTO;
import com.bank.manage.service.SparamService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 回单参数管理
 *
 * @author
 * @date 2020-07-08
 */
@Api(tags = "回单参数管理")
@RestController
@RequestMapping("/sparam")
public class SparamController {
    @Autowired
    private SparamService sparamService;

    @PostMapping("/list")
    @ApiOperation(value = "查询回单参数")
    @ApiImplicitParam(name = "pageQueryModel", value = "查询回单参数", required = false, paramType = "body", dataType = "PageQueryModel")
    public IPage<SparamDTO> queryList(@RequestBody PageQueryModel pageQueryModel) {
        return sparamService.queryList(pageQueryModel);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增回单参数")
    @ApiImplicitParam(name = "SparamDTO", value = "新增回单参数", required = true, paramType = "body", dataType = "SparamDTO")
    @SystemLog(logModul = "新增回单参数", logType = "新增", logDesc = "新增回单参数")
    public Boolean save(@RequestBody SparamDTO sparamDTO, HttpServletRequest request) {
        return sparamService.save(sparamDTO, null);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改回单参数")
    @ApiImplicitParam(name = "SparamDTO", value = "修改回单参数", required = true, paramType = "body", dataType = "SparamDTO")
    @SystemLog(logModul = "修改回单参数", logType = "修改", logDesc = "修改回单参数")
    public Boolean update(@RequestBody SparamDTO sparamDTO, HttpServletRequest request) {
        return sparamService.update(sparamDTO, null);
    }

    @PostMapping("/updateList")
    @ApiOperation(value = "批量修改回单参数")
    @ApiImplicitParam(name = "SparamDTO", value = "批量修改回单参数", required = true, paramType = "body", dataType = "SparamDTO")
    @SystemLog(logModul = "批量修改回单参数", logType = "修改", logDesc = "批量修改回单参数")
    public Boolean updateList(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        List<Integer> list = (List<Integer>) map.get("list");
        String value = (String) map.get("value");
        return sparamService.updateList(list, value);
    }
}
