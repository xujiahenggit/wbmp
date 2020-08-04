package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dto.TlogUserDTO;
import com.bank.manage.service.TlogUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 柜员操作日志
 *
 * @author
 * @date 2020-07-10
 */
@Api(value = "客户操作日志模块", tags = "客户操作日志接口")
@RestController
@RequestMapping("/tlogUser")
public class TlogUserController extends BaseController {

    @Autowired
    private TlogUserService tlogUserService;

    @PostMapping("/list")
    @ApiOperation(value = "客户操作日志信息查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "柜员操作日志查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<TlogUserDTO> queryList(@RequestBody PageQueryModel pageQueryModel) {
    	return this.tlogUserService.queryList(pageQueryModel);
    }
}
