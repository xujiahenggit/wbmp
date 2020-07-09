package com.bank.esb.webservice.impl;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.bank.esb.dto.*;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bank.core.entity.BizException;
import com.bank.esb.util.ESBUtil;
import com.bank.esb.vo.EngineerVo;
import com.bank.esb.vo.EquipmentDetailVo;
import com.bank.esb.vo.InspectionSheetVo;
import com.bank.esb.vo.InspectionSheetsVo;
import com.bank.esb.vo.InstitutionsVo;
import com.bank.esb.vo.OrderDealWithVo;
import com.bank.esb.vo.OrderNumVo;
import com.bank.esb.vo.OrderSubmissionVo;
import com.bank.esb.vo.RepairOrderBVo;
import com.bank.esb.vo.RepairOrderDispatchVo;
import com.bank.esb.vo.ServiceInformationVo;
import com.bank.esb.vo.StateChangesVo;
import com.bank.esb.vo.TransferInformationVo;
import com.bank.esb.webservice.AutoMaticeDeviceService;
import com.bank.esb.webservice.entity.ESBRequestHeader;
import com.bank.esb.webservice.entity.ESBResponseHeader;

import cn.hutool.core.lang.UUID;

@Service
@WebService(name = "automaticedeviceservice", targetNamespace = "http://webservice.wbmp.com")
public class AutoMaticeDeviceServiceImpl implements AutoMaticeDeviceService {

