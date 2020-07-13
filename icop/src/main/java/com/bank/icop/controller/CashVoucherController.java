package com.bank.icop.controller;

import com.bank.core.entity.TokenUserInfo;
import com.bank.icop.dos.OrderDetailDo;
import com.bank.icop.dos.VoucherNumberDo;
import com.bank.icop.dos.VoucherStockDo;
import com.bank.icop.service.CashVoucherService;
import com.bank.icop.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cashVoucher")
@Api(tags = "凭证管理接口")
public class CashVoucherController extends BaseIcopController{

    @Autowired
    private CashVoucherService cashVoucherService;

    @PostMapping("/queryVoucherStock")
    @ApiImplicitParam(name = "voucherStockDo", value = "库存信息查询", required = true, paramType = "body", dataType = "VoucherStockDo")
    @ApiOperation(value = "凭证管理——库存信息查询")
    public List<VoucherStockVo> queryVoucherStock(@RequestBody VoucherStockDo voucherStockDo) {
        return this.cashVoucherService.queryVoucherStock(voucherStockDo);
    }

    @PostMapping("/queryVoucherNumber")
    @ApiOperation(value = "凭证管理--号段查看")
    @ApiImplicitParam(name = "voucherNumberDo", value = "号段信息查询", required = true, paramType = "body", dataType = "VoucherNumberDo")
    public List<VoucherNumberVo> queryVoucherNumber(@RequestBody VoucherNumberDo voucherNumberDo) {
        return this.cashVoucherService.queryVoucherNumber(voucherNumberDo);
    }

    @PostMapping("/voucherNumberSave")
    @ApiOperation(value = "凭证管理--号段录入")
    @ApiImplicitParam(name = "inputVoucherNumberVo", value = "号段录入模型", required = true, paramType = "body", dataType = "InputVoucherNumberVo")
    public Object voucherNumberSave(@RequestBody InputVoucherNumberVo inputVoucherNumberVo) {
        return this.cashVoucherService.voucherNumberSave(inputVoucherNumberVo);
    }

    @PutMapping("/updateOrderStatus")
    @ApiOperation(value = "凭证管理--订单状态更新")
    @ApiImplicitParam(name = "updateOrderStatusVo", value = "订单状态更新模型", required = true, paramType = "body", dataType = "UpdateOrderStatusVo")
    public Object updateOrderStatus(@RequestBody UpdateOrderStatusVo updateOrderStatusVo) {
        return this.cashVoucherService.updateOrderStatus(updateOrderStatusVo);
    }

    @DeleteMapping("/deleteOrderDeatil/{userId}/{orderId}/{orderDeatild}")
    @ApiOperation(value = "凭证管理--订单明细删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "交易用户ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "orderId", value = "订单编号", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "orderDeatild", value = "订单明细id", required = true, dataType = "String", paramType = "path")
    })
    public Object deleteOrderDeatil(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId, @PathVariable("orderDeatild") String orderDeatild) {
        return this.cashVoucherService.deleteOrderDeatil(userId, orderId, orderDeatild);
    }

    @PutMapping("/updateOrderDetail")
    @ApiOperation("订单明细修改")
    @ApiImplicitParam(name = "updateOrderDetailVo", value = "订单明细更新模型", required = true, paramType = "body", dataType = "UpdateOrderDetailVo")
    public Object updateOrderDetail(@RequestBody UpdateOrderDetailVo updateOrderDetailVo) {
        return this.cashVoucherService.updateOrderDetail(updateOrderDetailVo);
    }

    @ApiOperation("根据机构号获取收货信息")
    @GetMapping("/getReceiptInfoByOrg")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, dataType = "String")
    public ReceiptInfoVo getReceiptInfoByOrg(@RequestParam(value = "orgId", required = true) String orgId) {
        return cashVoucherService.getReceiptInfoByOrg(orgId);
    }

    @Resource
    HttpServletRequest request;


    @ApiOperation("获取角色信息")
    @GetMapping("/roleinfo")
    public RoleInfoVo getRoleInfo(){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return cashVoucherService.getRoleInfo(tokenUserInfo);
    }

    @ApiOperation("凭证订单列表查询")
    @PostMapping("/voucherorderlist")
    public IPage<VoucherListVo> getVoucherList(@RequestBody VoucherListQueryVo voucherListQueryVo){
        return cashVoucherService.getVoucherList(voucherListQueryVo);
    }

    @PostMapping("/orderinfo")
    @ApiOperation("订单查询")
    public List<OrderVo> getOrderInfo(@RequestBody OrderQueryVo orderQueryVo){
        return cashVoucherService.getOrderInfo(orderQueryVo);
    }

    @PostMapping("/matterlist")
    @ApiOperation("事项列表查询")
    public List<MatterListVo> getMatterList(@RequestBody MatterListQueryVo matterListQueryVo){
        return cashVoucherService.getMatterList(matterListQueryVo);
    }

    @ApiOperation("新建订单")
    @PostMapping("/createOrder")
    public Object createOrder(@RequestBody OrderInfoVo orderInfoVo) {
        return cashVoucherService.createOrder(orderInfoVo);
    }

    @ApiOperation("凭证名称列表查询")
    @GetMapping("/orderList/{orderType}")
    @ApiImplicitParam(name = "orderType", value = "订单类型：1-重要空白凭证订单、2-非重要空白凭证订单、3-分行代理凭证订单", required = true, dataType = "String")
    public Object orderList(@PathVariable String orderType) {
        return cashVoucherService.orderList(orderType);
    }

    @ApiOperation("新建订单明细")
    @PostMapping("/createOrderDetail")
    public Object createOrderDetail(@RequestBody OrderDetailVo orderDetailVo) {
        return cashVoucherService.createOrderDetail(orderDetailVo);
    }

    @ApiOperation("是否可以创建订单")
    @PostMapping("/queryIsSure")
    public Object queryIsSure(@RequestBody OrderDetailListVo orderDetailListVo) {
        return cashVoucherService.queryIsSure(orderDetailListVo);
    }

    @PostMapping("/queryDetailList")
    @ApiOperation("查询订单明细列表")
    public IPage<OrderDetailDo> queryDetailList(@RequestBody OrderQueryDetailVo orderQueryDetailVo){
        return cashVoucherService.queryDetailList(orderQueryDetailVo);
    }


    @PostMapping("/waitlist")
    @ApiOperation("凭证待办列表")
    public List<VoucherWaitListVo> getWaitList(@RequestBody WaitListQueryVo waitListQueryVo){
        return cashVoucherService.getWaitList(waitListQueryVo);
    }


    @PostMapping("/ordervoucherinfo")
    @ApiOperation(value = "订单凭证详情接口")
    public OrderVoucherDetailResponseVo getOrderVoucherDetailInfo(@RequestBody OrderVoucherDetailVo orderVoucherDetailVo){
        log.info("Controller 接受的参数："+orderVoucherDetailVo.toString());
        return cashVoucherService.getOrderVoucherDetailInfo(orderVoucherDetailVo);
    }


}
