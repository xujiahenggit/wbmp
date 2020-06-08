package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.service.ExamineDataAdminService;
import com.bank.manage.service.ExamineDataBranchService;
import com.bank.manage.service.HappyService;
import com.bank.manage.vo.ExamineAnalyzeParmVo;
import com.bank.manage.vo.ExamineAnalyzeVo;
import com.bank.manage.vo.ExamineDataRankVo;
import com.bank.manage.vo.ExamineDeduVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/examineData")
@Api(tags = "快乐服务--季度考核数据报表列表查询")
public class ExamineDataController extends BaseController {

    @Autowired
    private ExamineDataAdminService examineDataAdminService;

    @Autowired
    private ExamineDataBranchService examineDataBranchService;

    @Autowired
    private HappyService happyService;

    @PostMapping("/queryExamineDataRank")
    @ApiOperation(value = "快乐服务--季度考核数据排名表")
    @ApiImplicitParam(name = "pageQueryModel", value = "报表列表信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<ExamineDataRankVo> queryExamineDataRank(@RequestBody PageQueryModel pageQueryModel, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        boolean b = happyService.hasAdminPermission(tokenUserInfo.getUserId());
        IPage<ExamineDataRankVo> page = null;
        if(b){
            page =  examineDataAdminService.queryExamineDataRankByAdmin(pageQueryModel);
        }else{
            page = examineDataBranchService.queryExamineDataRank(pageQueryModel);
        }
        return page;
    }


    @PostMapping("/queryExamineDataDedu")
    @ApiOperation(value = "快乐服务--季度考核扣分明细表")
    @ApiImplicitParam(name = "pageQueryModel", value = "报表列表信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<ExamineDeduVo> queryExamineDataDedu(@RequestBody PageQueryModel pageQueryModel,HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        boolean b = happyService.hasAdminPermission(tokenUserInfo.getUserId());
        IPage<ExamineDeduVo> examineDeduPage = null;
        if(b){//
            examineDeduPage = examineDataAdminService.queryExamineDataDeduByAdmin(pageQueryModel);
        }else{
            examineDeduPage = examineDataBranchService.queryExamineDataDeduByBranch(pageQueryModel);
        }
        return examineDeduPage;
    }


    @PostMapping("/queryExamineDataAnalyze")
    @ApiOperation(value = "快乐服务--季度考核分析图")
    @ApiImplicitParam(name = "examineAnalyzeParmVo", value = "报表列表信息分页查询", required = true, paramType = "body", dataType = "ExamineAnalyzeParmVo")
    public List<ExamineAnalyzeVo> queryExamineDataAnalyze(@RequestBody ExamineAnalyzeParmVo examineAnalyzeParmVo, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        boolean b = happyService.hasAdminPermission(tokenUserInfo.getUserId());
        List<ExamineAnalyzeVo> examineAnalyzeList = null;
        if(b){
            examineAnalyzeList = examineDataAdminService.queryExamineDataAnalyzeByAdmin(examineAnalyzeParmVo);
        }else{
            examineAnalyzeList = examineDataBranchService.queryExamineDataAnalyzeByBranch(examineAnalyzeParmVo);
        }
        return examineAnalyzeList;
    }
}
