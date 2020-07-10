package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.manage.dao.InspectionEquipmentDto;
import com.bank.manage.dao.LargerScreenDto;
import com.bank.manage.dao.RepairDao;
import com.bank.manage.dos.RepairDo;
import com.bank.manage.dto.WorkOrderDto;
import com.bank.manage.service.RepairService;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.BindException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {
    @Resource
    private RepairDao repairDao;

    @Override
    public int saveRepair(WorkOrderDto workOrderDto) {
        //生成工单编号  人工工单:01 系统自建工单:02 投诉工单:03 巡检工单:04
        LocalDateTime now =LocalDateTime.now();
        workOrderDto.setWorkOrderCode("02"+now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        //工单状态默认 待处理：0
        workOrderDto.setWorkOrderStatus("0");
        workOrderDto.setCreateTime(new Date());
        return  repairDao.saveWorkOrder(workOrderDto);
    }

    @Override
    public RepairVo getRepairById(String repairCode) {
        return repairDao.getRepairById(repairCode);
    }

    @Override
    public EquipmentVo getEquipmentByCode(String terminalCode) {
        return repairDao.getEquipmentByCode(terminalCode);
    }

    @Override
    public List<InspectionEquipmentVo> getInspectionEquipmentByCode(InspectionEquipmentDto inspectionEquipmentDto) {
        getTime(inspectionEquipmentDto);
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println( simpleDateFormat.format(inspectionEquipmentDto.getStartTime())+"--"+simpleDateFormat.format(inspectionEquipmentDto.getEndTime()));
        if("1".equals(inspectionEquipmentDto.getLogo())){
            //已巡检
            List<InspectionEquipmentVo> list = repairDao.getInspectionEquipmentByCode(inspectionEquipmentDto);
            for(int i=0;i<list.size();i++){
                list.get(i).setLogo("1");
            }
            return list;
        }else{
            //未巡检
            List<InspectionEquipmentVo> list = repairDao.getInspectionEquipment(inspectionEquipmentDto);
            for(int i=0;i<list.size();i++){
                list.get(i).setLogo("1");
            }

        }

        return null;
    }

    @Override
    public DevicesNumberVo getDevicesNumber() {
        return repairDao.getDevicesNumber();
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
