package com.bank.manage.service.impl;

import com.bank.core.config.DataSource;
import com.bank.core.config.DynamicDataSourceSwitcher;
import com.bank.core.entity.BizException;
import com.bank.manage.dao.InspectionEquipmentDto;
import com.bank.manage.dao.LargerScreenDto;
import com.bank.manage.dao.RepairDao;
import com.bank.manage.dos.ManageWorkOrderDO;
import com.bank.manage.dto.*;
import com.bank.manage.service.RepairService;
import com.bank.manage.vo.*;
import com.bank.user.dao.OrgDetailinfoDao;
import com.bank.user.dto.OrgDetailDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class RepairServiceImpl extends ServiceImpl<RepairDao, ManageWorkOrderDO> implements RepairService {
    @Resource
    private RepairDao repairDao;
    @Resource
    private OrgDetailinfoDao orgDetailinfoDao;

    @Override
    @Transactional
    public int saveRepair(WorkOrderDto workOrderDto) {
        //生成工单编号  工单类型 01-故障工单；02-投诉工单；03-巡检
        LocalDateTime now =LocalDateTime.now();
        workOrderDto.setWorkOrderCode("01"+now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        //工单状态默认  1：待分行确认；2：待总行确认；3：待厂商回复；4：总行知悉；5：分行知悉； 6 :待服务主管处理；7：待工程师处理 8：待评价；9：办接；
        workOrderDto.setWorkOrderStatus("6");
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        workOrderDto.setCreateTime(simpleDateFormat.format(new Date()));
        workOrderDto.setWorkOrderType("01");
        //获取现场联系人和号码
        OrgDetailDto orgDetailDto= orgDetailinfoDao.getOrgInfoByOrgId(workOrderDto.getOrgId());

        if(orgDetailDto != null){
            workOrderDto.setContactName(orgDetailDto.getOrgContactMan());
            workOrderDto.setContactPhone(orgDetailDto.getOrgPhone());
        }
        int i=repairDao.saveWorkOrder(workOrderDto);
        //添加到流水表
        String name =repairDao.getUserNameById(workOrderDto.getCreateId());
        WordOrderWaterDto wordOrderWaterDto =new WordOrderWaterDto();
        wordOrderWaterDto.setCreateTime(new Date());
        wordOrderWaterDto.setDealWithPeopleId(workOrderDto.getCreateId());
        wordOrderWaterDto.setDealWithPeopleRole("6");
        wordOrderWaterDto.setWordOrderCode(workOrderDto.getWorkOrderCode());
        wordOrderWaterDto.setOperationType("0");
        if(name!=null || !"".equals(name)){
            wordOrderWaterDto.setDealWithPeopleName(name);
        }
        repairDao.saveWorkOrderWater(wordOrderWaterDto);

        return i;
    }

    @Override
    public RepairVo getRepairById(String repairCode) {
        RepairVo repairVo=  repairDao.getRepairById(repairCode);
        //服务信息
        ServiceInfoVo serviceInfoVo = repairDao.getServiceInfoList(repairCode);
        //服务工程师
        List<EngineerListVo> engineerListVoList =repairDao.getEngineerList(repairCode);
        if(CollectionUtils.isNotEmpty(engineerListVoList)){
            serviceInfoVo.setEngineerListVoList(engineerListVoList);

        }
        //服务主管
        List<DirectorVo> directorVoList=repairDao.getDirectorList(repairCode);
        if(CollectionUtils.isNotEmpty(directorVoList)){
            serviceInfoVo.setDirectorVoList(directorVoList);

        }

        if(serviceInfoVo != null){
            repairVo.setServiceInfoVo(serviceInfoVo);
        }


        return repairVo;
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<EquipmentVo> getEquipmentByCode(String terminalCode) {
        return repairDao.getEquipmentByCode(terminalCode);
    }

    @Override
    public List<InspectionEquipmentVo> getInspectionEquipmentByCode(InspectionEquipmentDto inspectionEquipmentDto) {
        getTime(inspectionEquipmentDto);
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if("1".equals(inspectionEquipmentDto.getLogo())){
            //已巡检
            List<InspectionEquipmentVo> list = repairDao.getInspectionEquipmentByCode(inspectionEquipmentDto);
            for(int i=0;i<list.size();i++){
                list.get(i).setLogo("1");
            }
            return list;
        }
            //未巡检
            List<InspectionEquipmentVo> list = repairDao.getInspectionEquipment(inspectionEquipmentDto);
            for(int i=0;i<list.size();i++){
                list.get(i).setLogo("1");

            }
        return list;


    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public DevicesNumberVo getDevicesNumber(String orgId) {
        return repairDao.getDevicesNumber(orgId);
    }

    @Override
    public IPage<LargerScreenVo> getLargerScreen(LargerScreenDto largerScreenDto) {
        if(largerScreenDto.getTerminalCode() ==null || "".equals(largerScreenDto.getTerminalCode()) ){
            largerScreenDto.setTerminalCode(null);
        }
        Page<LargerScreenVo> page = new Page<>(largerScreenDto.getPageIndex(), largerScreenDto.getPageSize());
        return repairDao.getLargerScreen(page,largerScreenDto.getBranchCode(),largerScreenDto.getTerminalCode());
    }

    @Override
    public PrinterVo getPrinterByCode(String terminalCode) {
        return repairDao.getPrinterByCode(terminalCode);
    }

    @Override
    public Map<String, Object>  getWorkOrder(WorkOrdersDto workOrdersDto) {
        Page<WorkOrderVO> page = new Page<>(workOrdersDto.getPageIndex(), workOrdersDto.getPageSize());
        //判断来源类型  1 我发起的，2 我审批的、3 我办结的、4 系统自建；5 所有
        Map<String, Object> resultMap = new HashMap<>();
        if("5".equals(workOrdersDto.getSourceType())){
            //查询所有
            IPage<WorkOrderVO>  workOrderList=  repairDao.getWorkOrder(page,workOrdersDto);
            resultMap.put("records", workOrderList.getRecords());
            resultMap.put("current", workOrderList.getCurrent());
            resultMap.put("size", workOrderList.getSize());
            resultMap.put("total", workOrderList.getTotal());
            return resultMap;


        }else if("1".equals(workOrdersDto.getSourceType())){
            //我发起的
            IPage<WorkOrderVO>  workOrderList= repairDao.getWorkOrderByMe(page,workOrdersDto);
            resultMap.put("records", workOrderList.getRecords());
            resultMap.put("current", workOrderList.getCurrent());
            resultMap.put("size", workOrderList.getSize());
            resultMap.put("total", workOrderList.getTotal());
            return  resultMap;
        }else if("2".equals(workOrdersDto.getSourceType())){
            //我审批的
            IPage<WorkOrderVO>  workOrderList= repairDao.getWorkOrderByMeApp(page,workOrdersDto);
            resultMap.put("records", workOrderList.getRecords());
            resultMap.put("current", workOrderList.getCurrent());
            resultMap.put("size", workOrderList.getSize());
            resultMap.put("total", workOrderList.getTotal());
            return  resultMap;

        }else{
            //我办结的
            IPage<WorkOrderVO>  workOrderList= repairDao.getWorkOrderByOther(page,workOrdersDto);
            resultMap.put("records", workOrderList.getRecords());
            resultMap.put("current", workOrderList.getCurrent());
            resultMap.put("size", workOrderList.getSize());
            resultMap.put("total", workOrderList.getTotal());
            return  resultMap;
        }

    }

//    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
//    private IPage<WorkOrderVO> getWorkOrderBySystem(Page<WorkOrderVO> page, WorkOrdersDto workOrdersDto) {
//       return   repairDao.getWorkOrderBySystem(page,workOrdersDto);
//    }



    @Override
    public int saveInspectionWorkOrder(InspectionWorkOrderDto inspectionWorkOrderDto) {
        //生成工单编号  工单类型 1-故障工单；2-投诉工单；3-巡检
        LocalDateTime now =LocalDateTime.now();
        inspectionWorkOrderDto.setWorkOrderCode("03"+now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        //工单状态默认  1：待分行确认；2：待总行确认；3：待厂商回复；4：总行知悉；5：分行知悉； 6 :待服务主管处理；7：待工程师处理 8：待评价；9：办接；
        inspectionWorkOrderDto.setWorkOrderStatus("6");
        inspectionWorkOrderDto.setCreateTime(new Date());
        inspectionWorkOrderDto.setWorkOrderType("03");
        //将处理方式拼接在 json
        StringBuffer stringBuffer =new StringBuffer();
        for(int i=0;i<inspectionWorkOrderDto.getEscortsHandlingList().size();i++){
            stringBuffer.append(inspectionWorkOrderDto.getEscortsHandlingList().get(i)).append(",");
        }
        inspectionWorkOrderDto.setJson(stringBuffer.toString());
        return repairDao.saveInspectionWorkOrder(inspectionWorkOrderDto);
    }

    @Override
    @Transactional
    public int saveComplaintsWorkOrder(ComplaintsWorkOrderDto complaintsWorkOrderDto) {
        //生成工单编号  工单类型 1-故障工单；2-投诉工单；3-巡检
        LocalDateTime now =LocalDateTime.now();
        complaintsWorkOrderDto.setWorkOrderCode("02"+now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        //工单状态默认 1：待分行确认；2：待总行确认；3：待厂商回复；4：总行知悉；5：分行知悉； 6 :待服务主管处理；7：待工程师处理 8：待评价；9：办接；
        complaintsWorkOrderDto.setWorkOrderStatus("1");
        complaintsWorkOrderDto.setCreateTime(new Date());
        complaintsWorkOrderDto.setWorkOrderType("02");
        int i=repairDao.saveComplaintsWorkOrder(complaintsWorkOrderDto);
        //添加到流水表
        String name =repairDao.getUserNameById(complaintsWorkOrderDto.getCreateId());
        WordOrderWaterDto wordOrderWaterDto =new WordOrderWaterDto();
        wordOrderWaterDto.setCreateTime(new Date());
        wordOrderWaterDto.setDealWithPeopleId(complaintsWorkOrderDto.getCreateId());
        wordOrderWaterDto.setDealWithPeopleRole("6");
        wordOrderWaterDto.setWordOrderCode(complaintsWorkOrderDto.getWorkOrderCode());
        wordOrderWaterDto.setOperationType("0");
        if(name!=null || !"".equals(name)){
            wordOrderWaterDto.setDealWithPeopleName(name);
        }
        repairDao.saveWorkOrderWater(wordOrderWaterDto);
        return i;
    }

    @Override
    public BreakDownWorkOrderVo getBreakWorkOrderByCode(String repairCode) {

        return repairDao.getBreakWorkOrderByCode(repairCode);
    }

    @Override
    public List<CompletedWordOrderVo> getCompletedWordOrderByCode(CompletedWordOrderDto completedWordOrderDto) {
        return repairDao.getCompletedWordOrderByCode(completedWordOrderDto);
    }

    @Override
    public ServiceInformationsVo getServiceInformationByCode(String repairCode) {
        return repairDao.getServiceInformationByCode(repairCode);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public IPage<KioskVo> getKioskList(KioskDto kioskDto) {
        Page<KioskVo> page = new Page<>(kioskDto.getPageIndex(), kioskDto.getPageSize());
        return repairDao.kioskDto(page,kioskDto);
    }

    @Override
    public CompletedWordOrderVo getKioskById(String id) {
        return  repairDao.getKioskById(id);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<VendorVo> getVendorList() {

        return repairDao.getVendorList();
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public IPage<WorkOrderVO> getWorkOrderBySystem(WorkOrdersDto workOrdersDto) {
        Page<WorkOrderVO> page = new Page<>(workOrdersDto.getPageIndex(), workOrdersDto.getPageSize());
        IPage<WorkOrderVO> workOrderList= repairDao.getWorkOrderBySystem(page,workOrdersDto);

        return  workOrderList;
    }

    @Override
    public String getBuffetLine(String orgId) {

        return repairDao.getBuffetLine(orgId);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public  List<BuffetLineVo> getBuffetLineList(BuffetLineDto buffetLineDto) {
        return repairDao.getBuffetLineList(buffetLineDto);
    }

    @Override
    public List<RepairHistoryListVo> getRepairHistoryList(String repairCode) {
        List<RepairHistoryListVo> list  = repairDao.getRepairHistoryList(repairCode);
        if(list.size() >0 ){
            for (RepairHistoryListVo item:list){
                List<String> attachList = repairDao.getOrderAttachList(item.getWordOrderId());
                if(attachList.size()>0){
                    item.setThumbs(attachList);
                }
            }
        }
        return list ;
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<BuffetLineVo> getBranchVoList() {
        return repairDao.getBranchVoList();
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<BuffetLineVo> getSubbranchList(String code) {
        return repairDao.getSubbranchList(code);
    }

    @Override
    public RepairVo getComplaintsRepairById(String repairCode) {
        return repairDao.getComplaintsRepairById(repairCode);
    }

    @Override
    public InspectionRepairVo getInspectionRepairById(String repairCode) {
        InspectionRepairVo inspectionRepairVo= repairDao.getInspectionRepairById(repairCode);
        //服务信息

        ServiceInfoVo serviceInfoVo = repairDao.getServiceInfoList(repairCode);
        //服务工程师
        List<EngineerListVo> engineerListVoList =repairDao.getEngineerList(repairCode);
        if(CollectionUtils.isNotEmpty(engineerListVoList)){
            serviceInfoVo.setEngineerListVoList(engineerListVoList);

        }
        //服务主管
        List<DirectorVo> directorVoList=repairDao.getDirectorList(repairCode);
        if(CollectionUtils.isNotEmpty(directorVoList)){
            serviceInfoVo.setDirectorVoList(directorVoList);

        }

        if(serviceInfoVo != null){
            inspectionRepairVo.setServiceInfoVo(serviceInfoVo);
        }

        return inspectionRepairVo;
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public RepairVo getWOrkSystemByCode(String repairCode) {
        RepairVo repairVo = repairDao.getWOrkSystemByCode(repairCode);
        List<ContactVo> list =new ArrayList<>();
        String temp[];
        String phone[];
        if(repairVo.getContactName()!=null ||!"".equals(repairVo.getContactName())){
            //现场联系人存在
             temp =repairVo.getContactName().split("\\|");
             for(String name :temp){
                ContactVo contactVo =new ContactVo();
                contactVo.setContactName(name);
                list.add(contactVo);
            }
        }

        //现场联系电话
        if(repairVo.getContactPhone()!=null ||!"".equals(repairVo.getContactPhone())){
            phone =repairVo.getContactPhone().split("\\|");
         for(int i=0; i<list.size();i++){
             list.get(i).setContactPhone(phone[i]);
         }
        }
        if(CollectionUtils.isNotEmpty(list)){
            repairVo.setContactVoList(list);
        }
        //服务信息
        if(repairVo.getVendor()!=null ||!"".equals(repairVo.getVendor())){
            ServiceInfoVo serviceInfoVo =  repairDao.getServiceInfoListBySys(repairCode,repairVo.getVendor());
            //
            //服务工程师
            List<EngineerListVo> engineerListVoList =repairDao.getEngineerListBySyS(repairVo.getVendor());
            if(CollectionUtils.isNotEmpty(engineerListVoList)){
                serviceInfoVo.setEngineerListVoList(engineerListVoList);

            }
            //服务主管
            List<DirectorVo> directorVoList=repairDao.getDirectorListBySyS(repairVo.getVendor());
            if(CollectionUtils.isNotEmpty(directorVoList)){
                serviceInfoVo.setDirectorVoList(directorVoList);

            }
            repairVo.setServiceInfoVo(serviceInfoVo);

        }

        return repairVo;
    }

    @Override
    public String getUserByCode(String userId) {
        return repairDao.getUserByCode(userId);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<WorkOrderVO> getWorkOrderBySystemList(WorkOrdersDto workOrdersDto) {
        return repairDao.getWorkOrderBySystemList(workOrdersDto);
    }

    @Override
    public List<WorkOrderVO> getWorkOrderList(WorkOrdersDto workOrdersDto) {
        return repairDao.getWorkOrderList(workOrdersDto);
    }

    public void getTime(InspectionEquipmentDto inspectionEquipmentDto){
        //获取当前系统的月份
        Calendar calendar =Calendar.getInstance();
        int month =calendar.get(Calendar.MONTH)+1;
        int year =calendar.get(Calendar.YEAR);
        Date startDate;
        Date endDate;
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //1:本季度 2：上季度  3：本半年 4：上半年
        if("1".equals(inspectionEquipmentDto.getStatisticalTime())){

             if(month<=3){
                 try {
                     startDate =simpleDateFormat.parse(year+"-01-01"+" 00:00:00");
                     inspectionEquipmentDto.setStartTime(startDate);
                     endDate=simpleDateFormat.parse(year+"-03-30"+" 23:59:59");
                     inspectionEquipmentDto.setEndTime(endDate);
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }
             }else if(month<=6){
                 try {
                     startDate =simpleDateFormat.parse(year+"-04-01"+" 00:00:00");
                     inspectionEquipmentDto.setStartTime(startDate);
                     endDate=simpleDateFormat.parse(year+"-06-30"+" 23:59:59");
                     inspectionEquipmentDto.setEndTime(endDate);
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }

             }else if(month<=9){
                 try {
                     startDate =simpleDateFormat.parse(year+"-07-01"+" 00:00:00");
                     inspectionEquipmentDto.setStartTime(startDate);
                     endDate=simpleDateFormat.parse(year+"-09-30"+" 23:59:59");
                     inspectionEquipmentDto.setEndTime(endDate);
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }

             }else if(month<=12){
                 try {
                     startDate =simpleDateFormat.parse(year+"-10-01"+" 00:00:00");
                     inspectionEquipmentDto.setStartTime(startDate);
                     endDate=simpleDateFormat.parse(year+"-12-30"+" 23:59:59");
                     inspectionEquipmentDto.setEndTime(endDate);
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }

             }else{
                 throw new BizException("月份异常不在1-12之间");
             }
        }else if("2".equals(inspectionEquipmentDto.getStatisticalTime())){
            //上季度
            if(month<=3){
                try {
                    startDate =simpleDateFormat.parse(year+"-01-01"+" 00:00:00");
                    inspectionEquipmentDto.setStartTime(startDate);
                    endDate=simpleDateFormat.parse(year+"-03-30"+" 23:59:59");
                    inspectionEquipmentDto.setEndTime(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else if(month<=6){
                try {
                    startDate =simpleDateFormat.parse(year+"-01-01"+" 00:00:00");
                    inspectionEquipmentDto.setStartTime(startDate);
                    endDate=simpleDateFormat.parse(year+"-03-30"+" 23:59:59");
                    inspectionEquipmentDto.setEndTime(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else if(month<=9){
                try {
                    startDate =simpleDateFormat.parse(year+"-03-01"+" 00:00:00");
                    inspectionEquipmentDto.setStartTime(startDate);
                    endDate=simpleDateFormat.parse(year+"-06-30"+" 23:59:59");
                    inspectionEquipmentDto.setEndTime(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else if(month<=12){
                try {
                    startDate =simpleDateFormat.parse(year+"-06-01"+" 00:00:00");
                    inspectionEquipmentDto.setStartTime(startDate);
                    endDate=simpleDateFormat.parse(year+"-09-30"+" 23:59:59");
                    inspectionEquipmentDto.setEndTime(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else{
                throw new BizException("月份异常不在1-12之间");
            }

        }else if("3".equals(inspectionEquipmentDto.getStatisticalTime())){
            //本半年
            if(month<=6){
                try {
                    startDate =simpleDateFormat.parse(year+"-01-01"+" 00:00:00");
                    inspectionEquipmentDto.setStartTime(startDate);
                    endDate=simpleDateFormat.parse(year+"-06-30"+" 23:59:59");
                    inspectionEquipmentDto.setEndTime(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else{
                try {
                    startDate =simpleDateFormat.parse(year+"-07-01"+" 00:00:00");
                    inspectionEquipmentDto.setStartTime(startDate);
                    endDate=simpleDateFormat.parse(year+"-12-30"+" 23:59:59");
                    inspectionEquipmentDto.setEndTime(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }else{
            //上半年
            if(month<=6){
                try {
                    startDate =simpleDateFormat.parse(year+"-01-01"+" 00:00:00");
                    inspectionEquipmentDto.setStartTime(startDate);
                    endDate=simpleDateFormat.parse(year+"-06-30"+" 23:59:59");
                    inspectionEquipmentDto.setEndTime(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else{
                try {
                    startDate =simpleDateFormat.parse(year+"-01-01"+" 00:00:00");
                    inspectionEquipmentDto.setStartTime(startDate);
                    endDate=simpleDateFormat.parse(year+"-06-30"+" 23:59:59");
                    inspectionEquipmentDto.setEndTime(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
