package com.bank.esb.webservice.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.bank.core.entity.BizException;
import com.bank.esb.dao.DatWorkOrderDao;
import com.bank.esb.dos.*;
import com.bank.esb.dto.*;
import com.bank.esb.service.*;
import com.bank.esb.util.ESBUtil;
import com.bank.esb.vo.*;
import com.bank.esb.webservice.AutoMaticeDeviceService;
import com.bank.esb.webservice.entity.ESBRequestHeader;
import com.bank.esb.webservice.entity.ESBResponseHeader;
import com.bank.manage.dos.WorkWaterDO;
import com.bank.manage.service.ManageWorkWaterService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@WebService(name = "automaticedeviceservice", targetNamespace = "http://webservice.wbmp.com")
public class AutoMaticeDeviceServiceImpl implements AutoMaticeDeviceService {
    @Override
    public String esbService(String requestXml) {
        if (StringUtils.isBlank(requestXml)) {
            throw new BizException("ESB请求报文为空！");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestXml.getBytes());
        SAXReader builder = new SAXReader();
        //报文转xml文档
        Document doc;
        try {
            doc = builder.read(byteArrayInputStream);
        } catch (DocumentException e) {
            throw new BizException("ESB请求报文不符合xml格式！");
        }
        Element serviceElement = doc.getRootElement();
        Map<String, Object> requestMap = ESBUtil.elementTomap(serviceElement);

        Map<String, Object> header = (Map<String, Object>) requestMap.get("Header");
        Map<String, Object> request = (Map<String, Object>) requestMap.get("Body");
        Map<String, Object> body= (Map<String, Object>) request.get("Request");
        if (header == null) {
            throw new BizException("获取ESB请求报文头失败");
        }
        if (body == null) {
            throw new BizException("获取ESB请求报文体失败");
        }
        ESBRequestHeader requestHeader = JSON.parseObject(JSON.toJSONString(header), ESBRequestHeader.class);

        Map<String, Object> returnVO = new HashMap<String, Object>();
        switch (requestHeader.getServiceCode()) {
            case "WBMP10001"://工单查询
                OrderNumVo orderNumVO = JSON.parseObject(JSON.toJSONString(body), OrderNumVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(getOrders(orderNumVO)), Map.class);
                break;
            case "WBMP10002"://工单处理
                OrderDealWithVo orderDealWithVo = JSON.parseObject(JSON.toJSONString(body), OrderDealWithVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(orderDealWith(orderDealWithVo)), Map.class);
                break;
            case "WBMP10003"://机构列表接口
                InstitutionsVo institutionsVo = JSON.parseObject(JSON.toJSONString(body), InstitutionsVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(getInstitutions(institutionsVo)), Map.class);
                break;
            case "WBMP10004"://巡检单查询接口
                InspectionSheetVo inspectionSheetVo = JSON.parseObject(JSON.toJSONString(body), InspectionSheetVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(getInspectionSheet(inspectionSheetVo)), Map.class);
                break;
            case "WBMP10005"://巡检单创建接口
                InspectionSheetsVo inspectionSheet = JSON.parseObject(JSON.toJSONString(body), InspectionSheetsVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(getInspectionSheets(inspectionSheet)), Map.class);
                break;
            case "WBMP10007"://工程师列表查询接口    1
                EngineerVo engineerVo = JSON.parseObject(JSON.toJSONString(body), EngineerVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(getEngineer(engineerVo)), Map.class);
                break;
            case "WBMP10008"://工单分派查询
                EngineerDto engineerDto = JSON.parseObject(JSON.toJSONString(body), EngineerDto.class);
                returnVO = JSON.parseObject(JSON.toJSONString(getEngineer(engineerDto)), Map.class);
                break;
            case "WBMP10009"://状态变更（工程师到达现场）接口
                StateChangesVo changeStatus = JSON.parseObject(JSON.toJSONString(body), StateChangesVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(stateChanges(changeStatus)), Map.class);
                break;
            case "WBMP10010"://工单提交接口   1
                OrderSubmissionVo orderSubmissionVo = JSON.parseObject(JSON.toJSONString(body), OrderSubmissionVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(WBMP10010(orderSubmissionVo)), Map.class);
                break;
            case "WBMP10011"://投诉工单回复接口 1
                RepairOrderBVo repairOrderBVo = JSON.parseObject(JSON.toJSONString(body), RepairOrderBVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(repairOrder(repairOrderBVo)), Map.class);
                break;
            case "WBMP10012"://设备厂商列表查询接口   ?
                returnVO = JSON.parseObject(JSON.toJSONString(WBMP10012()), Map.class);
                break;
            case "WBMP10013"://设备详细信息查询接口   1
                returnVO = JSON.parseObject(JSON.toJSONString(WBMP10013(body.get("deviceId"))), Map.class);
                break;
            case "WBMP10014":
                TransferInformationVo transferInformationVo = JSON.parseObject(JSON.toJSONString(body), TransferInformationVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(getTransferInformation(transferInformationVo)), Map.class);
                break;
            case "WBMP10015"://服务信息查询接口
                TransferInformationVo orderId = JSON.parseObject(JSON.toJSONString(body), TransferInformationVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(WBMP10015(orderId)), Map.class);
                break;
            default:
                break;
        }

        Map<String, Object> response = new HashMap<String, Object>();
        Map<String, Object> responseHeader = new HashMap<String, Object>();
        responseHeader.putAll(header);
        ESBResponseHeader res = new ESBResponseHeader();
        res.setReturnCode("00000000");
        res.setProviderChannelId("812");
        res.setResponseTime(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        res.setReturnMessage("服务调用成功");
        res.setProviderReference(UUID.randomUUID().toString());
        res.setProviderWorkingDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        responseHeader.put("Response", JSON.parseObject(JSON.toJSONString(res), Map.class));

        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put("Response", returnVO);

        response.put("Header", responseHeader);
        response.put("Body", responseBody);

        return "<Service>" + ESBUtil.convert(response) + "</Service>";
    }

    private Object WBMP10010(OrderSubmissionVo orderSubmissionVo) {
        Map<Object, Object> map = MapUtil.newHashMap();
        //工单保存
        try {
            String orderNo = orderSubmissionVo.getOrderNo();
            WorkOrderDO workOrderDO = WorkOrderDO.builder()
                    .workOrderType(orderSubmissionVo.getOrderType())
                    .workOrderCode(orderNo)
                    .engineer(orderSubmissionVo.getEngineerId())
                    .workOrderDescribe(orderSubmissionVo.getOrderDescribe())
                    .build();

            workOrderService.saveOrUpdate(workOrderDO);

            //附件列表
            List<String> pictureUrl = orderSubmissionVo.getPictureUrl();
            ArrayList<WorkOrderAttachmentDO> list = new ArrayList<>();
            for (String url : pictureUrl) {
                list.add(new WorkOrderAttachmentDO(null, orderNo, url, url, null));
            }
            workOrderAttachmentService.saveBatch(list);
            map.put("repcode", "0");
        } catch (Exception e) {
            log.error(e.getMessage());
            map.put("repcode", "-1");
        }
        return map;
    }

    private Object WBMP10012() {
        Map<Object, Object> map = MapUtil.newHashMap();
        map.put("repcode", "1");
        map.put("List", esbService.getCSInfo());
        return map;

    }

    private ResponServiceInformationDto WBMP10015(TransferInformationVo transferInformationVo) {
        String orderId = transferInformationVo.getOrderId();
        ResponServiceInformationDto responseDto = new ResponServiceInformationDto();
        WorkOrderDO orderDO = workOrderService.getOne(new LambdaQueryWrapper<WorkOrderDO>().eq(WorkOrderDO::getWorkOrderCode, orderId));
        ServiceInformationDto dto = new ServiceInformationDto();
        if (orderDO != null) {
            String engineerId = orderDO.getEngineer();
            dto.setEngineerId(engineerId);
            Map<String,String> engineerInfo = esbService.getEngineerInfo(engineerId);
            dto.setEngineerName(engineerInfo.get("NAME"));
            dto.setEngineerPhone(engineerInfo.get("TELEPHONE"));
            //主管信息
            String serverId = orderDO.getDirector();
            Map<String,String> serverInfo = esbService.getEngineerInfo(serverId);
            dto.setServerId(serverId);
            dto.setServerName(serverInfo.get("NAME"));
            dto.setServerPhone(serverInfo.get("TELEPHONE"));

            String workOrderType = orderDO.getWorkOrderType();
            if (workOrderType.equals("01")){
                dto.setProcessMode(orderDO.getDealType());
            }else if (workOrderType.equals("03")){
                dto.setProcessMode(orderDO.getEscortsHandling());
            }
            List<WorkWaterDO> workWaterDOS = workWaterService.list(new LambdaQueryWrapper<WorkWaterDO>().eq(WorkWaterDO::getWordOrderId, orderId).eq(WorkWaterDO::getDealWithType, "4"));
            if (workWaterDOS.size()==1){
                dto.setFinishTime(workWaterDOS.get(0).getDealWithTime().toString());
            }else {
                dto.setFinishTime("");
            }
            dto.setServiceProvider(serverInfo.get("fws"));

            responseDto.setRepcode("0");
        } else {
            responseDto.setRepcode("-1");
        }

        ArrayList<ServiceInformationDto> serviceInformationDtos = new ArrayList<>();
        serviceInformationDtos.add(dto);
        responseDto.setServiceInformationDtoList(serviceInformationDtos);
        return responseDto;
    }

    private Object WBMP10013(Object deviceId) {
        Map<Object, Object> map = MapUtil.newHashMap();
        map.put("repcode", "0");
        Map<String, Object> deviceInfo = null;
        try {
            deviceInfo = esbService.getDeviceInfo(deviceId.toString());
            deviceInfo.put("serverList", esbService.getManager(deviceId));
            map.put("List", deviceInfo);
        } catch (Exception e) {
            log.error(e.getMessage());
            map.put("repcode", "-1");
        }
        return map;
    }


    @Resource
    DatWorkOrderDao datWorkOrderDao;

    private ResponseDto getOrders(OrderNumVo orderNumVo) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus("0");
        int pageIndex = orderNumVo.getPageIndex();
        int pageSize = orderNumVo.getPageSize();
        responseDto.setPageIndex(pageIndex);
        responseDto.setPageSize(pageSize);
        orderNumVo.setPageIndex((pageIndex - 1) * pageSize);
        List<OrderDto> orderDtoList = datWorkOrderDao.queryOrders(orderNumVo);
        String orderType = orderNumVo.getOrderType();
        if (orderType != null && orderNumVo.getUserId() != null
                && orderNumVo.getRelated() != null
                && orderNumVo.equals("1")) {
            List<OrderDto> esblist = esbService.getEsbErrOrder(orderNumVo);

        }

        responseDto.setList(orderDtoList);

        return responseDto;
    }

