package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dto.TaccountingOrderDTO;
import com.bank.manage.service.TaccountingOrderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 对账指令维护
 *
 * @author
 * @date 2020-07-08
 */
@Api(tags = "对账指令维护接口")
@RestController
@RequestMapping("/taccountingOrder")
public class TaccountingOrderController extends BaseController {

    @Autowired
    private TaccountingOrderService taccounTingOrderService;

    @PostMapping("/list")
    @ApiOperation(value = "对账指令维护查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "对账指令维护查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<TaccountingOrderDTO> queryList(@RequestBody PageQueryModel pageQueryModel) {
    	return this.taccounTingOrderService.queryList(pageQueryModel);
    }
    
    
    @PostMapping("/save")
    @ApiOperation(value = "新增对账指令维护信息")
    @ApiImplicitParam(name = "taccountingOrderDTO", value = "对账指令维护", required = true, paramType = "body", dataType = "TaccountingOrderDTO")
    @SystemLog(logModul = "对账指令维护", logType = "新增", logDesc = "新增对账指令维护")
    public Boolean save(@RequestBody TaccountingOrderDTO taccounTingOrderDTO, HttpServletRequest request) {
        //TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.taccounTingOrderService.save(taccounTingOrderDTO);
    }

    @DeleteMapping("/delete/{acctnos}")
    @ApiOperation(value = "删除对账指令维护信息")
    @ApiImplicitParam(name = "acctnos", value = "帐号", required = true, paramType = "path")
    @SystemLog(logModul = "对账指令维护", logType = "删除", logDesc = "删除对账指令维护信息")
    public Boolean delete(@PathVariable("acctnos") List<String> acctnos) {
        return this.taccounTingOrderService.delete(acctnos);
    }
    
    @PostMapping("/update")
    @ApiOperation(value = "修改对账指令维护信息")
    @ApiImplicitParam(name = "taccountingOrderDTO", value = "修改对账指令维护信息", required = true, paramType = "body", dataType = "TaccountingOrderDTO")
    @SystemLog(logModul = "对账指令维护", logType = "修改", logDesc = "修改对账指令维护信息")
    public Boolean updateTingOrder(@RequestBody TaccountingOrderDTO taccounTingOrderDTO, HttpServletRequest request) {
        //TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.taccounTingOrderService.updateTaccounTingOrder(taccounTingOrderDTO);
    }
}
