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
        parmMap.put("userId", voucherStockDo.getUserId());
        parmMap.put("orgId", getOrgId(voucherStockDo.getOrgId()));
        parmMap.put("voucherStatus", voucherStockDo.getVoucherStatus());
        parmMap.put("voucherNo", voucherStockDo.getVoucherNo());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0016", parmMap);
        } catch (Exception e) {
            throw new BizException("库存查询失败！" + e.getMessage());
        }
        List<VoucherStockVo> list = new ArrayList<>();
        Object objectData = report.get("data");
        if (isObjectIsNotEmpty(objectData)) {
            Map<String, Object> dataMap = (Map<String, Object>) report.get("data");
            Object data = dataMap.get("data");
            if (data != null) {//多条数据
                List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
                if (CollectionUtil.isNotEmpty(dataList)) {
                    for (int i = 0; i < dataList.size(); i++) {
                        VoucherStockVo vo = new VoucherStockVo();
                        vo.setNum((String) dataList.get(i).get("num"));
                        vo.setVoucherName((String) dataList.get(i).get("voucherName"));
                        vo.setVoucherStatus((String) dataList.get(i).get("voucherStatus"));
                        vo.setVoucherNo((String) dataList.get(i).get("voucherNo"));
                        list.add(vo);
                    }
                }
            } else {
                VoucherStockVo vo = new VoucherStockVo();
                vo.setNum((String) report.get("num"));
                vo.setVoucherName((String) report.get("voucherName"));
                vo.setVoucherStatus((String) report.get("voucherStatus"));
                vo.setVoucherNo((String) report.get("voucherNo"));
                list.add(vo);
            }
        }
        return list;
    }

    @Override
    public List queryVoucherNumber(VoucherNumberDo voucherNumberDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", voucherNumberDo.getUserId());
        parmMap.put("orderId", getOrgId(voucherNumberDo.getOrderId()));
        parmMap.put("voucherNo", voucherNumberDo.getVoucherNo());
        parmMap.put("orderDeatilId", voucherNumberDo.getOrderDeatilId());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0015", parmMap);
        } catch (Exception e) {
            throw new BizException("号段查看失败！" + e.getMessage());
        }
        List<VoucherNumberVo> list = new ArrayList<>();
        Object objectData = report.get("data");
        if (isObjectIsNotEmpty(objectData)) {
            Map<String, Object> dataMap = (Map<String, Object>) report.get("data");
            Object data = dataMap.get("data");
            if (data != null) {//多条数据
                List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
                if (CollectionUtil.isNotEmpty(dataList)) {
                    for (int i = 0; i < dataList.size(); i++) {
                        VoucherNumberVo vo = new VoucherNumberVo();
                        vo.setStartNo((String) report.get("startNo"));
                        vo.setEndNo((String) report.get("endNo"));
                        vo.setNum((Integer) report.get("num"));
                        list.add(vo);
                    }
                }
            } else {
                VoucherNumberVo vo = new VoucherNumberVo();
                vo.setStartNo((String) report.get("startNo"));
                vo.setEndNo((String) report.get("endNo"));
                vo.setNum((Integer) report.get("num"));
                list.add(vo);
            }
        }
        return list;
    }

    @Override
    public Object voucherNumberSave(InputVoucherNumberVo vo) {
        Map<String, Object> parmMap = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        parmMap.put("userId", vo.getUserId());
        parmMap.put("orderId", vo.getOrderId());
        parmMap.put("orderDeatilId", vo.getOrderDeatilId());
        parmMap.put("voucherNo", vo.getVoucherNo());
        List<VoucherNumberVo> data = vo.getData();
        for (VoucherNumberVo numberVo : data) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("startNo", numberVo.getStartNo());
            dataMap.put("endNo", numberVo.getEndNo());
            dataMap.put("num", numberVo.getNum());
            list.add(dataMap);
        }
        parmMap.put("data", list);
        Map report = null;
        try {
            report = SoapUtil.sendReportHd("VTMS0014", parmMap);
            report.put("ReturnCode", "00000000");
        } catch (Exception e) {
            throw new BizException("号段录入失败！" + e.getMessage());
        }
        return report;
    }

    @Override
    public Object updateOrderStatus(UpdateOrderStatusVo vo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", vo.getUserId());
        parmMap.put("userName", vo.getUserName());
        parmMap.put("orderId", vo.getOrderId());
        parmMap.put("orderDeatild", vo.getOrderDeatild());
        parmMap.put("operationObject", vo.getOperationObject());
        parmMap.put("operationType", vo.getOperationType());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0013", parmMap);
            report.put("ReturnCode", "00000000");
        } catch (Exception e) {
            throw new BizException("订单状态更新失败！" + e.getMessage());
        }
        return report;
    }

    @Override
    public Object deleteOrderDeatil(String userId, String orderId, String orderDeatild) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", userId);
        parmMap.put("orderId", orderId);
        parmMap.put("orderDeatild", orderDeatild);
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0012", parmMap);
            report.put("ReturnCode", "00000000");
        } catch (Exception e) {
            throw new BizException("订单明细删除失败！" + e.getMessage());
        }
        return report;
    }

    @Override
    public Object updateOrderDetail(UpdateOrderDetailVo vo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", vo.getUserId());
        parmMap.put("orderId", vo.getOrderId());
        parmMap.put("orderDeatild", vo.getOrderDeatild());
        parmMap.put("voucherName", vo.getVoucherName());
        parmMap.put("voucherNo", vo.getVoucherNo());
        parmMap.put("num", vo.getNum());
        parmMap.put("cardName", vo.getCardName());
        parmMap.put("cardNo", vo.getCardNo());
        parmMap.put("remark", vo.getRemark());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0011", parmMap);
            report.put("ReturnCode", "00000000");
        } catch (Exception e) {
            throw new BizException("订单明细修改失败！" + e.getMessage());
        }
        return report;
    }

    @Override
    public RoleInfoVo getRoleInfo(TokenUserInfo tokenUserInfo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", tokenUserInfo.getUserId());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0001", parmMap);
        } catch (Exception e) {
            throw new BizException("角色信息查询失败！" + e.getMessage());
        }
        RoleInfoVo roleInfoVo = new RoleInfoVo();
        roleInfoVo.setOrgId((String) report.get("orgId"));
        roleInfoVo.setOrgName((String) report.get("orgName"));
        roleInfoVo.setRoleId((String) report.get("roleId"));
        roleInfoVo.setRoleName((String) report.get("roleName"));
        return roleInfoVo;
    }

    @Override
    public List<MatterListVo> getMatterList(MatterListQueryVo matterListQueryVo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", matterListQueryVo.getUserId());
        parmMap.put("type", matterListQueryVo.getType());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0002", parmMap);
        } catch (Exception e) {
            throw new BizException("事项列表查询失败！" + e.getMessage());
        }
        List<MatterListVo> list = new ArrayList<>();
        Object objectData = report.get("data");
        if (isObjectIsNotEmpty(objectData)) {
            List<Map<String, Object>> dataList = new ArrayList<>();
            if (objectData instanceof HashMap) {
                dataList.add((Map<String, Object>) report.get("data"));
            } else {
                dataList = (List<Map<String, Object>>) report.get("data");
            }
            //List<Map<String,Object>> dataList =(List<Map<String,Object>> )report.get("data");
            if (CollectionUtil.isNotEmpty(dataList)) {
                for (int i = 0; i < dataList.size(); i++) {
                    MatterListVo vo = new MatterListVo();
                    vo.setOrderType((String) dataList.get(i).get("orderType"));
                    vo.setContent((String) dataList.get(i).get("content"));
                    vo.setCreateTime((String) dataList.get(i).get("createTime"));
                    vo.setEventName((String) dataList.get(i).get("eventName"));
                    vo.setEventID((String) dataList.get(i).get("eventID"));
                    vo.setType((String) dataList.get(i).get("type"));
                    list.add(vo);
                }
            }
        }
        return list;
    }


    @Override
    public IPage<VoucherListVo> getVoucherList(VoucherListQueryVo vo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", vo.getUserId());
        parmMap.put("orgId", getOrgId(vo.getOrgId()));
        parmMap.put("pageIndex", vo.getPageIndex());
        parmMap.put("pageSize", vo.getPageSize());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0003", parmMap);
        } catch (Exception e) {
            throw new BizException("凭证订单列表查询失败！" + e.getMessage());
        }
        IPage<VoucherListVo> page = new Page<>();
        List<VoucherListVo> list = new ArrayList<>();
        Object objectData = report.get("data");
        Object objectTotal = report.get("recordSize");
        if (isObjectIsNotEmpty(objectData)) {
            List<Map<String, Object>> dataList = new ArrayList<>();
            if (objectData instanceof HashMap) {
                dataList.add((Map<String, Object>) report.get("data"));
            } else {
                dataList = (List<Map<String, Object>>) report.get("data");
            }

            if (CollectionUtil.isNotEmpty(dataList)) {
                for (int i = 0; i < dataList.size(); i++) {
                    VoucherListVo voucherListVo = new VoucherListVo();
                    voucherListVo.setListType((String) dataList.get(i).get("listType"));
                    voucherListVo.setOrderType((String) dataList.get(i).get("orderType"));
                    voucherListVo.setOrderNo((String) dataList.get(i).get("orderNo"));
                    voucherListVo.setOrderCycle((String) dataList.get(i).get("orderCycle"));
                    voucherListVo.setOrderStatus((String) dataList.get(i).get("orderStatus"));
                    voucherListVo.setOrgId((String) dataList.get(i).get("orgId"));
                    voucherListVo.setOrgName((String) dataList.get(i).get("orgName"));
                    list.add(voucherListVo);
                }
            }
        }
        page.setTotal(Integer.parseInt(objectTotal.toString()));
        page.setRecords(list);
        return page;
    }

    @Override
    public List<OrderVo> getOrderInfo(OrderQueryVo orderQueryVo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", orderQueryVo.getUserId());
        parmMap.put("orderId", orderQueryVo.getOrderId());
        parmMap.put("orderType", orderQueryVo.getOrderType());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0004", parmMap);
        } catch (Exception e) {
            throw new BizException("订单查询失败！" + e.getMessage());
        }
        List<OrderVo> list = new ArrayList<>();
        Object objectData = report.get("data");//判断data节点是否为""
        if (isObjectIsNotEmpty(objectData)) {
            Map<String, Object> dataMap = (Map<String, Object>) report.get("data");
            Object data = dataMap.get("data");
            if (data != null) {//多条数据
                List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
                if (CollectionUtil.isNotEmpty(dataList)) {
                    for (int i = 0; i < dataList.size(); i++) {
                        OrderVo orderVo = new OrderVo();
                        orderVo.setListType((String) dataList.get(i).get("listType"));
                        orderVo.setOrderType((String) dataList.get(i).get("orderType"));
                        orderVo.setOrderNo((String) dataList.get(i).get("orderNo"));
                        orderVo.setOrderCycle((String) dataList.get(i).get("orderCycle"));
                        orderVo.setOrderStatus((String) dataList.get(i).get("orderStatus"));
                        orderVo.setOrgId((String) dataList.get(i).get("orgId"));
                        orderVo.setOrgName((String) dataList.get(i).get("orgName"));
                        orderVo.setCreateUser((String) dataList.get(i).get("createUser"));
                        orderVo.setCreateDate((String) dataList.get(i).get("createDate"));
                        orderVo.setVoucherCount((String) dataList.get(i).get("voucherCount"));
                        orderVo.setVoucherAmt((String) dataList.get(i).get("voucherAmt"));
                        orderVo.setConsignee((String) dataList.get(i).get("consignee"));
                        orderVo.setAddress((String) dataList.get(i).get("address"));
                        orderVo.setPhone((String) dataList.get(i).get("phone"));
                        orderVo.setInvoiceType((String) dataList.get(i).get("invoiceType"));
                        orderVo.setInvoiceTitle((String) dataList.get(i).get("invoiceTitle"));
                        orderVo.setDutyPara((String) dataList.get(i).get("dutyPara"));
                        orderVo.setRemark((String) dataList.get(i).get("remark"));
                        list.add(orderVo);
                    }
                }
            } else {
                OrderVo orderVo = new OrderVo();
                orderVo.setListType((String) dataMap.get("listType"));
                orderVo.setOrderType((String) dataMap.get("orderType"));
                orderVo.setOrderNo((String) dataMap.get("orderNo"));
                orderVo.setOrderCycle((String) dataMap.get("orderCycle"));
                orderVo.setOrderStatus((String) dataMap.get("orderStatus"));
                orderVo.setOrgId((String) dataMap.get("orgId"));
                orderVo.setOrgName((String) dataMap.get("orgName"));
                orderVo.setCreateUser((String) dataMap.get("createUser"));
                orderVo.setCreateDate((String) dataMap.get("createDate"));
                orderVo.setVoucherCount((String) dataMap.get("voucherCount"));
                orderVo.setVoucherAmt((String) dataMap.get("voucherAmt"));
                orderVo.setConsignee((String) dataMap.get("consignee"));
                orderVo.setAddress((String) dataMap.get("address"));
                orderVo.setPhone((String) dataMap.get("phone"));
                orderVo.setInvoiceType((String) dataMap.get("invoiceType"));
                orderVo.setInvoiceTitle((String) dataMap.get("invoiceTitle"));
                orderVo.setDutyPara((String) dataMap.get("dutyPara"));
                orderVo.setRemark((String) dataMap.get("remark"));
                list.add(orderVo);
            }
        }
        return list;
    }


    @Override
    public ReceiptInfoVo getReceiptInfoByOrg(String orgId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("orgId", getOrgId(orgId));
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0005", parmMap);
        } catch (Exception e) {
            throw new BizException("收货信息查询失败！" + e.getMessage());
        }
        ReceiptInfoVo vo = null;
        if (CollectionUtil.isNotEmpty(report)) {
            vo = ReceiptInfoVo.builder()
                    .orgId(orgId)
                    .orgName((String) report.get("orgName"))
                    .receiptUser((String) report.get("consignee"))
                    .receiptAddress((String) report.get("address"))
                    .phone((String) report.get("consigneePhone"))
                    .invoiceType((String) report.get("invoiceType"))
                    .invoiceTitle((String) report.get("invoiceTitle"))
                    .dutyParagraph((String) report.get("taxId")).build();
        }
        return vo;
    }

    @Override
    public Object queryIsSure(OrderDetailListVo orderDetailListVo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", orderDetailListVo.getUserId());
        parmMap.put("orgId", getOrgId(orderDetailListVo.getOrgId()));
        parmMap.put("orgName", orderDetailListVo.getOrgName());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0006", parmMap);
        } catch (Exception e) {
            throw new BizException("是否可以创建订单查询失败！" + e.getMessage());
        }
        return report;
    }

    @Override
    public Object createOrder(OrderInfoVo orderInfoVo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", orderInfoVo.getUserId());
        parmMap.put("orderType", orderInfoVo.getOrderType());
        parmMap.put("orgId", getOrgId(orderInfoVo.getOrgId()));
        parmMap.put("orgName", orderInfoVo.getOrgName());
        parmMap.put("consignee", orderInfoVo.getConsignee());
        parmMap.put("address", orderInfoVo.getAddress());
        parmMap.put("phone", orderInfoVo.getPhone());
        parmMap.put("invoiceType", orderInfoVo.getInvoiceType());
        parmMap.put("invoiceTitle", orderInfoVo.getInvoiceTitle());
        parmMap.put("dutyPara", orderInfoVo.getDutyPara());
        parmMap.put("remark", orderInfoVo.getRemark() == null ? "" : orderInfoVo.getRemark());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0007", parmMap);
            report.put("ReturnCode", "00000000");
        } catch (Exception e) {
            throw new BizException("新建订单失败！" + e.getMessage());
        }
        return report;
    }

    @Override
    public Object orderList(String orderType) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("orderType", orderType);
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0008", parmMap);
        } catch (Exception e) {
            throw new BizException("凭证名称列表查询失败！" + e.getMessage());
        }
        return report;

    }

    @Override
    public Object createOrderDetail(OrderDetailVo orderDetailVo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", orderDetailVo.getUserId());
        parmMap.put("voucherId", orderDetailVo.getVoucherId());
        parmMap.put("orderId", orderDetailVo.getOrderId());
        parmMap.put("voucherName", orderDetailVo.getVoucherName());
        parmMap.put("voucherNo", orderDetailVo.getVoucherNo());
        parmMap.put("num", orderDetailVo.getNum());
        parmMap.put("cardName", orderDetailVo.getCardName());
        parmMap.put("cardNo", orderDetailVo.getCardNo());
        parmMap.put("remark", orderDetailVo.getRemark() == null ? "" : orderDetailVo.getRemark());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0009", parmMap);
            report.put("ReturnCode", "00000000");
        } catch (Exception e) {
            throw new BizException("新建订单明细失败！" + e.getMessage());
        }
        return report;
    }

    @Override
    public IPage<OrderDetailDo> queryDetailList(OrderQueryDetailVo orderQueryDetailVo) {
        IPage<OrderDetailDo> page = new Page<>();
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", orderQueryDetailVo.getUserId());
        parmMap.put("orderId", orderQueryDetailVo.getOrderId());
        parmMap.put("pageIndex", orderQueryDetailVo.getPageIndex());
        parmMap.put("pageSize", orderQueryDetailVo.getPageSize());
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0010", parmMap);
        } catch (Exception e) {
            throw new BizException("查询订单明细列表失败！" + e.getMessage());
        }
        List<OrderDetailDo> list = new ArrayList<>();
        Object ObjectData = report.get("data");
        Object objectTotal = report.get("recordSize");
        if (isObjectIsNotEmpty(ObjectData)) {
            Object dataMap = report.get("data");
            if (dataMap instanceof ArrayList) {
                List<Map<String, Object>> dataList = (List<Map<String, Object>>) dataMap;
                if (CollectionUtil.isNotEmpty(dataList)) {
                    for (int i = 0; i < dataList.size(); i++) {
                        OrderDetailDo orderDetailDo = new OrderDetailDo();
                        orderDetailDo.setOrderDeatiId((String) dataList.get(i).get("orderDeatiId"));
                        orderDetailDo.setDetailStatus((String) dataList.get(i).get("detailStatus"));
                        orderDetailDo.setVoucherName((String) dataList.get(i).get("voucherName"));
                        orderDetailDo.setVoucherNo((String) dataList.get(i).get("voucherNo"));
                        orderDetailDo.setCardName((String) dataList.get(i).get("cardName"));
                        orderDetailDo.setCardNo((String) dataList.get(i).get("cardNo"));
                        orderDetailDo.setPrice((String) dataList.get(i).get("price"));
                        orderDetailDo.setVoucherAmt((String) dataList.get(i).get("voucherAmt"));
                        orderDetailDo.setNum((String) dataList.get(i).get("num"));
                        orderDetailDo.setSpec((String) dataList.get(i).get("spec"));
                        orderDetailDo.setRemark((String) dataList.get(i).get("remark"));
                        orderDetailDo.setEnterNum((String) dataList.get(i).get("enterNum"));
                        list.add(orderDetailDo);
                    }
                }
            } else {
                Map<String, Object> data = (Map<String, Object>) dataMap;
                OrderDetailDo orderDetailDo = new OrderDetailDo();
                orderDetailDo.setOrderDeatiId((String) data.get("orderDeatiId"));
                orderDetailDo.setDetailStatus((String) data.get("detailStatus"));
                orderDetailDo.setVoucherName((String) data.get("voucherName"));
                orderDetailDo.setVoucherNo((String) data.get("voucherNo"));
                orderDetailDo.setCardName((String) data.get("cardName"));
                orderDetailDo.setCardNo((String) data.get("cardNo"));
                orderDetailDo.setPrice((String) data.get("price"));
                orderDetailDo.setVoucherAmt((String) data.get("voucherAmt"));
                orderDetailDo.setNum((String) data.get("num"));
                orderDetailDo.setSpec((String) data.get("spec"));
                orderDetailDo.setRemark((String) data.get("remark"));
                orderDetailDo.setEnterNum((String) data.get("enterNum"));
                list.add(orderDetailDo);
            }
        }
        page.setTotal(Integer.parseInt(objectTotal.toString()));
        page.setRecords(list);
        return page;
    }

    /**
     * 查询待办 列表
     *
     * @param waitListQueryVo 查询参数
     */
    @Override
    public List<VoucherWaitListVo> getWaitList(WaitListQueryVo waitListQueryVo) {
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("userId", waitListQueryVo.getUserId());
        parmMap.put("orgId", getOrgId(waitListQueryVo.getOrgId()));
        parmMap.put("pageIndex", 1);
        parmMap.put("pageSize", 100);
        Map report = null;
        try {
            report = SoapUtil.sendReport("VTMS0003", parmMap);
        } catch (Exception e) {
            throw new BizException("凭证订单列表查询失败！" + e.getMessage());
        }
        List<VoucherWaitListVo> list = new ArrayList<>();
        Object objectData = report.get("data");
        Object objectTotal = report.get("recordSize");
        if (isObjectIsNotEmpty(objectData)) {
            List<Map<String, Object>> dataList = new ArrayList<>();
            if (objectData instanceof HashMap) {
                dataList.add((Map<String, Object>) report.get("data"));
            } else {
                dataList = (List<Map<String, Object>>) report.get("data");
            }
            if (CollectionUtil.isNotEmpty(dataList)) {
                for (int i = 0; i < dataList.size(); i++) {
                    if ("4".equals(dataList.get(i).get("orderStatus"))) {
                        VoucherWaitListVo voucherWaitListVo = new VoucherWaitListVo();
                        voucherWaitListVo.setListType((String) dataList.get(i).get("listType"));
                        voucherWaitListVo.setOrderType((String) dataList.get(i).get("orderType"));
                        voucherWaitListVo.setOrderNo((String) dataList.get(i).get("orderNo"));
                        voucherWaitListVo.setOrderCycle((String) dataList.get(i).get("orderCycle"));
                        voucherWaitListVo.setOrderStatus((String) dataList.get(i).get("orderStatus"));
                        voucherWaitListVo.setOrgId((String) dataList.get(i).get("orgId"));
                        voucherWaitListVo.setOrgName((String) dataList.get(i).get("orgName"));
                        voucherWaitListVo.setName("凭证订单确认");
                        OrderQueryVo queryVo = new OrderQueryVo();
                        queryVo.setOrderId((String) dataList.get(i).get("orderNo"));
                        queryVo.setUserId(getOrgId(waitListQueryVo.getOrgId()));
                        queryVo.setOrderType((String) dataList.get(i).get("orderType"));
                        List<OrderVo> listOrderInfo = getOrderInfo(queryVo);
                        if (listOrderInfo.size() > 0) {
                            voucherWaitListVo.setDate(listOrderInfo.get(0).getCreateDate());
                        }

                        voucherWaitListVo.setContent((String) dataList.get(i).get("orgName") + "+" + getOrderType((String) dataList.get(i).get("orderType")));
                        list.add(voucherWaitListVo);
                    }
                }
            }
        }
        return list;
    }


    /**
     * 获取订单类型
     *
     * @param orderTypeId 订单类型ID
     * @return
     */
    private String getOrderType(String orderTypeId) {
        String orderType = "";
        switch (orderTypeId) {
            case "1":
                orderType = "重要空白凭证订单";
            case "2":
                orderType = "非重要空白凭证订单";
            case "3":
                orderType = "分行代理凭证订单";
        }
        return orderType;
    }

    /**
     * 核心机构号判断是否为6位、6为+01
     *
     * @param orgId
     * @return
     */
    public static String getOrgId(String orgId) {
        if (orgId.length() == 6) {
            return orgId + "01";
        }
        return orgId;
    }


    /**
     * 判断Object对象是否为空或""
     *
     * @param object
     * @return
     */
    public static Boolean isObjectIsNotEmpty(Object object) {
        String str = ObjectUtils.toString(object, "");
        boolean flag = StringUtils.isNotBlank(str);
        return flag;
    }

}