    @Resource
    WorkOrderAttachmentService workOrderAttachmentService;

    @Resource
    ManageWorkWaterService workWaterService;

    @Resource
    WorkOrderService workOrderService;

    private OrderDealWithDto orderDealWith(OrderDealWithVo orderDealWithVo) {
        OrderDealWithDto orderDealWithDto = new OrderDealWithDto();
        orderDealWithDto.setRepcode("0");
        String orderNo = orderDealWithVo.getOrderNo();
        WorkOrderDO orderDO = workOrderService.getOne(new LambdaQueryWrapper<WorkOrderDO>().eq(WorkOrderDO::getWorkOrderCode, orderNo));
        if (orderDO != null) {
            //更新工单表
            String engineerId = orderDealWithVo.getEngineerId();
            String processMode = orderDealWithVo.getProcessMode();
            String serviceDescribe = orderDealWithVo.getServiceDescribe();
            orderDO.setEngineer(engineerId);
            orderDO.setDealType(processMode);
            orderDO.setDealNote(serviceDescribe);
            workOrderService.saveOrUpdate(orderDO);

            //插入流水
            workWaterService.save(new WorkWaterDO(null, null, orderNo,
                    processMode, LocalDateTime.now()
                    , engineerId, serviceDescribe, null, null, null
            ));

            //附件保存
            List<String> pictureUrl = orderDealWithVo.getPictureUrl();
            List list = new ArrayList<WorkOrderAttachmentDO>();
            if (pictureUrl.size() > 0) {
                for (String url : pictureUrl) {
                    list.add(new WorkOrderAttachmentDO(null, orderNo, url,
                            url, null));
                }

            }
            workOrderAttachmentService.saveBatch(list);
        } else {
            orderDealWithDto.setRepcode("-1");
        }
        return orderDealWithDto;
    }

