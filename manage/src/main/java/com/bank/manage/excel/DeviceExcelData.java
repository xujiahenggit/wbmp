package com.bank.manage.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeviceExcelData implements Serializable {
    private static final long serialVersionUID = -4629805664609833535L;
    @ExcelProperty("设备名称")
    private String deviceName;
    @ExcelProperty("机构号")
    private String orgId;
    @ExcelProperty("机构名称")
    private String orgName;
    @ExcelProperty("设备类型")
    private String deviceType;
    @ExcelProperty("设备系统")
    private String deviceSystem;
    @ExcelProperty("所在位置简称")
    private String addressAbbr;
    @ExcelProperty("IP地址")
    private String ip;
    @ExcelProperty("网关")
    private String gateWay;
    @ExcelProperty("MAC地址")
    private String mac;
}
