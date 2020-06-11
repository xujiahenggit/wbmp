package com.bank.icop.controller;

import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.icop.service.CashVoucherService;
import com.bank.icop.vo.InputVoucherNumberVo;
import com.bank.icop.vo.ReceiptInfoVo;
import com.bank.icop.vo.UpdateOrderDetailVo;
import com.bank.icop.vo.UpdateOrderStatusVo;
import com.bank.icop.vo.VoucherStockVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.bank.icop.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cashVoucher")
@Api(tags = "凭证管理接口")
public class CashVoucherController extends BaseIcopController{

    @Autowired
    private CashVoucherService cashVoucherService;

    @PostMapping("/queryVoucherStock")
    @ApiImplicitParam(name = "pageQueryModel", value = "库存信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    @ApiOperation(value = "凭证管理——库存信息分页查询", notes = "userId:交易用户ID，orgId:机构号，voucherStatus:凭证状态，voucherNo:凭证代码")
    public IPage<VoucherStockVo> queryVoucherStock(@RequestBody PageQueryModel pageQueryModel) {
        return this.cashVoucherService.queryVoucherStock(pageQueryModel);
    }

    @PostMapping("/queryVoucherNumber")
    @ApiOperation(value = "凭证管理--号段查看", notes = "userId:交易用户ID，orderId:订单id，voucherNo:凭证代码，orderDeatilId:订单明细id")
    @ApiImplicitParam(name = "pageQueryModel", value = "号段信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage queryVoucherNumber(@RequestBody PageQueryModel pageQueryModel) {
        return this.cashVoucherService.queryVoucherNumber(pageQueryModel);
    }

    @PostMapping("/voucherNumberSave")
    @ApiOperation(value = "凭证管理--号段录入")
    @ApiImplicitParam(name = "inputVoucherNumberVo", value = "号段录入模型", required = true, paramType = "body", dataType = "InputVoucherNumberVo")
    public Boolean voucherNumberSave(@RequestBody InputVoucherNumberVo inputVoucherNumberVo) {
        return this.cashVoucherService.voucherNumberSave(inputVoucherNumberVo);
    }

    @PutMapping("/updateOrderStatus")
    @ApiOperation(value = "凭证管理--订单状态更新")
    @ApiImplicitParam(name = "updateOrderStatusVo", value = "订单状态更新模型", required = true, paramType = "body", dataType = "UpdateOrderStatusVo")
    public Boolean updateOrderStatus(@RequestBody UpdateOrderStatusVo updateOrderStatusVo) {
        return this.cashVoucherService.updateOrderStatus(updateOrderStatusVo);
    }

    @DeleteMapping("/deleteOrderDeatil/{userId}/{orderId}/{orderDeatild}")
    @ApiOperation(value = "凭证管理--订单明细删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "交易用户ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "orderId", value = "订单编号", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "orderDeatild", value = "订单明细id", required = true, dataType = "String", paramType = "path")
    })
    public Boolean deleteOrderDeatil(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId, @PathVariable("orderDeatild") String orderDeatild) {
        return this.cashVoucherService.deleteOrderDeatil(userId, orderId, orderDeatild);
    }

    @PutMapping("/updateOrderDetail")
    @ApiOperation("订单明细修改")
    @ApiImplicitParam(name = "updateOrderDetailVo", value = "订单明细更新模型", required = true, paramType = "body", dataType = "UpdateOrderDetailVo")
    public Boolean updateOrderDetail(@RequestBody UpdateOrderDetailVo updateOrderDetailVo) {
        return this.cashVoucherService.updateOrderDetail(updateOrderDetailVo);
    }

    @ApiOperation("根据机构号获取收货信息")
    @GetMapping("/getReceiptInfoByOrg")
    @ApiImplicitParam(name = "orgId", value = "机构号", required = true, dataType = "String")
    public ReceiptInfoVo getReceiptInfoByOrg(@RequestParam(value = "orgId", required = true) String orgId) {
        return ReceiptInfoVo.builder().orgId("0101")
                .orgName("长沙银行")
                .receiptUser("张磊")
                .receiptAddress("长沙银行二办15楼")
                .phone("13838384380")
                .invoiceType("电子发票")
                .invoiceTitle("北京宇信科技集团股份有限公司")
                .dutyParagraph("911101087921006070").build();
    }

    @Resource
    HttpServletRequest request;


    @ApiOperation("获取角色信息")
    @GetMapping("/roleinfo")
    public RoleInfoVo getRoleInfo(){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return cashVoucherService.getRoleInfo(tokenUserInfo);
    }

    @ApiOperation("获取凭证订单列表")
    @PostMapping("/voucherorderlist")
    public IPage<VoucherListVo> getVoucherList(@RequestBody VoucherListQueryVo voucherListQueryVo){
        return cashVoucherService.getVoucherList(voucherListQueryVo);
    }

    @PostMapping("/orderinfo")
    @ApiOperation("订单查询")
    public OrderVo getOrderInfo(@RequestBody OrderQueryVo orderQueryVo){
        return cashVoucherService.getOrderInfo(orderQueryVo);
    }

    @PostMapping("/matterlist")
    @ApiOperation("事项列表查询")
    public List<MatterListQueryVo> getMatterList(@RequestBody MatterListQueryVo matterListQueryVo){
        return cashVoucherService.getMatterList(matterListQueryVo);
    }

    @ApiOperation("新建订单")
    @PostMapping("/createOrder")
    public Object createOrder(@RequestBody OrderInfoVo orderInfoVo) {
        return cashVoucherService.createOrder(orderInfoVo);
    }

    @ApiOperation("凭证名称列表查询")
    @GetMapping("/orderList/{orderType}")
    @ApiImplicitParam(name = "orderType", value = "订单类型：01-重要空白凭证订单、02-非重要空白凭证订单、03-分行代理凭证订单", required = true, dataType = "String")
    public Object orderList(@PathVariable String orderType) {

        return cashVoucherService.orderList(orderType);
    }

    @ApiOperation("新建订单明细")
    @PostMapping("/createOrderDetail")
    public Object createOrderDetail(@RequestBody OrderDetailVo orderDetailVo) {
        return cashVoucherService.createOrderDetail(orderDetailVo);
    }

    @ApiOperation("新建订单明细")
    @PostMapping("/orderDetailList")
    public Object orderDetailList(@RequestBody OrderDetailListVo orderDetailListVo) {
        return cashVoucherService.orderDetailList(orderDetailListVo);
    }

}
