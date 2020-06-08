package com.bank.icop.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.icop.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface CashVoucherService {
    IPage<VoucherStockVo> queryVoucherStock(PageQueryModel pageQueryModel);

    IPage queryVoucherNumber(PageQueryModel pageQueryModel);

    Boolean voucherNumberSave(InputVoucherNumberVo inputVoucherNumberVo);

    Boolean updateOrderStatus(UpdateOrderStatusVo updateOrderStatusVo);

    Boolean deleteOrderDeatil(String userId, String orderId, String orderDeatild);

    Boolean updateOrderDetail(UpdateOrderDetailVo updateOrderDetailVo);

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
    OrderVo getOrderInfo(OrderQueryVo orderQueryVo);

    /**
     * 查询事项列表
     * @param matterListQueryVo 查询参数
     * @return
     */
    List<MatterListQueryVo> getMatterList(MatterListQueryVo matterListQueryVo);

    Object createOrder(OrderInfoVo orderInfoVo);

    Object createOrderDetail(OrderDetailVo orderDetailVo);

    Object orderList(String orderType);

    Object orderDetailList(OrderDetailListVo orderDetailListVo);
}
