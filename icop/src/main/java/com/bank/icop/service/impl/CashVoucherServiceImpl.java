package com.bank.icop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.icop.dos.VoucherNumberDo;
import com.bank.icop.dos.VoucherStockDo;
import com.bank.icop.service.CashVoucherService;
import com.bank.icop.util.SoapUtil;
import com.bank.icop.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CashVoucherServiceImpl implements CashVoucherService {

    @Override
    public List<VoucherStockVo> queryVoucherStock(VoucherStockDo voucherStockDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId",voucherStockDo.getUserId());
        parmMap.put("orgId",voucherStockDo.getOrgId());
        parmMap.put("voucherStatus",voucherStockDo.getVoucherStatus());
        parmMap.put("voucherNo",voucherStockDo.getVoucherNo());
        Map report = SoapUtil.sendReport("VTMS0016",parmMap);
        List<VoucherStockVo> list = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(report)){
            VoucherStockVo vo = new VoucherStockVo();
            vo.setNum((Integer) report.get("num"));
            vo.setVoucherName((String)report.get("voucherName"));
            vo.setVoucherStatus((String)report.get("voucherStatus"));
            vo.setVoucherNo((String)report.get("voucherNo"));
            list.add(vo);
        }
        return list;
    }

    @Override
    public List queryVoucherNumber(VoucherNumberDo voucherNumberDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId",voucherNumberDo.getUserId());
        parmMap.put("orgId",voucherNumberDo.getOrderId());
        parmMap.put("voucherNo",voucherNumberDo.getVoucherNo());
        parmMap.put("orderDeatilId",voucherNumberDo.getOrderDeatilId());
        Map report = SoapUtil.sendReport("VTMS0015",parmMap);
        List<VoucherNumberVo> list = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(report)){
            VoucherNumberVo vo = new VoucherNumberVo();
            vo.setStartNo((String)report.get("startNo"));
            vo.setEndNo((String)report.get("endNo"));
            vo.setNum((Integer) report.get("num"));
            list.add(vo);
        }
        return list;
    }

    @Override
    public Boolean voucherNumberSave(InputVoucherNumberVo vo) {
        Map<String, Object> parmMap = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList<>();
        parmMap.put("userId",vo.getUserId());
        parmMap.put("orderId",vo.getOrderId());
        parmMap.put("orderDeatilId",vo.getOrderDeatilId());
        parmMap.put("voucherNo",vo.getVoucherNo());
        List<VoucherNumberVo> data = vo.getData();
        for (VoucherNumberVo numberVo : data) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("startNo",numberVo.getStartNo());
            dataMap.put("endNo",numberVo.getEndNo());
            dataMap.put("num",numberVo.getNum());
            list.add(dataMap);
        }
        parmMap.put("data",list);
        Map report = SoapUtil.sendReport("VTMS0014",parmMap);
        String repcode = (String)report.get("repcode");
        String repmsg = (String)report.get("repmsg");
        if("0".equals(repcode)){
            return true;
        }else{
            throw new BizException(repmsg);
        }
    }

    @Override
    public Boolean updateOrderStatus(UpdateOrderStatusVo vo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId",vo.getUserId());
        parmMap.put("userName",vo.getUserName());
        parmMap.put("orderId",vo.getOrderId());
        parmMap.put("operationObject",vo.getOperationObject());
        parmMap.put("operationType",vo.getOperationType());
        Map report = SoapUtil.sendReport("VTMS0013",parmMap);
        String repcode = (String)report.get("repcode");
        String repmsg = (String)report.get("repmsg");
        if("0".equals(repcode)){
            return true;
        }else{
            throw new BizException(repmsg);
        }
    }

    @Override
    public Boolean deleteOrderDeatil(String userId, String orderId, String orderDeatild) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId",userId);
        parmMap.put("orderId",orderId);
        parmMap.put("orderDeatild",orderDeatild);
        Map report = SoapUtil.sendReport("VTMS0012",parmMap);
        String repcode = (String)report.get("repcode");
        String repmsg = (String)report.get("repmsg");
        if("0".equals(repcode)){
            return true;
        }else{
            throw new BizException(repmsg);
        }
    }

    @Override
    public Boolean updateOrderDetail(UpdateOrderDetailVo vo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId",vo.getUserId());
        parmMap.put("orderId",vo.getOrderId());
        parmMap.put("orderDeatild",vo.getOrderDeatild());
        parmMap.put("voucherName",vo.getVoucherName());
        parmMap.put("voucherNo",vo.getVoucherNo());
        parmMap.put("num",vo.getNum());
        parmMap.put("cardName",vo.getCardName());
        parmMap.put("cardNo",vo.getCardNo());
        parmMap.put("remark",vo.getRemark());
        Map report = SoapUtil.sendReport("VTMS0011",parmMap);
        String repcode = (String)report.get("repcode");
        String repmsg = (String)report.get("repmsg");
        if("0".equals(repcode)){
            return true;
        }else{
            throw new BizException(repmsg);
        }
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
