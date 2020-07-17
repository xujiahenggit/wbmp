package com.bank.manage.service;


import com.bank.manage.dto.KioskDto;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SsarunDeviceService {

    /**
     * 查询设备列表[自助设备管理监控平台 ssarun库]
     * @param kioskDto
     * @return
     */
    IPage<SsarunDeviceVo> ssarunDeviceList(KioskDto kioskDto);

    /**
     * 根据终端编号list查询终端状态list
     * @param list
     * @return
     */
    List<SsaViewTermStatusVo>termStatusList(@Param("list") List<String> list);

    /**
     * 根据id查询设备信息
     * @param id
     * @return
     */
    DeviceDetailsVo getDeviceDetailsById(String id);

    /**
     * 获取终端状态
     * @param terminalCode
     * @return
     */
    TerminalDetailsVo getTerminalDetailsById(String terminalCode);

    /**
     * 厂商信息
     * @param deviceVendor
     * @return
     */
    DeviceVendorVo getDeviceVendorByCode(String deviceVendor);

    /**
     * 根据设备id,获取设备模块列表
     * @param deviceId
     * @return
     */
    List<SsarunDeviceModelVo> getDeviceModelList(@Param("deviceId") String deviceId);

    List<ReaderStatusList> getReaderStatusListById(String terminalCode);

    List<PrinterListVo> getPrinterListById(String terminalCode);
}
