package com.bank.esb.webservice.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
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

@Service
@WebService(name = "automaticedeviceservice", targetNamespace = "http://webservice.wbmp.com")
public class AutoMaticeDeviceServiceImpl implements AutoMaticeDeviceService {
    @SuppressWarnings("unchecked")
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
        Map<String, Object> body = (Map<String, Object>) requestMap.get("Body");
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
            case "WBMP10007"://工程师列表查询接口
                EngineerVo engineerVo = JSON.parseObject(JSON.toJSONString(body), EngineerVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(getEngineer(engineerVo)), Map.class);
                break;
            case "WBMP10008":
                EngineerDto engineerDto = JSON.parseObject(JSON.toJSONString(body), EngineerDto.class);
                returnVO = JSON.parseObject(JSON.toJSONString(getEngineer(engineerDto)), Map.class);
                break;
            case "WBMP10009"://状态变更（工程师到达现场）接口
                StateChangesVo changeStatus = JSON.parseObject(JSON.toJSONString(body), StateChangesVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(stateChanges(changeStatus)), Map.class);
                break;
//            case "WBMP10010":
//                InspectionSheetsVo inspectionSheet = JSON.parseObject(JSON.toJSONString(body), InspectionSheetsVo.class);
//                returnVO = JSON.parseObject(JSON.toJSONString(getInspectionSheets(inspectionSheet)), Map.class);
//                break;
            case "WBMP10011"://投诉工单回复接口
                RepairOrderBVo repairOrderBVo = JSON.parseObject(JSON.toJSONString(body), RepairOrderBVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(repairOrder(repairOrderBVo)), Map.class);
                break;
//            case "WBMP10012":
//                InspectionSheetsVo inspectionSheet = JSON.parseObject(JSON.toJSONString(body), InspectionSheetsVo.class);
//                returnVO = JSON.parseObject(JSON.toJSONString(getInspectionSheets(inspectionSheet)), Map.class);
//                break;
            case "WBMP10013"://设备详细信息查询接口
                returnVO = JSON.parseObject(JSON.toJSONString(getDeviceInfo(body.get("deviceId"))), Map.class);
                break;
//            case "WBMP10014":
//                InspectionSheetsVo inspectionSheet = JSON.parseObject(JSON.toJSONString(body), InspectionSheetsVo.class);
//                returnVO = JSON.parseObject(JSON.toJSONString(getInspectionSheets(inspectionSheet)), Map.class);
//                break;
//            case "WBMP10015":
//                InspectionSheetsVo inspectionSheet = JSON.parseObject(JSON.toJSONString(body), InspectionSheetsVo.class);
//                returnVO = JSON.parseObject(JSON.toJSONString(getInspectionSheets(inspectionSheet)), Map.class);
//                break;
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

    private Object getDeviceInfo(Object deviceId) {
        Map<String, Object> deviceInfo = datWorkOrderDao.getDeviceInfo(deviceId.toString());
        deviceInfo.put("serverList","");
        return deviceInfo;
    }


    @Resource
    DatWorkOrderDao datWorkOrderDao;

