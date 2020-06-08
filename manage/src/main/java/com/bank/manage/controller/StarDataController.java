package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.StarDataAdminDO;
import com.bank.manage.service.HappyService;
import com.bank.manage.service.StarTempAdminService;
import com.bank.manage.service.StarTempBranchService;
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
@RequestMapping("/starData")
@Api(tags = "快乐服务---行内星级标准化网点数据统计接口")
public class StarDataController extends BaseController {


    @Autowired
    private StarTempAdminService starTempAdminService;

    @Autowired
    private StarTempBranchService starTempBranchService;

    @Autowired
    private HappyService happyService;

    @PostMapping("/queryStarData")
    @ApiOperation(value = "快乐服务--全国标杆网点数据报表列表查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "报表列表信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<StarDataAdminDO> queryExampleBranch(@RequestBody PageQueryModel pageQueryModel, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        boolean b = happyService.hasAdminPermission(tokenUserInfo.getUserId());
        IPage<StarDataAdminDO> page = null;
        if(b){///数据导入管理员用户
           page = starTempAdminService.queryExampleBranchByAdmin(pageQueryModel);
        }else{
            page = starTempBranchService.queryExampleBranch(pageQueryModel);
        }
        return page;
    }

}
