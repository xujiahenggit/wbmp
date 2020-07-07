package com.bank.esb.webservice.impl;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bank.core.entity.BizException;
import com.bank.esb.dto.InspectionSheetsDto;
import com.bank.esb.dto.OrderDealWithDto;
import com.bank.esb.dto.OrderSubmissionDto;
import com.bank.esb.dto.RepairOrderBDto;
import com.bank.esb.dto.RepairOrderDispatchDto;
import com.bank.esb.dto.ResonseTransferInformationDto;
import com.bank.esb.dto.ResponServiceInformationDto;
import com.bank.esb.dto.ResponseDto;
import com.bank.esb.dto.ResponseEngineerDto;
import com.bank.esb.dto.ResponseEquipmentDetailDto;
import com.bank.esb.dto.ResponseInspectionSheetDto;
import com.bank.esb.dto.ResponseInstitutionsDto;
import com.bank.esb.dto.StateChangesDto;
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
        return responseDto;
    }

    private OrderDealWithDto orderDealWith(OrderDealWithVo orderDealWithVo) {
        OrderDealWithDto orderDealWithDto = new OrderDealWithDto();
        orderDealWithDto.setRepcode("0");
        return orderDealWithDto;
    }

    private ResponseInstitutionsDto getInstitutions(InstitutionsVo institutionsVo) {
        ResponseInstitutionsDto responseInstitutionsDto = new ResponseInstitutionsDto();
        return responseInstitutionsDto;
    }

    private ResponseInspectionSheetDto getInspectionSheet(InspectionSheetVo inspectionSheetVo) {
        ResponseInspectionSheetDto responseInspectionSheetDto = new ResponseInspectionSheetDto();
        return responseInspectionSheetDto;
    }

    private InspectionSheetsDto getInspectionSheets(InspectionSheetsVo inspectionSheetsVo) {
        InspectionSheetsDto inspectionSheetsDto = new InspectionSheetsDto();
        return inspectionSheetsDto;
    }

    private ResponseEngineerDto getEngineer(EngineerVo engineerVo) {
        ResponseEngineerDto responseEngineerDto = new ResponseEngineerDto();
        return responseEngineerDto;
    }

    private RepairOrderDispatchDto getRepairOrderDispatch(RepairOrderDispatchVo repairOrderDispatchVo) {
        RepairOrderDispatchDto repairOrderDispatchDto = new RepairOrderDispatchDto();
        return repairOrderDispatchDto;
    }

    private StateChangesDto stateChanges(StateChangesVo stateChangesVo) {
        StateChangesDto stateChangesDto = new StateChangesDto();
        return stateChangesDto;
    }

    private OrderSubmissionDto orderSubmission(OrderSubmissionVo orderSubmissionVo) {
        OrderSubmissionDto orderSubmissionDto = new OrderSubmissionDto();
        return orderSubmissionDto;
    }

    private RepairOrderBDto repairOrder(RepairOrderBVo repairOrderBVo) {
        RepairOrderBDto repairOrderBDto = new RepairOrderBDto();
        return repairOrderBDto;
    }

    private ResponseEquipmentDetailDto getEquipmentDetail(EquipmentDetailVo equipmentDetailVo) {
        ResponseEquipmentDetailDto responseEquipmentDetailDto = new ResponseEquipmentDetailDto();
        return responseEquipmentDetailDto;
    }

    private ResonseTransferInformationDto getTransferInformation(TransferInformationVo transferInformationVo) {
        ResonseTransferInformationDto resonseTransferInformationDto = new ResonseTransferInformationDto();
        return resonseTransferInformationDto;
    }

    private ResponServiceInformationDto getServiceInformation(ServiceInformationVo serviceInformationVo) {
        ResponServiceInformationDto responServiceInformationDto = new ResponServiceInformationDto();
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