    private ResponseDto getOrders(OrderNumVo orderNumVo) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus("0");
        int pageIndex = orderNumVo.getPageIndex();
        responseDto.setPageIndex(pageIndex);
        int pageSize = orderNumVo.getPageSize();
        responseDto.setPageSize(pageSize);
        orderNumVo.setPageIndex((pageIndex - 1) * pageSize);
        List<OrderDto> orderDtoList = datWorkOrderDao.queryOrders(orderNumVo);
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
                    , engineerId, serviceDescribe, null,null,null
                    ));

            //附件保存
            List<String> pictureUrl = orderDealWithVo.getPictureUrl();
            List list = new ArrayList<WorkOrderAttachmentDO>();
            if (pictureUrl.size()>0){
                for (String url : pictureUrl) {
                    list.add(new WorkOrderAttachmentDO(null,orderNo,url,
                            url.substring(url.lastIndexOf("/")),null));
                }

            }
            workOrderAttachmentService.saveBatch(list);
        } else {
            orderDealWithDto.setRepcode("-1");
        }
        return orderDealWithDto;
    }

    @Resource
    DatBranchService datBranchService;
    @Resource
    DatSubbranchService datSubbranchService;
    @Resource
    DatSelfsvcbankService datSelfsvcbankService;

    private ResponseInstitutionsDto getInstitutions(InstitutionsVo institutionsVo) {
        ResponseInstitutionsDto responseInstitutionsDto = new ResponseInstitutionsDto();
        responseInstitutionsDto.setRepcode("0");
        String orgId = institutionsVo.getOrgId();
        List<InstitutionsDto> list = new ArrayList<>();
        if (StrUtil.isBlankIfStr(orgId)){
            List<DatBranchDO> datBranchDOS = datBranchService.list(new LambdaQueryWrapper<DatBranchDO>().eq(DatBranchDO::getStrbanknum, orgId));
            if (datBranchDOS.size()>0){
                for (DatBranchDO datBranchDO : datBranchDOS) {
                    list.add(new InstitutionsDto(datBranchDO.getStrbranchnum(), datBranchDO.getStrbranchname()));
                }
            }else {
                List<DatSubbranchDO> subbranchDOS = datSubbranchService.list(new LambdaQueryWrapper<DatSubbranchDO>().eq(DatSubbranchDO::getStrbranchnum, orgId));
                if (subbranchDOS.size()>0){
                    for (DatSubbranchDO subbranchDO : subbranchDOS) {
                        list.add(new InstitutionsDto(subbranchDO.getStrsubbranchnum(), subbranchDO.getStrsubbranchname()));
                    }
                }else {
                    List<DatSelfsvcbankDO> selfsvcbankDOS = datSelfsvcbankService.list(new LambdaQueryWrapper<DatSelfsvcbankDO>().eq(DatSelfsvcbankDO::getStrsubbranchnum, orgId));
                    if (selfsvcbankDOS.size()>0){
                        for (DatSelfsvcbankDO selfsvcbankDO : selfsvcbankDOS) {
                            list.add(new InstitutionsDto(selfsvcbankDO.getStrssbnum(),selfsvcbankDO.getStrssbname()));
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
        List<Map<String, Object>> engineerDtoList = esbService.getEngineer(engineerVo.getEngineerMId(),engineerVo.getSeachTxt());
//        responseEngineerDto.setEngineerDtoList(engineerDtoList);
        return responseEngineerDto;
    }
    private ResponseEngineerDto getEngineer(EngineerDto engineerDto) {
        ResponseEngineerDto responseEngineerDto = new ResponseEngineerDto();
        responseEngineerDto.setRepcode("0");
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
                , engineerId, "工单分派", null,null,engineerDto.getEngineerName()
        ));
        return responseEngineerDto;
    }

    private RepairOrderDispatchDto getRepairOrderDispatch(RepairOrderDispatchVo repairOrderDispatchVo) {
        RepairOrderDispatchDto repairOrderDispatchDto = new RepairOrderDispatchDto();
        return repairOrderDispatchDto;
    }

    private StateChangesDto stateChanges(StateChangesVo stateChangesVo) {
        StateChangesDto stateChangesDto = new StateChangesDto();
        stateChangesDto.setRepcode("0");
        String engineerId = stateChangesVo.getEngineerId();
        String orderId = stateChangesVo.getOrderNo();
        //更改状态
        workWaterService.save(new WorkWaterDO(null, null, orderId,
                "3", LocalDateTime.now()
                , engineerId, "工程师到达现场处理状态变更", null,null,null
        ));
        return stateChangesDto;
    }

    private OrderSubmissionDto orderSubmission(OrderSubmissionVo orderSubmissionVo) {
        OrderSubmissionDto orderSubmissionDto = new OrderSubmissionDto();
        orderSubmissionDto.setRepcode("0");
        return orderSubmissionDto;
    }

    private RepairOrderBDto repairOrder(RepairOrderBVo repairOrderBVo) {
        RepairOrderBDto repairOrderBDto = new RepairOrderBDto();
        String orderNo = repairOrderBVo.getOrderNo();
        String engineerId = repairOrderBVo.getEngineerId();
        //获取工单
        WorkOrderDO orderDO = workOrderService.getOne(new LambdaQueryWrapper<WorkOrderDO>().eq(WorkOrderDO::getWorkOrderCode, orderNo));
        if (orderDO != null) {
            orderDO.setEngineer(engineerId);
            orderDO.setSuggestion(repairOrderBVo.getOrderDescribe());
            orderDO.setWorkOrderStatus("5");
            workOrderService.saveOrUpdate(orderDO);
            repairOrderBDto.setRepcode("0");
        }else {
            repairOrderBDto.setRepcode("-1");
        }
        return repairOrderBDto;
    }

    private ResponseEquipmentDetailDto getEquipmentDetail(EquipmentDetailVo equipmentDetailVo) {
        ResponseEquipmentDetailDto responseEquipmentDetailDto = new ResponseEquipmentDetailDto();
        responseEquipmentDetailDto.setRepcode("0");
        List<EquipmentDetailDto> equipmentDetailDtoList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            EquipmentDetailDto engineerDto = new EquipmentDetailDto();
            engineerDto.setAddress("湖南省" + i);
            engineerDto.setDeviceId("1" + i);
            engineerDto.setDeviceModel("asda");
            engineerDto.setDeviceVendor("asda");
            engineerDto.setOrgId("10010" + i);
            engineerDto.setInstallDate(new Date());
            engineerDto.setOrgName("测试" + i);
            List<ServiceSupervisorDto> serviceSupervisorDto = new ArrayList<>();
            ServiceSupervisorDto supervisorDto = new ServiceSupervisorDto();
            supervisorDto.setServerId("1" + i);
            supervisorDto.setServerName("擦拭" + i);
            serviceSupervisorDto.add(supervisorDto);
            engineerDto.setServiceSupervisorDtoList(serviceSupervisorDto);
            equipmentDetailDtoList.add(engineerDto);
        }
        responseEquipmentDetailDto.setEquipmentDetailDtoList(equipmentDetailDtoList);
        return responseEquipmentDetailDto;
    }

    private ResonseTransferInformationDto getTransferInformation(TransferInformationVo transferInformationVo) {
        ResonseTransferInformationDto resonseTransferInformationDto = new ResonseTransferInformationDto();
        return resonseTransferInformationDto;
    }

    private ResponServiceInformationDto getServiceInformation(ServiceInformationVo serviceInformationVo) {
        ResponServiceInformationDto responServiceInformationDto = new ResponServiceInformationDto();
        responServiceInformationDto.setRepcode("0");
        List<ServiceInformationDto> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ServiceInformationDto serviceInformationDto = new ServiceInformationDto();
            serviceInformationDto.setEngineerId("1" + i);
            serviceInformationDto.setEngineerName("测试" + i);
            serviceInformationDto.setEngineerPhone("123456789632");
            serviceInformationDto.setFinishTime(new Date());
            serviceInformationDto.setProcessMode("asda");
            serviceInformationDto.setServerId("1" + i);
            serviceInformationDto.setServerName("测试" + i);
            serviceInformationDto.setServerPhone("12365246985");
            serviceInformationDto.setServiceProvider("asdsssss");
            list.add(serviceInformationDto);
        }
        responServiceInformationDto.setServiceInformationDtoList(list);
        return responServiceInformationDto;
    }

}
