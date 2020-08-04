package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dos.TlimitTimeDO;
import com.bank.manage.dto.TlimitTimeDTO;
import com.bank.manage.service.TlimitTimeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 逾期催收时限
 *
 * @author
 * @date 2020-07-03
 */
@Api(tags = "逾期催收时限接口")
@RestController
@RequestMapping("/tlimitTime")
public class TlimitTimeController extends BaseController {

    @Autowired
    private TlimitTimeService tlimitTimeService;

    @PostMapping("/list")
    @ApiOperation(value = "逾期催收时限信息查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "逾期催收时限查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<TlimitTimeDO> queryList(@RequestBody PageQueryModel pageQueryModel, HttpServletRequest request) {
        //TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
    	return this.tlimitTimeService.queryList(pageQueryModel);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改逾期催收时限信息")
    @ApiImplicitParam(name = "tlimitTimeDTO", value = "修改逾期催收时限信息", required = true, paramType = "body", dataType = "TlimitTimeDTO")
    @SystemLog(logModul = "逾期催收时限", logType = "修改", logDesc = "修改逾期催收时限信息")
    public Boolean updateTlimitTime(@RequestBody TlimitTimeDTO tlimitTimeDTO) {
        return this.tlimitTimeService.updateTlimitTime(tlimitTimeDTO);
    }

    @PostMapping("/select/{branch}")
    @ApiOperation(value = "根据机构号查询逾期催收时限信息")
    @ApiImplicitParam(name = "branch", value = "逾期催收时限查询", required = true, paramType = "path")
    public Boolean selectByAcctno(@PathVariable String branch) {
        return this.tlimitTimeService.selectByBranch(branch);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增逾期催收时限信息")
    @ApiImplicitParam(name = "tlimitTimeDTO", value = "逾期催收时限信息", required = true, paramType = "body", dataType = "TlimitTimeDTO")
    @SystemLog(logModul = "逾期催收时限", logType = "新增", logDesc = "新增逾期催收时限信息")
    public Boolean save(@RequestBody TlimitTimeDTO tlimitTimeDTO, HttpServletRequest request) {
        //TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.tlimitTimeService.save(tlimitTimeDTO);
    }

    @DeleteMapping("/delete/{ids}")
    @ApiOperation(value = "删除逾期催收时限信息")
    @ApiImplicitParam(name = "ids", value = "id", required = true, paramType = "path")
    @SystemLog(logModul = "逾期催收时限", logType = "删除", logDesc = "删除逾期催收时限信息")
    public Boolean delete(@PathVariable("ids") List<String> ids) {
        return this.tlimitTimeService.delete(ids);
    }
}