    private ResponseInstitutionsDto getInstitutions(InstitutionsVo institutionsVo) {
        ResponseInstitutionsDto responseInstitutionsDto = new ResponseInstitutionsDto();
        responseInstitutionsDto.setRepcode("0");
        String orgId = institutionsVo.getOrgId();
        List<InstitutionsDto> list = new ArrayList<>();
        if (StrUtil.isBlankIfStr(orgId)) {
            List<DatBranchDO> datBranchDOS = esbService.getBranch(orgId);
            if (datBranchDOS.size() > 0) {
                for (DatBranchDO datBranchDO : datBranchDOS) {
                    list.add(new InstitutionsDto(datBranchDO.getStrbranchnum(), datBranchDO.getStrbranchname()));
                }
            } else {
                List<DatSubbranchDO> subbranchDOS = esbService.getSubBranch(orgId);
                if (subbranchDOS.size() > 0) {
                    for (DatSubbranchDO subbranchDO : subbranchDOS) {
                        list.add(new InstitutionsDto(subbranchDO.getStrsubbranchnum(), subbranchDO.getStrsubbranchname()));
                    }
                } else {
                    List<DatSelfsvcbankDO> selfsvcbankDOS = esbService.getSelfBranch(orgId);
                    if (selfsvcbankDOS.size() > 0) {
                        for (DatSelfsvcbankDO selfsvcbankDO : selfsvcbankDOS) {
                            list.add(new InstitutionsDto(selfsvcbankDO.getStrssbnum(), selfsvcbankDO.getStrssbname()));
                        }
                    }

                }
            }
        }
        responseInstitutionsDto.setInstitutionsDtoList(list);
        return responseInstitutionsDto;
    }

