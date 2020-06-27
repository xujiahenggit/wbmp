package com.bank.pad.dao;

import com.bank.manage.dos.DeviceDO;
import com.bank.manage.dto.DeviceDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface PadDeviceDao {
    IPage<DeviceDTO> queryDeviceByOrgId(Page<DeviceDTO> page, @Param("orgId") String orgId,
                                       @Param("terminalNum") String terminalNum, @Param("deviceName") String deviceName, @Param("deviceType") String deviceType);
}
