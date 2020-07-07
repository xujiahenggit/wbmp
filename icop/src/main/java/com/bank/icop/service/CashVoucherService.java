package com.bank.icop.service;

import com.bank.core.entity.TokenUserInfo;
import com.bank.icop.dos.OrderDetailDo;
import com.bank.icop.dos.VoucherNumberDo;
import com.bank.icop.dos.VoucherStockDo;
import com.bank.icop.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface CashVoucherService {
    List<VoucherStockVo> queryVoucherStock(VoucherStockDo voucherStockDo);

    List queryVoucherNumber(VoucherNumberDo voucherNumberDo);

    Object voucherNumberSave(InputVoucherNumberVo inputVoucherNumberVo);

    Object updateOrderStatus(UpdateOrderStatusVo updateOrderStatusVo);

    Object deleteOrderDeatil(String userId, String orderId, String orderDeatild);

    Object updateOrderDetail(UpdateOrderDetailVo updateOrderDetailVo);

    /**
     * 获取角色信息
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    RoleInfoVo getRoleInfo(TokenUserInfo tokenUserInfo);

    /**
     * 获取凭证订单列表
     * @param voucherListQueryVo 查询参数
     * @return
     */
    IPage<VoucherListVo> getVoucherList(VoucherListQueryVo voucherListQueryVo);

    /**
     * 获取订单详情
     * @param orderQueryVo 查询参数
     * @return
     */
    List<OrderVo> getOrderInfo(OrderQueryVo orderQueryVo);

    /**
     * 查询事项列表
     * @param matterListQueryVo 查询参数
     * @return
     */
    List<MatterListVo> getMatterList(MatterListQueryVo matterListQueryVo);

    Object createOrder(OrderInfoVo orderInfoVo);

    Object createOrderDetail(OrderDetailVo orderDetailVo);

    Object orderList(String orderType);

    Object queryIsSure(OrderDetailListVo orderDetailListVo);

    ReceiptInfoVo getReceiptInfoByOrg(String orgId);

    IPage<OrderDetailDo> queryDetailList(OrderQueryDetailVo orderQueryDetailVo);

    /**
     * 查询凭证 待办列表
     * @param waitListQueryVo 查询参数
     */
    List<VoucherWaitListVo> getWaitList(WaitListQueryVo waitListQueryVo);
}