    private ResponseInspectionSheetDto getInspectionSheet(InspectionSheetVo inspectionSheetVo) {
        ResponseInspectionSheetDto responseInspectionSheetDto = new ResponseInspectionSheetDto();
        responseInspectionSheetDto.setRepcode("0");
        List<InspectionSheetDto> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            InspectionSheetDto institutionsDto = new InspectionSheetDto();
            institutionsDto.setOrgId("10010" + i);
            institutionsDto.setDeviceProperty("阿萨");
            institutionsDto.setInprocessNo("1111");
            institutionsDto.setOrgAddress("湖南省");
            institutionsDto.setSerialNum("101");
            institutionsDto.setWarrantyTime(new Date());
            list.add(institutionsDto);
        }
        responseInspectionSheetDto.setInspectionSheetDtoList(list);
        return responseInspectionSheetDto;
    }

    private InspectionSheetsDto getInspectionSheets(InspectionSheetsVo inspectionSheetsVo) {
        InspectionSheetsDto inspectionSheetsDto = new InspectionSheetsDto();
        inspectionSheetsDto.setRepcode("0");
        //巡检单创建
        WorkOrderDO workOrderDO = WorkOrderDO.builder()
                .terminalCode(inspectionSheetsVo.getDeviceNo())
                .workOrderType("3")
                .workOrderCode(UUID.randomUUID().toString())
                .workOrderStatus("0")
                .escortsPatrol(inspectionSheetsVo.getAccompany())
                .escortsStartTime(DateUtil.parseLocalDateTime(inspectionSheetsVo.getStartTime()))
                .escortsCompleteTime(DateUtil.parseLocalDateTime(inspectionSheetsVo.getEndTime()))
                .escortsHandling(inspectionSheetsVo.getProcessMode())
                .workOrderDescribe(inspectionSheetsVo.getOrderDescribe()).build();
        workOrderService.save(workOrderDO);
        return inspectionSheetsDto;
    }

    @Resource
    EsbService esbService;

    private ResponseEngineerDto getEngineer(EngineerVo engineerVo) {
        ResponseEngineerDto responseEngineerDto = new ResponseEngineerDto();
        responseEngineerDto.setRepcode("0");
        List<EngineerDto> engineerDtoList = esbService.getEngineer(engineerVo.getEngineerMId(), engineerVo.getSeachTxt());
        responseEngineerDto.setEngineerDtoList(engineerDtoList);
        return responseEngineerDto;
    }

    private ResponseEngineerDto getEngineer(EngineerDto engineerDto) {
        ResponseEngineerDto responseEngineerDto = new ResponseEngineerDto();
        responseEngineerDto.setRepcode("1");
        String engineerId = engineerDto.getEngineerId();
        String orderId = engineerDto.getOrderId();
        //工单分派
        WorkOrderDO orderDO = workOrderService.getOne(new LambdaQueryWrapper<WorkOrderDO>().eq(WorkOrderDO::getWorkOrderCode, orderId));
        if (orderDO != null) {
            orderDO.setEngineer(engineerId);
            workOrderService.saveOrUpdate(orderDO);

        }
        //插入流水
        workWaterService.save(new WorkWaterDO(null, null, orderId,
                "2", LocalDateTime.now()
                , engineerId, "工单分派", null, null, engineerDto.getEngineerName()
        ));
        return responseEngineerDto;
    }

    private StateChangesDto stateChanges(StateChangesVo stateChangesVo) {
        StateChangesDto stateChangesDto = new StateChangesDto();
        stateChangesDto.setRepcode("0");
        String engineerId = stateChangesVo.getEngineerId();
        String orderId = stateChangesVo.getOrderNo();
        //更改状态
        workWaterService.save(new WorkWaterDO(null, null, orderId,
                "3", LocalDateTime.now()
                , engineerId, "工程师到达现场处理状态变更", null, null, null
        ));
        return stateChangesDto;
    }

    private RepairOrderBDto repairOrder(RepairOrderBVo repairOrderBVo) {
        RepairOrderBDto repairOrderBDto = new RepairOrderBDto();
        String orderNo = repairOrderBVo.getOrderNo();
        String engineerId = repairOrderBVo.getEngineerId();
        //获取工单
        WorkOrderDO orderDO = workOrderService.getOne(new LambdaQueryWrapper<WorkOrderDO>().eq(WorkOrderDO::getWorkOrderCode, orderNo));
        if (orderDO != null) {
            orderDO.setEngineer(engineerId);
            orderDO.setSuggestion(repairOrderBVo.getOrderDescribe());//记录厂商回复意见
            orderDO.setWorkOrderStatus("5");
            workOrderService.saveOrUpdate(orderDO);
            repairOrderBDto.setRepcode("0");
        } else {
            repairOrderBDto.setRepcode("-1");
        }
        return repairOrderBDto;
    }

    private ResonseTransferInformationDto getTransferInformation(TransferInformationVo transferInformationVo) {
        String orderId = transferInformationVo.getOrderId();
        ResonseTransferInformationDto resonseTransferInformationDto = new ResonseTransferInformationDto();
        List<Map<String,String>> workWaterDOS = workWaterService.getwater(orderId);
        ArrayList<TransferInformationDto> list = new ArrayList<>();
        for (Map<String,String> waterDO : workWaterDOS) {
            TransferInformationDto dto = TransferInformationDto.builder()
                    .operatorUserId(waterDO.get("uid"))
                    .operatorUserName(waterDO.get("name"))
                    .operatorUserPhone(waterDO.get("phone"))
                    .operatorStatus(waterDO.get("operatorStatus"))
                    .installDate(null)
                    .build();
            list.add(dto);
        }
        resonseTransferInformationDto.setRepcode("0");
        resonseTransferInformationDto.setTransferInformationDtoList(list);
        if (workWaterDOS.size()>0){
            resonseTransferInformationDto.setOrderStatus(workWaterDOS.get(0).get("status"));
        }
        return resonseTransferInformationDto;
    }

}
