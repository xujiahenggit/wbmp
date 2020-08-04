package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dos.TaccountingSubjectDO;
import com.bank.manage.dto.TaccountingSubjectDTO;
import com.bank.manage.service.TaccountingSubjectService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 对账科目维护
 *
 * @author
 * @date 2020-07-14
 */
@Api(tags = "对账科目维护接口")
@RestController
@RequestMapping("/taccountingSubject")
public class TaccountingSubjectController extends BaseController {

    @Autowired
    private TaccountingSubjectService taccountingSubjectService;

    @PostMapping("/list")
    @ApiOperation(value = "对账科目维护查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "对账科目维护查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<TaccountingSubjectDO> queryList(@RequestBody PageQueryModel pageQueryModel) {
    	return this.taccountingSubjectService.queryList(pageQueryModel);
    }
    
    
    @PostMapping("/save")
    @ApiOperation(value = "新增对账科目维护信息")
    @ApiImplicitParam(name = "taccountingSubjectDTO", value = "对账科目维护", required = true, paramType = "body", dataType = "TaccountingSubjectDTO")
    @SystemLog(logModul = "对账科目维护", logType = "新增", logDesc = "新增对账科目维护")
    public Boolean save(@RequestBody TaccountingSubjectDTO taccountingSubjectDTO, HttpServletRequest request) {
        //TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.taccountingSubjectService.save(taccountingSubjectDTO);
    }

    @DeleteMapping("/delete/{acctnos}")
    @ApiOperation(value = "删除对账科目维护信息")
    @ApiImplicitParam(name = "acctnos", value = "帐号", required = true, paramType = "path")
    @SystemLog(logModul = "对账科目维护", logType = "删除", logDesc = "删除对账科目维护信息")
    public Boolean delete(@PathVariable("acctnos") List<String> acctnos) {
        return this.taccountingSubjectService.delete(acctnos);
    }
    
    @PostMapping("/update")
    @ApiOperation(value = "修改对账科目维护信息")
    @ApiImplicitParam(name = "taccountingSubjectDTO", value = "修改对账科目维护信息", required = true, paramType = "body", dataType = "TaccountingSubjectDTO")
    @SystemLog(logModul = "对账科目维护", logType = "修改", logDesc = "修改对账科目维护信息")
    public Boolean updateTingOrder(@RequestBody TaccountingSubjectDTO taccountingSubjectDTO, HttpServletRequest request) {
        //TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.taccountingSubjectService.updateTaccountingSubject(taccountingSubjectDTO);
    }
}
