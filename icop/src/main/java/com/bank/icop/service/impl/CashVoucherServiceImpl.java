package com.bank.icop.service.impl;

import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.icop.service.CashVoucherService;
import com.bank.icop.util.SoapUtil;
import com.bank.icop.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CashVoucherServiceImpl implements CashVoucherService {

    @Override
    public IPage<VoucherStockVo> queryVoucherStock(PageQueryModel pageQueryModel) {
        Page<VoucherStockVo> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> params = pageQueryModel.getQueryParam();
        String userId = (String) params.get("userId");
        String orgId = (String) params.get("orgId");
        String voucherStatus = (String) params.get("voucherStatus");
        String voucherNo = (String) params.get("voucherNo");
        //TODO 凭着管理---库存查询 分页查询假数据组装
        VoucherStockVo vo = new VoucherStockVo();
        vo.setNum(700);
        vo.setVoucherName("凭证111111");
        vo.setVoucherStatus("1");
        vo.setVoucherNo("EC12456");
        List<VoucherStockVo> list = new ArrayList<>();
        list.add(vo);

        page.setRecords(list);
        page.setTotal(1L);


        return page;
    }

    @Override
    public IPage queryVoucherNumber(PageQueryModel pageQueryModel) {
        Page<VoucherNumberVo> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> params = pageQueryModel.getQueryParam();
        String userId = (String) params.get("userId");
        String orderId = (String) params.get("orderId");
        String voucherNo = (String) params.get("voucherNo");
        String orderDeatilId = (String) params.get("orderDeatilId");
        //TODO 凭着管理---段号查看查询 分页查询假数据组装
        VoucherNumberVo vo = new VoucherNumberVo();
        vo.setStartNo("2345632245");
        vo.setEndNo("23457874365");
        vo.setNum(1000);
        List<VoucherNumberVo> list = new ArrayList<>();
        list.add(vo);

        page.setRecords(list);
        page.setTotal(1L);

        return page;
    }

    @Override
    public Boolean voucherNumberSave(InputVoucherNumberVo inputVoucherNumberVo) {
        //TODO 号段录入


        return true;
    }

    @Override
    public Boolean updateOrderStatus(UpdateOrderStatusVo updateOrderStatusVo) {
        //TODO 订单状态更新


        return true;
    }

    @Override
    public Boolean deleteOrderDeatil(String userId, String orderId, String orderDeatild) {
        //TODO 订单明细删除


        return true;
    }

    @Override
    public Boolean updateOrderDetail(UpdateOrderDetailVo updateOrderDetailVo) {
        //TODO 订单明细修改

        return true;
    }

    /**
     * 获取角色信息
     *
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    @Override
    public RoleInfoVo getRoleInfo(TokenUserInfo tokenUserInfo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", tokenUserInfo.getUserId());
        Map report = SoapUtil.sendReport("VTMS0001",parmMap);

        RoleInfoVo roleInfoVo = new RoleInfoVo();
        roleInfoVo.setOrgId("10001");
        roleInfoVo.setOrgName("总行领导");
        roleInfoVo.setRoleId("1");
        roleInfoVo.setRoleName("系统管理员");
        return roleInfoVo;
    }

    /**
     * 获取凭证订单列表
     *
     * @param voucherListQueryVo 查询参数
     * @return
     */
    @Override
    public IPage<VoucherListVo> getVoucherList(VoucherListQueryVo voucherListQueryVo) {
        IPage<VoucherListVo> page = new Page<>();

        List<VoucherListVo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            VoucherListVo voucherListVo = new VoucherListVo();
            voucherListVo.setListType("1");
            voucherListVo.setOrderType("01");
            voucherListVo.setOrderNo(String.valueOf(i));
            voucherListVo.setOrderCycle(String.valueOf(i + 1));
            voucherListVo.setOrderStatus(String.valueOf(i));
            voucherListVo.setOrgId("10001");
            voucherListVo.setOrgName("总行领导");
            list.add(voucherListVo);
        }
        page.setRecords(list);
        return page;
    }

    /**
     * 获取订单详情
     *
     * @param orderQueryVo 查询参数
     * @return
     */
    @Override
    public OrderVo getOrderInfo(OrderQueryVo orderQueryVo) {
        OrderVo orderVo = new OrderVo();
        //设置订单列表类型
        orderVo.setListType(String.valueOf(1));
        //设置 订单类型
        orderVo.setOrderType("01");
        //设置 订单编号
        orderVo.setOrderNo("123456");
        //设置订单周期
        orderVo.setOrderCycle("10");
        //设置订单状态
        orderVo.setOrderStatus("10");
        //设置  机构ID
        orderVo.setOrgId("10001");
        //设置 机构名称
        orderVo.setOrgName("总行领导");
        //设置创建人
        orderVo.setCreateUser("张三");
        //设置创建时间
        orderVo.setCreateDate("2020-05-04 20:30:30");
        //设置 数量
        orderVo.setVoucherCount(String.valueOf(10));
        //设置       总价
        orderVo.setVoucherAmt("10.20");
        //设置 收货人
        orderVo.setConsignee("李四");
        //设置       收货地址
        orderVo.setAddress("汇丰支行1楼");
        //设置 联系电话
        orderVo.setPhone("15974141234");
        //设置        发票类型
        orderVo.setInvoiceType("电子");
        //设置 发票抬头
        orderVo.setInvoiceTitle("长沙银行股份有限公司");
        //设置       税号
        orderVo.setDutyPara("000000000001");
        //设置 备注
        orderVo.setRemark("备注");
        return orderVo;
    }

    /**
     * 查询待办事项列表
     * @param matterListQueryVo 查询参数
     * @return
     */
    @Override
    public List<MatterListQueryVo> getMatterList(MatterListQueryVo matterListQueryVo) {
        List<MatterListQueryVo> list=new ArrayList<>();
        return list;
    }

    @Override
    public Object createOrder(OrderInfoVo orderInfoVo) {
        return "{\"code\":\"200\",\"msg\":\"success\"}";
    }


    @Override
    public Object createOrderDetail(OrderDetailVo orderDetailVo) {
        return "{\"code\":\"200\",\"msg\":\"success\"}";
    }

    @Override
    public Object orderList(String orderType) {
        return "{\"code\":\"200\",\"msg\":\"success\",\"data\":[{\"voucherTypeName\":\"1\",\"voucherCode\":\"1\",\"spec\":\"1\",\"price\":\"1\",\"num\":\"1\",\"cardNo\":\"1\",\"cardName\":\"1\"},{\"voucherTypeName\":\"1\",\"voucherCode\":\"1\",\"spec\":\"1\",\"price\":\"1\",\"num\":\"1\",\"cardNo\":\"1\",\"cardName\":\"1\"},{\"voucherTypeName\":\"1\",\"voucherCode\":\"1\",\"spec\":\"1\",\"price\":\"1\",\"num\":\"1\",\"cardNo\":\"1\",\"cardName\":\"1\"}]}\n";
    }

    @Override
    public Object orderDetailList(OrderDetailListVo orderDetailListVo) {
        return null;
    }
}
