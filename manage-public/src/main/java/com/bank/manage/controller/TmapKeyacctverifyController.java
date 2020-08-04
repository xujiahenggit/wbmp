package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dos.TmapKeyacctverifyDO;
import com.bank.manage.dto.TmapKeyacctverifyDTO;
import com.bank.manage.service.TmapKeyacctverifyService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 指定重点账号
 *
 * @author
 * @date 2020-07-03
 */
@Api(tags = "指定重点账号接口")
@RestController
@RequestMapping("/tmapKeyacctverify")
public class TmapKeyacctverifyController extends BaseController {

    @Autowired
    private TmapKeyacctverifyService tmapKeyacctverifyService;

    @PostMapping("/list")
    @ApiOperation(value = "指定重点账号信息查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "指定重点账号查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<TmapKeyacctverifyDO> queryList(@RequestBody PageQueryModel pageQueryModel) {
    	return this.tmapKeyacctverifyService.queryList(pageQueryModel);
    }
    
    
    @PostMapping("/save")
    @ApiOperation(value = "新增指定重点账号信息")
    @ApiImplicitParam(name = "tmapKeyacctverifyDTO", value = "指定重点账号信息", required = true, paramType = "body", dataType = "TmapKeyacctverifyDTO")
    @SystemLog(logModul = "指定重点账号", logType = "新增", logDesc = "新增指定重点账号信息")
    public Boolean save(@RequestBody TmapKeyacctverifyDTO tmapKeyacctverifyDTO, HttpServletRequest request) {
        //TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.tmapKeyacctverifyService.save(tmapKeyacctverifyDTO);
    }

    @DeleteMapping("/delete/{acctnos}")
    @ApiOperation(value = "删除指定重点账号信息")
    @ApiImplicitParam(name = "acctnos", value = "帐号", required = true, paramType = "path")
    @SystemLog(logModul = "指定重点账号", logType = "删除", logDesc = "删除指定重点账号信息")
    public Boolean delete(@PathVariable("acctnos") List<String> acctnos) {
        return this.tmapKeyacctverifyService.delete(acctnos);
    }
    
    @PostMapping("/select/{acctno}")
    @ApiOperation(value = "根据帐号查询指定重点账号信息")
    @ApiImplicitParam(name = "acctno", value = "指定重点账号查询", required = true, paramType = "path")
    public String selectByAcctno(@PathVariable String acctno) {
    	return this.tmapKeyacctverifyService.selectByAcctno(acctno);
    }

    @PostMapping("/update/switchByAcctno")
    @ApiOperation(value = "启用停用指定重点账号信息")
    @ApiImplicitParam(name = "tmapKeyacctverifyDTO", value = "启用停用指定重点账号", required = true, paramType = "body", dataType = "TmapKeyacctverifyDTO")
    @SystemLog(logModul = "指定重点账号", logType = "修改", logDesc = "启用停用指定重点账号信息")
    public Boolean switchByAcctno(@RequestBody TmapKeyacctverifyDTO tmapKeyacctverifyDTO, HttpServletRequest request) {
        //TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.tmapKeyacctverifyService.switchByAcctno(tmapKeyacctverifyDTO);
    }

}
