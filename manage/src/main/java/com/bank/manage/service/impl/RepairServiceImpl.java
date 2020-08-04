package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.manage.dao.InspectionEquipmentDto;
import com.bank.manage.dao.LargerScreenDto;
import com.bank.manage.dao.RepairDao;
import com.bank.manage.dos.ManageWorkOrderDO;
import com.bank.manage.dto.*;
import com.bank.manage.service.RepairService;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RepairServiceImpl extends ServiceImpl<RepairDao, ManageWorkOrderDO> implements RepairService {
    @Resource
    private RepairDao repairDao;

    @Override
    public int saveRepair(WorkOrderDto workOrderDto) {
        //生成工单编号  工单类型 1-故障工单；2-投诉工单；3-巡检
        LocalDateTime now =LocalDateTime.now();
        workOrderDto.setWorkOrderCode("1"+now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        //工单状态默认  0 :待处理；1：待评价；2：办接；3：待分行确认；4：待总行确认；4：待厂商回复；6：总行知悉；7：分行知悉；8：退回；9：已关闭
        workOrderDto.setWorkOrderStatus("0");
        workOrderDto.setCreateTime(new Date());
        return  repairDao.saveWorkOrder(workOrderDto);
    }

    @Override
    public RepairVo getRepairById(String repairCode) {
        return repairDao.getRepairById(repairCode);
    }

    @Override
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
    public DevicesNumberVo getDevicesNumber(String orgId) {
        return repairDao.getDevicesNumber(orgId);
    }

    @Override
    public IPage<LargerScreenVo> getLargerScreen(LargerScreenDto largerScreenDto) {
        Page<LargerScreenVo> page = new Page<>(largerScreenDto.getPageIndex(), largerScreenDto.getPageSize());
        if (StringUtils.isNotBlank(largerScreenDto.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", largerScreenDto.getOrder())) {
                page.setDesc(largerScreenDto.getSort());
            }
            else {
                page.setAsc(largerScreenDto.getSort());
            }
        }

        return repairDao.getLargerScreen(page,largerScreenDto.getBranchCode(),largerScreenDto.getTerminalCode(),largerScreenDto.getSelfBankCode());
    }

    @Override
    public PrinterVo getPrinterByCode(String terminalCode) {
        return repairDao.getPrinterByCode(terminalCode);
    }

    @Override
    public IPage<WorkOrderVO> getWorkOrder(WorkOrdersDto workOrdersDto) {
        Page<LargerScreenVo> page = new Page<>(workOrdersDto.getPageIndex(), workOrdersDto.getPageSize());
        if (StringUtils.isNotBlank(workOrdersDto.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", workOrdersDto.getOrder())) {
                page.setDesc(workOrdersDto.getSort());
            }
            else {
                page.setAsc(workOrdersDto.getSort());
            }
        }
        //判断来源类型  1 我发起的，2 我审批的、3 我办结的、4 系统自建；5 所有
        if("5".equals(workOrdersDto.getSourceType())){
            //查询所有
            return repairDao.getWorkOrder(page,workOrdersDto);
        }else if("1".equals(workOrdersDto.getSourceType())){
            //我发起的
            return  repairDao.getWorkOrderByMe(page,workOrdersDto);
        }
        else if("4".equals(workOrdersDto.getSourceType())){
            //系统自建
             return  repairDao.getWorkOrderBySystem(page,workOrdersDto);
        }

        //其他类型
        return repairDao.getWorkOrderByOther(page,workOrdersDto);


    }

    @Override
    public int saveInspectionWorkOrder(InspectionWorkOrderDto inspectionWorkOrderDto) {
        //生成工单编号  工单类型 1-故障工单；2-投诉工单；3-巡检
        LocalDateTime now =LocalDateTime.now();
        inspectionWorkOrderDto.setWorkOrderCode("3"+now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        //工单状态默认  0 :待处理；1：待评价；2：办接；3：待分行确认；4：待总行确认；4：待厂商回复；6：总行知悉；7：分行知悉；8：退回；9：已关闭
        inspectionWorkOrderDto.setWorkOrderStatus("0");
        inspectionWorkOrderDto.setCreateTime(new Date());
        inspectionWorkOrderDto.setWorkOrderType("3");
        //将处理方式拼接在 json
        StringBuffer stringBuffer =new StringBuffer();
        for(int i=0;i<inspectionWorkOrderDto.getEscortsHandlingList().size();i++){
            stringBuffer.append(inspectionWorkOrderDto.getEscortsHandlingList().get(i)).append(",");
        }
        inspectionWorkOrderDto.setJson(stringBuffer.toString());
        return repairDao.saveInspectionWorkOrder(inspectionWorkOrderDto);
    }

    @Override
    public int saveComplaintsWorkOrder(ComplaintsWorkOrderDto complaintsWorkOrderDto) {
        //生成工单编号  工单类型 1-故障工单；2-投诉工单；3-巡检
        LocalDateTime now =LocalDateTime.now();
        complaintsWorkOrderDto.setWorkOrderCode("2"+now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        //工单状态默认  0 :待处理；1：待评价；2：办接；3：待分行确认；4：待总行确认；4：待厂商回复；6：总行知悉；7：分行知悉；8：退回；9：已关闭
        complaintsWorkOrderDto.setWorkOrderStatus("0");
        complaintsWorkOrderDto.setCreateTime(new Date());
        complaintsWorkOrderDto.setWorkOrderType("2");
        return repairDao.saveComplaintsWorkOrder(complaintsWorkOrderDto);
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
    public IPage<KioskVo> getKioskList(KioskDto kioskDto) {
        Page<LargerScreenVo> page = new Page<>(kioskDto.getPageIndex(), kioskDto.getPageSize());
        if (StringUtils.isNotBlank(kioskDto.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", kioskDto.getOrder())) {
                page.setDesc(kioskDto.getSort());
            }
            else {
                page.setAsc(kioskDto.getSort());
            }
        }
        return repairDao.kioskDto(page,kioskDto);
    }

    @Override
    public CompletedWordOrderVo getKioskById(String id) {
        return  repairDao.getKioskById(id);
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