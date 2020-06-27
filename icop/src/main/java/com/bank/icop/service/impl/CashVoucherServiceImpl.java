package com.bank.icop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.icop.dos.OrderDetailDo;
import com.bank.icop.dos.VoucherNumberDo;
import com.bank.icop.dos.VoucherStockDo;
import com.bank.icop.service.CashVoucherService;
import com.bank.icop.util.SoapUtil;
import com.bank.icop.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
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
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0016",parmMap);
        } catch (Exception e) {
            throw new BizException("库存查询失败！"+e.getMessage());
        }
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
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0015",parmMap);
        } catch (Exception e) {
            throw new BizException("号段查看失败！"+e.getMessage());
        }
        List<VoucherNumberVo> list = new ArrayList<>();
        Object objectData = report.get("data");
        if(isObjectIsNotEmpty(objectData)){
            Map<String,Object> dataMap =(Map<String,Object> )report.get("data");
            Object data = dataMap.get("data");
            if(data != null){//多条数据
                List<Map<String,Object>> dataList = (List<Map<String,Object>>)data;
                if(CollectionUtil.isNotEmpty(dataList)){
                    for (int i = 0; i < dataList.size(); i++) {
                        VoucherNumberVo vo = new VoucherNumberVo();
                        vo.setStartNo((String)report.get("startNo"));
                        vo.setEndNo((String)report.get("endNo"));
                        vo.setNum((Integer) report.get("num"));
                        list.add(vo);
                    }
                }
            }else{
                VoucherNumberVo vo = new VoucherNumberVo();
                vo.setStartNo((String)report.get("startNo"));
                vo.setEndNo((String)report.get("endNo"));
                vo.setNum((Integer) report.get("num"));
                list.add(vo);
            }
        }
        return list;
    }

    @Override
    public Object voucherNumberSave(InputVoucherNumberVo vo) {
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
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0014",parmMap);
        } catch (Exception e) {
            throw new BizException("号段录入失败！"+e.getMessage());
        }
        return report;
    }

    @Override
    public Object updateOrderStatus(UpdateOrderStatusVo vo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId",vo.getUserId());
        parmMap.put("userName",vo.getUserName());
        parmMap.put("orderId",vo.getOrderId());
        parmMap.put("operationObject",vo.getOperationObject());
        parmMap.put("operationType",vo.getOperationType());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0013",parmMap);
        } catch (Exception e) {
            throw new BizException("订单状态更新失败！"+e.getMessage());
        }
        return report;
    }

    @Override
    public Object deleteOrderDeatil(String userId, String orderId, String orderDeatild) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId",userId);
        parmMap.put("orderId",orderId);
        parmMap.put("orderDeatild",orderDeatild);
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0012",parmMap);
        } catch (Exception e) {
            throw new BizException("订单明细删除失败！"+e.getMessage());
        }
        return report;
    }

    @Override
    public Object updateOrderDetail(UpdateOrderDetailVo vo) {
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
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0011",parmMap);
        } catch (Exception e) {
            throw new BizException("订单明细修改失败！"+e.getMessage());
        }
        return report;
    }

    @Override
    public RoleInfoVo getRoleInfo(TokenUserInfo tokenUserInfo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", tokenUserInfo.getUserId());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0001",parmMap);
        } catch (Exception e) {
            throw new BizException("角色信息查询失败！"+e.getMessage());
        }
        RoleInfoVo roleInfoVo = new RoleInfoVo();
        roleInfoVo.setOrgId((String)report.get("orgId"));
        roleInfoVo.setOrgName((String)report.get("orgName"));
        roleInfoVo.setRoleId((String)report.get("roleId"));
        roleInfoVo.setRoleName((String)report.get("roleName"));
        return roleInfoVo;
    }

    @Override
    public List<MatterListVo> getMatterList(MatterListQueryVo matterListQueryVo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId",matterListQueryVo.getUserId());
        parmMap.put("type",matterListQueryVo.getType());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0002",parmMap);
        } catch (Exception e) {
            throw new BizException("事项列表查询失败！"+e.getMessage());
        }
        List<MatterListVo> list=new ArrayList<>();
        MatterListVo vo= new MatterListVo();
        vo.setOrderType((String)report.get("orderType"));
        vo.setContent((String)report.get("content"));
        vo.setCreateTime((String)report.get("createTime"));
        vo.setEventName((String)report.get("eventName"));
        list.add(vo);
        return list;
    }


    @Override
    public IPage<VoucherListVo> getVoucherList(VoucherListQueryVo vo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", vo.getUserId());
        parmMap.put("orgId", vo.getOrgId());
        parmMap.put("pageIndex", vo.getPageIndex());
        parmMap.put("pageSize", vo.getPageSize());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0003",parmMap);
        } catch (Exception e) {
            throw new BizException("凭证订单列表查询失败！"+e.getMessage());
        }
        IPage<VoucherListVo> page = new Page<>();
        List<Map<String,Object>> dataList =(List<Map<String,Object>> )report.get("data");
        List<VoucherListVo> list = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(dataList)){
            for (int i = 0; i < dataList.size(); i++) {
                VoucherListVo voucherListVo = new VoucherListVo();
                voucherListVo.setListType((String)dataList.get(i).get("listType"));
                voucherListVo.setOrderType((String)dataList.get(i).get("orderType"));
                voucherListVo.setOrderNo((String)dataList.get(i).get("orderNo"));
                voucherListVo.setOrderCycle((String)dataList.get(i).get("orderCycle"));
                voucherListVo.setOrderStatus((String)dataList.get(i).get("orderStatus"));
                voucherListVo.setOrgId((String)dataList.get(i).get("orgId"));
                voucherListVo.setOrgName((String)dataList.get(i).get("orgName"));
                list.add(voucherListVo);
            }
        }

        /*Object data = dataMap.get("data");
        List<VoucherListVo> list = new ArrayList<>();
        if(data != null){
            List<Map<String,Object>> dataList = (List<Map<String,Object>>)data;
            if(CollectionUtil.isNotEmpty(dataList)){
                for (int i = 0; i < dataList.size(); i++) {
                    VoucherListVo voucherListVo = new VoucherListVo();
                    voucherListVo.setListType((String)dataList.get(i).get("listType"));
                    voucherListVo.setOrderType((String)dataList.get(i).get("orderType"));
                    voucherListVo.setOrderNo((String)dataList.get(i).get("orderNo"));
                    voucherListVo.setOrderCycle((String)dataList.get(i).get("orderCycle"));
                    voucherListVo.setOrderStatus((String)dataList.get(i).get("orderStatus"));
                    voucherListVo.setOrgId((String)dataList.get(i).get("orgId"));
                    voucherListVo.setOrgName((String)dataList.get(i).get("orgName"));
                    list.add(voucherListVo);
                }
            }
        }else{
            VoucherListVo voucherListVo = new VoucherListVo();
            voucherListVo.setListType((String)dataMap.get("listType"));
            voucherListVo.setOrderType((String)dataMap.get("orderType"));
            voucherListVo.setOrderNo((String)dataMap.get("orderNo"));
            voucherListVo.setOrderCycle((String)dataMap.get("orderCycle"));
            voucherListVo.setOrderStatus((String)dataMap.get("orderStatus"));
            voucherListVo.setOrgId((String)dataMap.get("orgId"));
            voucherListVo.setOrgName((String)dataMap.get("orgName"));
            list.add(voucherListVo);
        }*/

        page.setRecords(list);
        return page;
    }

    @Override
    public List<OrderVo> getOrderInfo(OrderQueryVo orderQueryVo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId",orderQueryVo.getUserId());
        parmMap.put("orderId",orderQueryVo.getOrderId());
        parmMap.put("orderType",orderQueryVo.getOrderType());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0004",parmMap);
        } catch (Exception e) {
            throw new BizException("订单查询失败！"+e.getMessage());
        }
        List<OrderVo> list = new ArrayList<>();
        Object objectData = report.get("data");//判断data节点是否为""
        if(isObjectIsNotEmpty(objectData)){
            Map<String,Object> dataMap =(Map<String,Object> )report.get("data");
            Object data = dataMap.get("data");
            if(data != null){//多条数据
                List<Map<String,Object>> dataList = (List<Map<String,Object>>)data;
                if(CollectionUtil.isNotEmpty(dataList)){
                    for (int i = 0; i < dataList.size(); i++) {
                        OrderVo orderVo = new OrderVo();
                        orderVo.setListType((String)dataList.get(i).get("listType"));
                        orderVo.setOrderType((String)dataList.get(i).get("orderType"));
                        orderVo.setOrderNo((String)dataList.get(i).get("orderNo"));
                        orderVo.setOrderCycle((String)dataList.get(i).get("orderCycle"));
                        orderVo.setOrderStatus((String)dataList.get(i).get("orderStatus"));
                        orderVo.setOrgId((String)dataList.get(i).get("orgId"));
                        orderVo.setOrgName((String)dataList.get(i).get("orgName"));
                        orderVo.setCreateUser((String)dataList.get(i).get("createUser"));
                        orderVo.setCreateDate((String)dataList.get(i).get("createDate"));
                        orderVo.setVoucherCount((String)dataList.get(i).get("voucherCount"));
                        orderVo.setVoucherAmt((String)dataList.get(i).get("voucherAmt"));
                        orderVo.setConsignee((String)dataList.get(i).get("consignee"));
                        orderVo.setAddress((String)dataList.get(i).get("address"));
                        orderVo.setPhone((String)dataList.get(i).get("phone"));
                        orderVo.setInvoiceType((String)dataList.get(i).get("invoiceType"));
                        orderVo.setInvoiceTitle((String)dataList.get(i).get("invoiceTitle"));
                        orderVo.setDutyPara((String)dataList.get(i).get("dutyPara"));
                        orderVo.setRemark((String)dataList.get(i).get("remark"));
                        list.add(orderVo);
                    }
                }
            }else{
                OrderVo orderVo = new OrderVo();
                orderVo.setListType((String)dataMap.get("listType"));
                orderVo.setOrderType((String)dataMap.get("orderType"));
                orderVo.setOrderNo((String)dataMap.get("orderNo"));
                orderVo.setOrderCycle((String)dataMap.get("orderCycle"));
                orderVo.setOrderStatus((String)dataMap.get("orderStatus"));
                orderVo.setOrgId((String)dataMap.get("orgId"));
                orderVo.setOrgName((String)dataMap.get("orgName"));
                orderVo.setCreateUser((String)dataMap.get("createUser"));
                orderVo.setCreateDate((String)dataMap.get("createDate"));
                orderVo.setVoucherCount((String)dataMap.get("voucherCount"));
                orderVo.setVoucherAmt((String)dataMap.get("voucherAmt"));
                orderVo.setConsignee((String)dataMap.get("consignee"));
                orderVo.setAddress((String)dataMap.get("address"));
                orderVo.setPhone((String)dataMap.get("phone"));
                orderVo.setInvoiceType((String)dataMap.get("invoiceType"));
                orderVo.setInvoiceTitle((String)dataMap.get("invoiceTitle"));
                orderVo.setDutyPara((String)dataMap.get("dutyPara"));
                orderVo.setRemark((String)dataMap.get("remark"));
                list.add(orderVo);
            }
        }
        return list;
    }


    @Override
    public ReceiptInfoVo getReceiptInfoByOrg(String orgId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("orgId",orgId);
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0005",parmMap);
        } catch (Exception e) {
            throw new BizException("收货信息查询失败！"+e.getMessage());
        }
        ReceiptInfoVo vo = null;
        if(CollectionUtil.isNotEmpty(report)){
            vo  =  ReceiptInfoVo.builder()
                    .orgId(orgId)
                    .orgName((String)report.get("orgName"))
                    .receiptUser((String)report.get("consignee"))
                    .receiptAddress((String)report.get("address")).build();
        }
        return vo;
    }

    @Override
    public Object queryIsSure(OrderDetailListVo orderDetailListVo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId" ,orderDetailListVo.getUserId());
        parmMap.put("orgId" ,orderDetailListVo.getOrgId());
        parmMap.put("orgName" ,orderDetailListVo.getOrgName());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0006",parmMap);
        } catch (Exception e) {
            throw new BizException("是否可以创建订单查询失败！"+e.getMessage());
        }
        return report;
    }

    @Override
    public Object createOrder(OrderInfoVo orderInfoVo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId",orderInfoVo.getUserId());
        parmMap.put("orderType",orderInfoVo.getOrderType());
        parmMap.put("orgId",orderInfoVo.getOrgId());
        parmMap.put("orgName",orderInfoVo.getOrgName());
        parmMap.put("consignee",orderInfoVo.getConsignee());
        parmMap.put("address",orderInfoVo.getAddress());
        parmMap.put("phone",orderInfoVo.getPhone());
        parmMap.put("invoiceType",orderInfoVo.getInvoiceType());
        parmMap.put("invoiceTitle",orderInfoVo.getInvoiceTitle());
        parmMap.put("dutyPara",orderInfoVo.getDutyPara());
        parmMap.put("remark",orderInfoVo.getRemark());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0007",parmMap);
        } catch (Exception e) {
            throw new BizException("新建订单失败！"+e.getMessage());
        }
        return report;
    }

    @Override
    public Object orderList(String orderType) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("orderType",orderType);
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0008",parmMap);
        } catch (Exception e) {
            throw new BizException("凭证名称列表查询失败！"+e.getMessage());
        }
        return report;

    }

    @Override
    public Object createOrderDetail(OrderDetailVo orderDetailVo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId",orderDetailVo.getUserId());
        parmMap.put("voucherId",orderDetailVo.getVoucherId());
        parmMap.put("orderId",orderDetailVo.getOrderId());
        parmMap.put("voucherName",orderDetailVo.getVoucherName());
        parmMap.put("voucherNo",orderDetailVo.getVoucherNo());
        parmMap.put("num",orderDetailVo.getNum());
        parmMap.put("cardName",orderDetailVo.getCardName());
        parmMap.put("cardNo",orderDetailVo.getCardNo());
        parmMap.put("remark",orderDetailVo.getRemark());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0009",parmMap);
        } catch (Exception e) {
            throw new BizException("新建订单明细失败！"+e.getMessage());
        }
        return report;
    }

    @Override
    public List<OrderDetailDo> queryDetailList(OrderQueryDetailVo orderQueryDetailVo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId",orderQueryDetailVo.getUserId());
        parmMap.put("orderId",orderQueryDetailVo.getOrderId());
        parmMap.put("pageIndex",orderQueryDetailVo.getPageIndex());
        parmMap.put("pageSize",orderQueryDetailVo.getPageSize());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0010",parmMap);
        } catch (Exception e) {
            throw new BizException("查询订单明细列表失败！"+e.getMessage());
        }
        List<OrderDetailDo> list = new ArrayList<>();
        Object ObjectData = report.get("data");
        if(isObjectIsNotEmpty(ObjectData)){
            Map<String,Object> dataMap = (Map<String,Object>) report.get("data");
            Object data = dataMap.get("data");
            if(data != null){
                List<Map<String,Object>> dataList = (List<Map<String,Object>>)data;
                if(CollectionUtil.isNotEmpty(dataList)){
                    for (int i = 0; i < dataList.size(); i++) {
                        OrderDetailDo orderDetailDo = new OrderDetailDo();
                        orderDetailDo.setOrderDeatiId((String)dataList.get(i).get("orderDeatiId"));
                        orderDetailDo.setDetailStatus((String)dataList.get(i).get("detailStatus"));
                        orderDetailDo.setVoucherName((String)dataList.get(i).get("voucherName"));
                        orderDetailDo.setVoucherNo((String)dataList.get(i).get("voucherNo"));
                        orderDetailDo.setCardName((String)dataList.get(i).get("cardName"));
                        orderDetailDo.setCardNo((String)dataList.get(i).get("cardNo"));
                        orderDetailDo.setPrice((String)dataList.get(i).get("price"));
                        orderDetailDo.setVoucherAmt((String)dataList.get(i).get("voucherAmt"));
                        orderDetailDo.setNum((String)dataList.get(i).get("num"));
                        orderDetailDo.setSpec((String)dataList.get(i).get("spec"));
                        orderDetailDo.setRemark((String)dataList.get(i).get("remark"));
                        orderDetailDo.setEnterNum((String)dataList.get(i).get("enterNum"));
                        list.add(orderDetailDo);
                    }
                }
            }else{
                OrderDetailDo orderDetailDo = new OrderDetailDo();
                orderDetailDo.setOrderDeatiId((String)dataMap.get("orderDeatiId"));
                orderDetailDo.setDetailStatus((String)dataMap.get("detailStatus"));
                orderDetailDo.setVoucherName((String)dataMap.get("voucherName"));
                orderDetailDo.setVoucherNo((String)dataMap.get("voucherNo"));
                orderDetailDo.setCardName((String)dataMap.get("cardName"));
                orderDetailDo.setCardNo((String)dataMap.get("cardNo"));
                orderDetailDo.setPrice((String)dataMap.get("price"));
                orderDetailDo.setVoucherAmt((String)dataMap.get("voucherAmt"));
                orderDetailDo.setNum((String)dataMap.get("num"));
                orderDetailDo.setSpec((String)dataMap.get("spec"));
                orderDetailDo.setRemark((String)dataMap.get("remark"));
                orderDetailDo.setEnterNum((String)dataMap.get("enterNum"));
                list.add(orderDetailDo);
            }
        }
        return list;
    }

    /**
     * 判断Object对象是否为空或""
     * @param object
     * @return
     */
    public static Boolean isObjectIsNotEmpty(Object object){
        String str = ObjectUtils.toString(object, "");
        boolean flag = StringUtils.isNotBlank(str);
        return flag;
    }

}
