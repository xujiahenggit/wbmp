package com.bank.manage.listener;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bank.manage.dos.DeviceDO;
import com.bank.manage.excel.DeviceExcelData;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.service.DeviceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.*;

public class DeviceExcelListener extends AnalysisEventListener<DeviceExcelData> {

    private ImportExcelResponse response;
    private DeviceService deviceService;
    private String userId;


    public DeviceExcelListener(DeviceService deviceService,String userId,ImportExcelResponse response){
        this.deviceService = deviceService;
        this.response = response;
        this.userId = userId;
    }

    @Override
    public void invoke(DeviceExcelData data, AnalysisContext context) {
        QueryWrapper<DeviceDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("MAC",data.getMac());
        DeviceDO deviceDO = deviceService.getOne(queryWrapper);
        if(ObjectUtil.isNull(deviceDO)){
            DeviceDO device = DeviceDO.builder()
                    .terminalNum("BCS" + System.currentTimeMillis())
                    .orgId(data.getOrgId())
                    .orgName(data.getOrgName())
                    .deviceName(data.getDeviceName())
                    .deviceType(getDeviceType(data.getDeviceType()))
                    .deviceSystem(getDeviceSystem(data.getDeviceSystem()))
                    .addressAbbr(data.getAddressAbbr())
                    .ip(data.getIp())
                    .gateWay(data.getGateWay())
                    .mac(data.getMac().trim().toUpperCase())
                    .status("01")
                    .createTime(new Date())
                    .createUser(userId).build();
            importData(device, context.readRowHolder().getRowIndex());
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    private void importData(DeviceDO device, Integer rowIndex) {
        try {
            this.deviceService.save(device);
        }
        catch (Exception e) {
            this.response.setStatus(false);
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("rowIndex", rowIndex);
            errorMap.put("errorMsg", e.getMessage());
            this.response.getErrorRows().add(errorMap);
        }
    }

    private String getDeviceSystem(String deviceSystemName){
        String deviceSystem = null;
        switch (deviceSystemName){
            case "安卓":
                deviceSystem = "00";
                break;
            case "IOS":
                deviceSystem = "02";
                break;
            default:
                deviceSystem = "01";
                break;
        }
        return deviceSystem;
    }

    private String getDeviceType(String deviceTypeName){
        String deviceType = null;
        switch (deviceTypeName){
            case "竖屏":
                deviceType = "00";
                break;
            case "柱面屏":
                deviceType = "01";
                break;
            case "叫号屏":
                deviceType = "02";
                break;
            default:
                deviceType = "03";
                break;
        }
        return deviceType;
    }

}
