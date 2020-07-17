package com.bank.manage.dao;


import com.bank.manage.dto.KioskDto;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SsarunDeviceDao {

    /**
     * 根据机构号查询设备列表
     * @param page
     * @param KioskDto
     * @return
     */
    IPage<SsarunDeviceVo> ssarunDeviceList(Page<SsarunDeviceVo> page, @Param("model") KioskDto KioskDto);

    /**
     *根据终端编号list查询终端状态的List
     * @param list
     * @return
     */
    List<SsaViewTermStatusVo> termStatusList(@Param("list") List<String> list);

    /**
     * 根据id查询设备
     * @param id
     * @return
     */
    DeviceDetailsVo getDeviceDetailsById(@Param("id") String id);

    /**
     * 获取终端状态
     * @param terminalCode
     * @return
     */
    TerminalDetailsVo getTerminalDetailsById(@Param("terminalCode") String terminalCode);

    DeviceVendorVo getDeviceVendorByCode(@Param("deviceVendor") String deviceVendor);

    /**
     * 根据设备id获取设备模块列表
     * @param deviceId
     * @return
     */
    List<SsarunDeviceModelVo> getDeviceModelList(@Param("deviceId") String deviceId);


}