    private ResponseDto getOrders(OrderNumVo orderNumVo) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus("0");
        responseDto.setPageIndex(orderNumVo.getPageIndex());
        responseDto.setPageSize(orderNumVo.getPageSize());
        List<OrderDto> orderDtoList = new ArrayList<>();
        OrderDto orderDto1 = new OrderDto();
        orderDto1.setDeviceType("deviceType");
        orderDtoList.add(orderDto1);
        OrderDto orderDto2 = new OrderDto();
        orderDto2.setDeviceName("deviceName");
        orderDtoList.add(orderDto2);
        responseDto.setList(orderDtoList);
        return responseDto;
    }

    private OrderDealWithDto orderDealWith(OrderDealWithVo orderDealWithVo) {
        OrderDealWithDto orderDealWithDto = new OrderDealWithDto();
        orderDealWithDto.setRepcode("0");
        return orderDealWithDto;
    }

    private ResponseInstitutionsDto getInstitutions(InstitutionsVo institutionsVo) {
        ResponseInstitutionsDto responseInstitutionsDto = new ResponseInstitutionsDto();
        responseInstitutionsDto.setRepcode("0");
        List<InstitutionsDto> list = new ArrayList<>();
        for(int i=0;i<2;i++){
            InstitutionsDto institutionsDto =new InstitutionsDto();
            institutionsDto.setOrgId("10010"+i);
            institutionsDto.setOrgName("测试"+i);
            list.add(institutionsDto);
        }
        responseInstitutionsDto.setInstitutionsDtoList(list);
        return responseInstitutionsDto;
    }

    private ResponseInspectionSheetDto getInspectionSheet(InspectionSheetVo inspectionSheetVo) {
        ResponseInspectionSheetDto responseInspectionSheetDto = new ResponseInspectionSheetDto();
        responseInspectionSheetDto.setRepcode("0");
        List<InspectionSheetDto> list =new ArrayList<>();
        for(int i=0;i<2;i++){
            InspectionSheetDto institutionsDto =new InspectionSheetDto();
            institutionsDto.setOrgId("10010"+i);
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
        return inspectionSheetsDto;
    }

    private ResponseEngineerDto getEngineer(EngineerVo engineerVo) {
        ResponseEngineerDto responseEngineerDto = new ResponseEngineerDto();
        responseEngineerDto.setRepcode("0");
        List<EngineerDto> engineerDtoList =new ArrayList<>();
        for(int i=0;i<2;i++){
            EngineerDto engineerDto =new EngineerDto();
            engineerDto.setEngineerId("1"+i);
            engineerDto.setEngineerName("测试"+i);
            engineerDto.setPhone("13263953265");
            engineerDtoList.add(engineerDto);
        }
        responseEngineerDto.setEngineerDtoList(engineerDtoList);
        return responseEngineerDto;
    }

    private RepairOrderDispatchDto getRepairOrderDispatch(RepairOrderDispatchVo repairOrderDispatchVo) {
        RepairOrderDispatchDto repairOrderDispatchDto = new RepairOrderDispatchDto();
        return repairOrderDispatchDto;
    }

    private StateChangesDto stateChanges(StateChangesVo stateChangesVo) {
        StateChangesDto stateChangesDto = new StateChangesDto();
        stateChangesDto.setRepcode("0");
        return stateChangesDto;
    }

    private OrderSubmissionDto orderSubmission(OrderSubmissionVo orderSubmissionVo) {
        OrderSubmissionDto orderSubmissionDto = new OrderSubmissionDto();
        orderSubmissionDto.setRepcode("0");
        return orderSubmissionDto;
    }

    private RepairOrderBDto repairOrder(RepairOrderBVo repairOrderBVo) {
        RepairOrderBDto repairOrderBDto = new RepairOrderBDto();
        repairOrderBDto.setRepcode("0");
        return repairOrderBDto;
    }

    private ResponseEquipmentDetailDto getEquipmentDetail(EquipmentDetailVo equipmentDetailVo) {
        ResponseEquipmentDetailDto responseEquipmentDetailDto = new ResponseEquipmentDetailDto();
        responseEquipmentDetailDto.setRepcode("0");
        List<EquipmentDetailDto> equipmentDetailDtoList = new ArrayList<>();
        for(int i=0;i<2;i++){
            EquipmentDetailDto engineerDto =new EquipmentDetailDto();
            engineerDto.setAddress("湖南省"+i);
            engineerDto.setDeviceId("1"+i);
            engineerDto.setDeviceModel("asda");
            engineerDto.setDeviceVendor("asda");
            engineerDto.setOrgId("10010"+i);
            engineerDto.setInstallDate(new Date());
            engineerDto.setOrgName("测试"+i);
            List<ServiceSupervisorDto> serviceSupervisorDto =new ArrayList<>();
            ServiceSupervisorDto supervisorDto =new ServiceSupervisorDto();
            supervisorDto.setServerId("1"+i);
            supervisorDto.setServerName("擦拭"+i);
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
        List<ServiceInformationDto> list =new ArrayList<>();
        for(int i=0;i<2;i++){
            ServiceInformationDto serviceInformationDto =new ServiceInformationDto();
            serviceInformationDto.setEngineerId("1"+i);
            serviceInformationDto.setEngineerName("测试"+i);
            serviceInformationDto.setEngineerPhone("123456789632");
            serviceInformationDto.setFinishTime(new Date());
            serviceInformationDto.setProcessMode("asda");
            serviceInformationDto.setServerId("1"+i);
            serviceInformationDto.setServerName("测试"+i);
            serviceInformationDto.setServerPhone("12365246985");
            serviceInformationDto.setServiceProvider("asdsssss");
            list.add(serviceInformationDto);
        }
        responServiceInformationDto.setServiceInformationDtoList(list);
        return responServiceInformationDto;
    }

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
        }
        catch (DocumentException e) {
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
            case "WBMP10001":
                OrderNumVo orderNumVO = JSON.parseObject(JSON.toJSONString(body), OrderNumVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(getOrders(orderNumVO)), Map.class);
                break;
            case "WBMP10002":
                OrderDealWithVo orderDealWithVo = JSON.parseObject(JSON.toJSONString(body), OrderDealWithVo.class);
                returnVO = JSON.parseObject(JSON.toJSONString(orderDealWith(orderDealWithVo)), Map.class);
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

}
