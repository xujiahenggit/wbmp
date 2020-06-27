package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.ExampleAdminDO;
import com.bank.manage.service.ExampleBranchAdminService;
import com.bank.manage.service.ExampleBranchService;
import com.bank.manage.service.HappyService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/exampleBranch")
@Api( tags = "快乐服务---全国标杆网点数据接口")
public class ExampleBranchController extends BaseController {

    @Autowired
    private ExampleBranchAdminService exampleBranchAdminService;

    @Autowired
    private ExampleBranchService exampleBranchService;

    @Autowired
    private HappyService happyService;

    @PostMapping("/queryExampleBranch")
    @ApiOperation(value = "快乐服务--全国标杆网点数据报表列表查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "报表列表信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<ExampleAdminDO> queryExampleBranch(@RequestBody PageQueryModel pageQueryModel, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        boolean b = happyService.hasAdminPermission(tokenUserInfo.getUserId());
        IPage<ExampleAdminDO> page = null;
        if(b){//数据导入管理员用户
            page = exampleBranchAdminService.queryExampleBranchByAdmin(pageQueryModel);
        }else{
            page = exampleBranchService.queryExampleBranch(pageQueryModel);
        }
        return page;
    }

}
