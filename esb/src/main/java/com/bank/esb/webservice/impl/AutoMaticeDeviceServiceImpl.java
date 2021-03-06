package com.bank.esb.webservice.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.bank.core.entity.BizException;
import com.bank.core.utils.DateUtils;
import com.bank.core.utils.PageUtils;
import com.bank.esb.dao.DatWorkOrderDao;
import com.bank.esb.dos.*;
import com.bank.esb.dto.*;
import com.bank.esb.service.EsbLogService;
import com.bank.esb.service.EsbService;
import com.bank.esb.service.WorkOrderAttachmentService;
import com.bank.esb.service.WorkOrderService;
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
import java.util.*;

@Slf4j
@Service
@WebService(name = "automaticedeviceservice", targetNamespace = "http://webservice.wbmp.com")
public class AutoMaticeDeviceServiceImpl implements AutoMaticeDeviceService {


    @Resource
    EsbLogService esbLogService;

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
        String reference = header.get("ExternalReference").toString();
        log.info("请求流水号码：" + reference);
        //记录流水
        esbLogService.save(new EsbLogDO(null, reference, requestXml));
        Map<String, Object> request = (Map<String, Object>) requestMap.get("Body");
        Object requestArags = request.get("Request");
        Map<String, Object> body = null;

        try {
            body = (Map<String, Object>) requestArags;
        } catch (Exception e) {
            log.info("报文参数为空");
        }
        if (header == null) {
            throw new BizException("获取ESB请求报文头失败");
        }
        if (body == null && !header.get("ServiceCode").equals("WBMP10012")) {
            throw new BizException("获取ESB请求报文体失败");
        }
        ESBRequestHeader requestHeader = JSON.parseObject(JSON.toJSONString(header), ESBRequestHeader.class);

        Map<String, Object> returnVO = new HashMap<String, Object>();

