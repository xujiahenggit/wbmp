package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dos.TmapActverifyDO;
import com.bank.manage.dto.TmapActverifyDTO;
import com.bank.manage.service.TmapActverifyService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 帐号指定
 *
 * @author
 * @date 2020-07-03
 */
@Api(tags = "帐号指定接口")
@RestController
@RequestMapping("/tmapActverify")
public class TmapActverifyController extends BaseController {

    @Autowired
    private TmapActverifyService tmapActverifyService;

    @PostMapping("/list")
    @ApiOperation(value = "帐号指定信息查询")	
    @ApiImplicitParam(name = "pageQueryModel", value = "帐号指定查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<TmapActverifyDO> queryList(@RequestBody PageQueryModel pageQueryModel) {
    	return this.tmapActverifyService.queryList(pageQueryModel);
    }
    
    
    @PostMapping("/save")
    @ApiOperation(value = "新增帐号指定信息")
    @ApiImplicitParam(name = "tmapActverifyDTO", value = "帐号指定信息", required = true, paramType = "body", dataType = "TmapActverifyDTO")
    @SystemLog(logModul = "帐号指定", logType = "新增", logDesc = "新增帐号指定信息")
    public Boolean save(@RequestBody TmapActverifyDTO tmapActverifyDTO, HttpServletRequest request) {
        //TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.tmapActverifyService.save(tmapActverifyDTO);
    }

    @DeleteMapping("/delete/{acctnos}")
    @ApiOperation(value = "删除帐号指定信息")
    @ApiImplicitParam(name = "acctnos", value = "帐号", required = true, paramType = "path")
    @SystemLog(logModul = "帐号指定", logType = "删除", logDesc = "删除帐号指定信息")
    public Boolean delete(@PathVariable("acctnos") List<String> acctnos) {
        return this.tmapActverifyService.delete(acctnos);
    }
    
    @PostMapping("/select/{acctno}")
    @ApiOperation(value = "根据帐号查询帐号指定信息")	
    @ApiImplicitParam(name = "acctno", value = "帐号指定查询", required = true, paramType = "path")
    public String selectByAcctno(@PathVariable String acctno) {
    	return this.tmapActverifyService.selectByAcctno(acctno);
    }

}