        try {
            switch (requestHeader.getServiceCode()) {
                case "WBMP10001"://工单查询
                    OrderNumVo orderNumVO = JSON.parseObject(JSON.toJSONString(body), OrderNumVo.class);
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10001(orderNumVO)), Map.class);
                    break;
                case "WBMP10002"://工单处理
                    OrderDealWithVo orderDealWithVo = JSON.parseObject(JSON.toJSONString(body), OrderDealWithVo.class);
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10002(orderDealWithVo)), Map.class);
                    break;
                case "WBMP10003"://机构列表接口
                    InstitutionsVo institutionsVo = JSON.parseObject(JSON.toJSONString(body), InstitutionsVo.class);
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10003(institutionsVo)), Map.class);
                    break;
                case "WBMP10004"://巡检单查询接口
                    InspectionSheetVo inspectionSheetVo = JSON.parseObject(JSON.toJSONString(body), InspectionSheetVo.class);
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10004(inspectionSheetVo)), Map.class);
                    break;
                case "WBMP10005"://巡检单创建接口
                    InspectionSheetsVo inspectionSheet = JSON.parseObject(JSON.toJSONString(body), InspectionSheetsVo.class);
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10005(inspectionSheet)), Map.class);
                    break;
                case "WBMP10007"://工程师列表查询接口
                    EngineerVo engineerVo = JSON.parseObject(JSON.toJSONString(body), EngineerVo.class);
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10007(engineerVo)), Map.class);
                    break;
                case "WBMP10008"://工单分派查询
                    EngineerDto engineerDto = JSON.parseObject(JSON.toJSONString(body), EngineerDto.class);
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10008(engineerDto)), Map.class);
                    break;
                case "WBMP10009"://状态变更（工程师到达现场）接口
                    StateChangesVo changeStatus = JSON.parseObject(JSON.toJSONString(body), StateChangesVo.class);
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10009(changeStatus)), Map.class);
                    break;
                case "WBMP10010"://工单提交接口
                    OrderSubmissionVo orderSubmissionVo = JSON.parseObject(JSON.toJSONString(body), OrderSubmissionVo.class);
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10010(orderSubmissionVo)), Map.class);
                    break;
                case "WBMP10011"://投诉工单回复接口
                    RepairOrderBVo repairOrderBVo = JSON.parseObject(JSON.toJSONString(body), RepairOrderBVo.class);
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10011(repairOrderBVo)), Map.class);
                    break;
                case "WBMP10012"://设备厂商列表查询接口
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10012()), Map.class);
                    break;
                case "WBMP10013"://设备详细信息查询接口
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10013(body.get("deviceId"))), Map.class);
                    break;
                case "WBMP10014":
                    TransferInformationVo transferInformationVo = JSON.parseObject(JSON.toJSONString(body), TransferInformationVo.class);
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10014(transferInformationVo)), Map.class);
                    break;
                case "WBMP10015"://服务信息查询接口
                    TransferInformationVo orderId = JSON.parseObject(JSON.toJSONString(body), TransferInformationVo.class);
                    returnVO = JSON.parseObject(JSON.toJSONString(WBMP10015(orderId)), Map.class);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            String errid = UUID.randomUUID().toString().replaceAll("-", "");
            returnVO.put("repcode", "-1");
            returnVO.put("errid", errid);
            returnVO.put("errmsg", "逻辑处理报错，错误id:" + errid + ",请联系接口提供者");
            log.error("errid:{},报错详情：{}", errid, e.getMessage());
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
        if (returnVO.containsKey("list")) {
            returnVO.put("List", returnVO.get("list"));
            returnVO.remove("list");
        }
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
            saveAttachment(orderNo, orderSubmissionVo.getList());
            map.put("repcode", "0");
        } catch (Exception e) {
            log.error(e.getMessage());
            map.put("repcode", "-1");
        }
        return map;
    }

    private void saveAttachment(String orderNo, List<Attachment> pictureUrl) {
        if (pictureUrl != null && pictureUrl.size() > 0) {
            List list = new ArrayList<WorkOrderAttachmentDO>();
            for (Attachment attachment : pictureUrl) {
                list.add(WorkOrderAttachmentDO.builder()
                        .repairId(orderNo)
                        .path(attachment.getPictureUrl())
                        .name(attachment.getPictureUploadDate())
                        .build());
            }
            workOrderAttachmentService.saveBatch(list);
        }
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
            dto.setProvider(orderDO.getVendorName());
            dto.setEngineerId(engineerId);
            dto.setEngineerName(orderDO.getEngineerName());
            dto.setEngineerPhone(engineerId);
            //主管信息
            String serverId = orderDO.getDirector();
            dto.setServerId(serverId);
            dto.setServerName(orderDO.getDirectorName());
            dto.setServerPhone(serverId);

            String workOrderType = orderDO.getWorkOrderType();
            if (workOrderType.equals("03")) {
                dto.setProcessMode(orderDO.getEscortsHandling());
            } else {
                dto.setProcessMode(orderDO.getDealType());
            }
            dto.setFinishTime(DateUtil.formatLocalDateTime(orderDO.getWorkOrderCompleteTime()));
            responseDto.setRepcode("0");
        } else {
            responseDto.setRepcode("-1");
        }

        ArrayList<ServiceInformationDto> serviceInformationDtos = new ArrayList<>();
        serviceInformationDtos.add(dto);
        responseDto.setList(serviceInformationDtos);
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

    private ResponseDto WBMP10001(OrderNumVo orderNumVo) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setRepcode("0");
        Integer pageIndex = orderNumVo.getPageIndex();
        Integer pageSize = orderNumVo.getPageSize();
        pageIndex = pageIndex == 0 ? 1 : pageIndex;
        pageSize = pageSize == 0 ? 10 : pageSize;
        Integer total = 0;

        responseDto.setPageIndex(pageIndex);
        responseDto.setPageSize(pageSize);
        List<OrderDto> orderDtoList = datWorkOrderDao.queryOrders(orderNumVo);
        String orderType = orderNumVo.getOrderType();
        List<OrderDto> esblist = new ArrayList<>();
        if (orderType != null && orderNumVo.getUserId() != null
                && orderNumVo.getRelated() != null
                && orderNumVo.getOrderType().equals("01")) {
            esblist = esbService.getEsbErrOrder(orderNumVo);
        }
        orderDtoList.addAll(esblist);
        total = orderDtoList.size();
        List list = PageUtils.startPage(orderDtoList, pageIndex, pageSize);
        responseDto.setTotal(total);
        responseDto.setList(list);
        return responseDto;
    }

    @Resource
    WorkOrderAttachmentService workOrderAttachmentService;

    @Resource
    ManageWorkWaterService workWaterService;

    @Resource
    WorkOrderService workOrderService;

    private OrderDealWithDto WBMP10002(OrderDealWithVo orderDealWithVo) {
        OrderDealWithDto orderDealWithDto = new OrderDealWithDto();
        orderDealWithDto.setRepcode("0");
        String orderNo = orderDealWithVo.getOrderNo();
        WorkOrderDO orderDO = workOrderService.getOne(new LambdaQueryWrapper<WorkOrderDO>().eq(WorkOrderDO::getWorkOrderCode, orderNo));
        if (orderDO != null) {
            //更新工单表
            String engineerId = orderDealWithVo.getEngineerId();
            String processMode = orderDealWithVo.getProcessMode();
            String serviceDescribe = orderDealWithVo.getServiceDescribe();
            //查询信息
            Map<String, String> engineerInfo = getEngineerInfo(engineerId);
            String name = "";
            String phone = "";
            String role = "";
            if (engineerInfo != null) {
                name = engineerInfo.get("NAME");
                phone = engineerInfo.get("TELEPHONE");
                role = engineerInfo.get("MANUEMPNATURE");
            } else {
                orderDealWithDto.setRepcode("-1");
                log.error("获取处理人信息为空，engineerId为必传字，段请检查");
                return orderDealWithDto;
            }

            if (role.equals("1")) {
                orderDO.setDirector(engineerId);
                orderDO.setDirectorName(name);
            } else {
                orderDO.setEngineer(engineerId);
                orderDO.setEngineerName(name);
            }
            orderDO.setDealType(processMode);
            String orderStatus = orderDealWithVo.getOrderStatus();
            orderDO.setWorkOrderStatus(orderStatus);
            if (!StrUtil.isBlankIfStr(serviceDescribe)) {
                orderDO.setDealNote(serviceDescribe);
            }
            workOrderService.saveOrUpdate(orderDO);


            //插入流水，待处理
            workWaterService.save(
                    WorkWaterDO.builder()
                            .wordOrderId(orderNo)
                            .dealWithTime(new Date())
                            .createTime(new Date())
                            .dealWithPeopleId(engineerId)
                            .dealWithPeopleRole(Integer.parseInt(role))
                            .dealWithNote(serviceDescribe)
                            .dealWithPeopleName(name)
                            .phone(phone)
                            .operationType(getoperationType(orderStatus))
                            .build()
            );

            //附件保存
            saveAttachment(orderNo, orderDealWithVo.getList());
        } else {
            orderDealWithDto.setRepcode("-1");
        }
        return orderDealWithDto;
    }

    private String getoperationType(String orderStatus) {
        switch (orderStatus) {
            case "1":
                return "0";
            case "2":
                return "3";
            case "3":
                return "4";
            case "4":
                return "5";
            case "5":
                return "6";
            case "6":
                return "0";//8
            case "7":
                return "11";
            case "8":
                return "7";//12
            case "9":
                return "2";//13、14、
            case "10":
                return "2";
            default:
                return "-1";
        }

    }

    private ResponseInstitutionsDto WBMP10003(InstitutionsVo institutionsVo) {
        ResponseInstitutionsDto responseInstitutionsDto = new ResponseInstitutionsDto();
        responseInstitutionsDto.setRepcode("0");
        String orgId = institutionsVo.getOrgId();
        List<InstitutionsDto> list = new ArrayList<>();
        if (!StrUtil.isBlankIfStr(orgId)) {
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
        } else {
            //默认查询支行信息
            List<DatSubbranchDO> subBranchs = esbService.getSubBranch();
            for (DatSubbranchDO subbranchDO : subBranchs) {
                list.add(new InstitutionsDto(subbranchDO.getStrsubbranchnum(), subbranchDO.getStrsubbranchname()));
            }

        }
        responseInstitutionsDto.setList(list);
        return responseInstitutionsDto;
    }

    private ResponseInspectionSheetDto WBMP10004(InspectionSheetVo inspectionSheetVo) {
        ResponseInspectionSheetDto responseInspectionSheetDto = new ResponseInspectionSheetDto();
        responseInspectionSheetDto.setRepcode("0");
        int pageIndex = inspectionSheetVo.getPageIndex();
        int pageSize = inspectionSheetVo.getPageSize();
        inspectionSheetVo.setPageIndex((pageIndex - 1) * pageSize);
        String orderStatus = inspectionSheetVo.getOrderStatus();
        List<Map<String, String>> data = new ArrayList<>();
        //巡检状态获取
        if (StrUtil.isBlankIfStr(orderStatus)) {
            responseInspectionSheetDto.setRepcode("-1");
            return responseInspectionSheetDto;
        } else if (orderStatus.equals("1")) {
            String statisticalTime = inspectionSheetVo.getStatisticalTime();//1-上季度；2-本季度；3-上个半年；4-本半年
            if (StrUtil.isBlankIfStr(statisticalTime)) {
                responseInspectionSheetDto.setRepcode("-1");
                return responseInspectionSheetDto;
            }
            Date date = new Date();
            DateTime startTime = DateUtil.date();
            DateTime endTime = DateUtil.date();
            DateTime june = DateUtil.parseDate(DateUtil.year(date) + "-06-30");
            switch (statisticalTime) {
                case "1":
                    DateTime lastQuarter = DateUtil.offsetMonth(date, -3);
                    startTime = DateUtil.beginOfQuarter(lastQuarter);
                    endTime = DateUtil.endOfQuarter(lastQuarter);
                    break;
                case "2":
                    startTime = DateUtil.beginOfQuarter(date);
                    endTime = DateUtil.endOfQuarter(date);
                    break;
                case "3":
                    startTime = DateUtil.beginOfYear(date);
                    endTime = june;
                    break;
                case "4":
                    int quarter = DateUtil.quarter(date);
                    if (quarter < 3) {
                        startTime = DateUtil.beginOfYear(date);
                        endTime = june;
                    } else {
                        startTime = june;
                        endTime = DateUtil.endOfYear(date);
                    }
                    break;
                default:
                    break;
            }
            inspectionSheetVo.setStartTime(startTime);
            inspectionSheetVo.setEndTime(endTime);
            List<WorkOrderDO> xjdDo = datWorkOrderDao.getXjd(inspectionSheetVo);
            for (WorkOrderDO workOrderDO : xjdDo) {
                Map<String, String> order = new HashMap<>();
                order.put("STRDEVSN", workOrderDO.getSerialNum());
                order.put("STRTERMNUM", workOrderDO.getTerminalCode());
                order.put("IDEVTYPE", String.valueOf(workOrderDO.getDeviceType()));
                order.put("STRORGNO", workOrderDO.getBuffetLineName());
                order.put("STRSSBNAME", workOrderDO.getInstallAddr());
                order.put("freeduedate", workOrderDO.getFreeduedate());
                order.put("orderNo", workOrderDO.getWorkOrderCode());
                order.put("startTime", DateUtil.formatLocalDateTime(workOrderDO.getEscortsStartTime()));
                order.put("endTime", DateUtil.formatLocalDateTime(workOrderDO.getEscortsCompleteTime()));
                order.put("engineerName", workOrderDO.getCreateName());
                order.put("engineerPhone", workOrderDO.getCreateUserPhone());
                order.put("accompanyName", workOrderDO.getEscortsPatrolName());
                order.put("accompanyPhone", workOrderDO.getEscortsPatrolPhone());
                data.add(order);
            }
        } else if (orderStatus.equals("2")) {
            data=esbService.getXjd(inspectionSheetVo);
            responseInspectionSheetDto.setTotal(esbService.getXjdTotal(inspectionSheetVo));
        }

        ArrayList<InspectionSheetDto> inspectionSheetDtos = new ArrayList<>();
        for (Map<String, String> map : data) {
            inspectionSheetDtos.add(InspectionSheetDto.builder()
                    .serialNum(map.get("STRDEVSN"))
                    .inprocessNo(map.get("STRTERMNUM"))
                    .inprocessStatus(orderStatus)
                    .deviceProperty(String.valueOf(map.get("IDEVTYPE")))
                    .orgId(map.get("STRORGNO"))
                    .orgAddress(map.get("STRSSBNAME"))
                    .warrantyTime(map.get("freeduedate"))
                    .orderNo(map.get("orderNo"))
                    .startTime(map.get("startTime"))
                    .endTime(map.get("endTime"))
                    .engineerName(map.get("engineerName"))
                    .engineerPhone(map.get("engineerPhone"))
                    .accompanyName(map.get("accompanyName"))
                    .accompanyPhone(map.get("accompanyPhone"))
                    .build()
            );
        }
        responseInspectionSheetDto.setList(inspectionSheetDtos);
        return responseInspectionSheetDto;
    }

    private InspectionSheetsDto WBMP10005(InspectionSheetsVo inspectionSheetsVo) {
        InspectionSheetsDto inspectionSheetsDto = new InspectionSheetsDto();
        inspectionSheetsDto.setRepcode("0");
        //巡检单创建
        String orderNo = inspectionSheetsVo.getOrderNo();
        String deviceNo = inspectionSheetsVo.getDeviceNo();
        String accompany = inspectionSheetsVo.getAccompany();
        if (StrUtil.isBlankIfStr(deviceNo) || StrUtil.isBlankIfStr(accompany)) {
            log.error("设备号,巡检陪同人员id号不能为空");
            inspectionSheetsDto.setRepcode("-1");
            return inspectionSheetsDto;
        }
        Map<String, Object> xjdInfo = esbService.getXjdInfo(deviceNo);
        if (xjdInfo == null) {
            log.error("设备号错误，没有查到设备信息");
            inspectionSheetsDto.setRepcode("-1");
            return inspectionSheetsDto;
        }
        Object freeduedate = xjdInfo.get("freeduedate");
        Object firstinstalldate = xjdInfo.get("firstinstalldate");
        Object strtermaddr = xjdInfo.get("STRTERMADDR");
        Object strdevsn = xjdInfo.get("STRDEVSN");
        boolean isSave = StrUtil.isBlankIfStr(orderNo);
        if (isSave) {
            orderNo = "03" + DateUtils.now();
        }
        //获取巡检陪同人员信息
        Map<String, String> userInfo = datWorkOrderDao.getuserInfo(accompany);
        if (userInfo == null) {
            log.error("巡检陪同人员id错误，没有任何信息");
            inspectionSheetsDto.setRepcode("-1");
            return inspectionSheetsDto;
        }

        String createUserId = inspectionSheetsVo.getCreateUserId();
        String createUserName = inspectionSheetsVo.getCreateUserName();
        String createUserPhone = inspectionSheetsVo.getCreateUserPhone();
        WorkOrderDO workOrderDO = WorkOrderDO.builder()
                .terminalCode(deviceNo)
                .workOrderType("03")
                .workOrderCode(orderNo)
                .escortsFlag("1")
                .deviceType(Integer.parseInt(xjdInfo.get("IDEVTYPE").toString()))
                .deviceClass(xjdInfo.get("IDEVCLASS").toString())
                .serialNum(strdevsn == null ? "" : strdevsn.toString())
                .vendor(xjdInfo.get("STRDEVMANU").toString())
                .vendorName(xjdInfo.get("STRVALUE").toString())
                .branch(xjdInfo.get("STRBRANCHNUM").toString())
                .branchName(xjdInfo.get("STRBRANCHNAME").toString())
                .subBranch(xjdInfo.get("STRSUBBRANCHNUM").toString())
                .subBranchName(xjdInfo.get("STRSUBBRANCHNAME").toString())
                .buffetLine(xjdInfo.get("STRSSBNUM").toString())
                .buffetLineName(xjdInfo.get("STRSSBNAME").toString())
                .freeduedate(freeduedate == null ? "" : freeduedate.toString())
                .installDate(firstinstalldate == null ? "" : firstinstalldate.toString())
                .installAddr(strtermaddr == null ? "" : strtermaddr.toString())
                .workOrderStatus("8")
                .escortsPatrol(accompany)//巡检陪同人员
                .escortsPatrolName(userInfo.get("name"))
                .escortsPatrolPhone(userInfo.get("phone"))
                .createUserPhone(createUserPhone)
                .escortsStartTime(DateUtil.parseLocalDateTime(inspectionSheetsVo.getStartTime()))
                .escortsCompleteTime(DateUtil.parseLocalDateTime(inspectionSheetsVo.getEndTime()))
                .escortsHandling(inspectionSheetsVo.getProcessMode())
                .workOrderDescribe(inspectionSheetsVo.getOrderDescribe())
                .contactName(inspectionSheetsVo.getSceneUserName())
                .contactPhone(inspectionSheetsVo.getSceneUserPhone())
                .build();

        Map<String, String> engineerInfo = esbService.getEngineerInfo(createUserId);
        int role = 0;
        if (engineerInfo.size() == 1) {
            role = Integer.parseInt(engineerInfo.get("MANUEMPNATURE"));
            if (role == 1) {
                workOrderDO.setDirector(createUserId);
                workOrderDO.setDirectorName(createUserName);
            } else {
                workOrderDO.setEngineer(createUserId);
                workOrderDO.setEngineerName(createUserName);
            }
        }


        workOrderService.saveOrUpdate(workOrderDO);

        //添加一条流水,创建巡检单
        workWaterService.save(
                WorkWaterDO.builder()
                        .wordOrderId(orderNo)
                        .dealWithTime(new Date())
                        .createTime(new Date())
                        .dealWithPeopleRole(role)
                        .dealWithPeopleId(createUserId)
                        .dealWithNote(isSave ? "巡检单创建" : "巡检单更新")
                        .dealWithPeopleName(createUserName)
                        .phone(createUserPhone)
                        .operationType(isSave ? "0" : "8")
                        .build()
        );
        return inspectionSheetsDto;
    }

    @Resource
    EsbService esbService;

    private ResponseEngineerDto WBMP10007(EngineerVo engineerVo) {
        ResponseEngineerDto responseEngineerDto = new ResponseEngineerDto();
        responseEngineerDto.setRepcode("0");
        int pageIndex = engineerVo.getPageIndex();
        int pageSize = engineerVo.getPageSize();
        engineerVo.setPageIndex((pageIndex - 1) * pageSize);
        List<EngineerDto> engineerDtoList = esbService.getEngineer(engineerVo);
        responseEngineerDto.setTotal(esbService.getEngineerTotal(engineerVo));
        responseEngineerDto.setList(engineerDtoList);
        return responseEngineerDto;
    }

    private ResponseEngineerDto WBMP10008(EngineerDto engineerDto) {
        ResponseEngineerDto responseEngineerDto = new ResponseEngineerDto();
        responseEngineerDto.setRepcode("1");
        String engineerId = engineerDto.getEngineerId();
        String orderNo = engineerDto.getOrderId();
        //工单分派
        WorkOrderDO orderDO = workOrderService.getOne(new LambdaQueryWrapper<WorkOrderDO>().eq(WorkOrderDO::getWorkOrderCode, orderNo));
        if (orderDO != null) {
            //查询信息
            Map<String, String> engineerInfo = getEngineerInfo(engineerId);
            String name = "";
            String phone = "";
            String role = "";
            if (engineerInfo != null) {
                name = engineerInfo.get("NAME");
                phone = engineerInfo.get("TELEPHONE");
                role = engineerInfo.get("MANUEMPNATURE");
            } else {
                responseEngineerDto.setRepcode("-1");
                log.error("获取处理人信息为空，engineerId为必传字段,请检查");
                return responseEngineerDto;
            }

            if (role.equals("1")) {
                orderDO.setDirector(engineerId);
                orderDO.setDirectorName(name);
            } else {
                orderDO.setEngineer(engineerId);
                orderDO.setEngineerName(name);
            }
            orderDO.setWorkOrderStatus("7");
            workOrderService.saveOrUpdate(orderDO);


            //插入流水，分派
            workWaterService.save(
                    WorkWaterDO.builder()
                            .wordOrderId(orderNo)
                            .dealWithTime(new Date())
                            .createTime(new Date())
                            .dealWithPeopleId(engineerId)
                            .dealWithPeopleRole(Integer.parseInt(role))
                            .dealWithNote("工单分派")
                            .dealWithPeopleName(name)
                            .phone(phone)
                            .operationType("11")
                            .build()
            );
        }
        return responseEngineerDto;
    }

    private StateChangesDto WBMP10009(StateChangesVo stateChangesVo) {
        StateChangesDto stateChangesDto = new StateChangesDto();
        stateChangesDto.setRepcode("0");
        String engineerId = stateChangesVo.getEngineerId();
        String orderId = stateChangesVo.getOrderNo();
        WorkOrderDO orderDO = workOrderService.getOne(new LambdaQueryWrapper<WorkOrderDO>().eq(WorkOrderDO::getWorkOrderCode, orderId));
        if (orderDO != null) {
            Map<String, String> engineerInfo = getEngineerInfo(engineerId);
            String name = "";
            String phone = "";
            if (engineerInfo != null) {
                name = engineerInfo.get("NAME");
                phone = engineerInfo.get("TELEPHONE");
            } else {
                stateChangesDto.setRepcode("-1");
                log.error("获取处理人信息为空，engineerId为必传字段,请检查");
                return stateChangesDto;
            }

            orderDO.setWorkOrderStatus("8");
            orderDO.setEngineer(engineerId);
            orderDO.setEngineerName(name);
            workOrderService.saveOrUpdate(orderDO);


            //更改状态
            workWaterService.save(
                    WorkWaterDO.builder()
                            .wordOrderId(orderId)
                            .dealWithTime(new Date())
                            .createTime(new Date())
                            .dealWithPeopleId(engineerId)
                            .dealWithPeopleName(name)
                            .dealWithPeopleRole(2)
                            .dealWithNote("工程师到达现场处理状态变更")
                            .phone(phone)
                            .operationType("12")
                            .build()
            );
        }
        return stateChangesDto;
    }

    private Map<String, String> getEngineerInfo(String engineerId) {
        return esbService.getEngineerInfo(engineerId);
    }

    private RepairOrderBDto WBMP10011(RepairOrderBVo repairOrderBVo) {
        RepairOrderBDto repairOrderBDto = new RepairOrderBDto();
        String orderNo = repairOrderBVo.getOrderNo();
        String engineerId = repairOrderBVo.getEngineerId();
        //获取工单
        WorkOrderDO orderDO = workOrderService.getOne(new LambdaQueryWrapper<WorkOrderDO>().eq(WorkOrderDO::getWorkOrderCode, orderNo));
        if (orderDO != null && !StrUtil.isBlankIfStr(engineerId)) {
            orderDO.setSuggestion(repairOrderBVo.getOrderDescribe());//记录厂商回复意见
            orderDO.setWorkOrderStatus("4");
            //查询信息
            Map<String, String> engineerInfo = getEngineerInfo(engineerId);
            String name = "";
            String phone = "";
            String role = "";
            if (engineerInfo != null) {
                name = engineerInfo.get("NAME");
                phone = engineerInfo.get("TELEPHONE");
                role = engineerInfo.get("MANUEMPNATURE");
            } else {
                repairOrderBDto.setRepcode("-1");
                log.error("获取处理人信息为空，engineerId为必传字段,请检查");
                return repairOrderBDto;
            }

            if (role.equals("1")) {
                orderDO.setDirector(engineerId);
                orderDO.setDirectorName(name);
            } else {
                orderDO.setEngineer(engineerId);
                orderDO.setEngineerName(name);
            }

            workOrderService.saveOrUpdate(orderDO);


            //插入流水，待处理
            workWaterService.save(
                    WorkWaterDO.builder()
                            .wordOrderId(orderNo)
                            .dealWithTime(new Date())
                            .createTime(new Date())
                            .dealWithPeopleId(engineerId)
                            .dealWithPeopleRole(Integer.parseInt(role))
                            .dealWithNote("厂商回复")
                            .dealWithPeopleName(name)
                            .phone(phone)
                            .operationType("5")
                            .build()
            );
            repairOrderBDto.setRepcode("0");
        } else {
            repairOrderBDto.setRepcode("-1");
        }
        return repairOrderBDto;
    }

    private ResonseTransferInformationDto WBMP10014(TransferInformationVo transferInformationVo) {
        String orderId = transferInformationVo.getOrderId();
        ResonseTransferInformationDto resonseTransferInformationDto = new ResonseTransferInformationDto();
        List<Map<String, String>> workWaterDOS = workWaterService.getwater(orderId);
        ArrayList<TransferInformationDto> list = new ArrayList<>();
        for (Map<String, String> waterDO : workWaterDOS) {
            TransferInformationDto dto = TransferInformationDto.builder()
                    .operatorUserId(waterDO.get("uid"))
                    .operatorUserName(waterDO.get("name"))
                    .operatorUserPhone(waterDO.get("phone"))
                    .operatorStatus(waterDO.get("operatorStatus"))
                    .operatorTime(waterDO.get("time"))
                    .installDate(null)
                    .build();
            list.add(dto);
        }
        resonseTransferInformationDto.setRepcode("0");
        resonseTransferInformationDto.setList(list);
        if (workWaterDOS.size() > 0) {
            resonseTransferInformationDto.setOrderStatus(workWaterDOS.get(0).get("status"));
        }
        return resonseTransferInformationDto;
    }

}
