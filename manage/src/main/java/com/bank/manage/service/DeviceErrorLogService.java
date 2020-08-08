package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.DeviceErrorLogDO;
import com.bank.manage.dto.DeviceErrorLogDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DeviceErrorLogService extends IService<DeviceErrorLogDO> {

    /**
     * 新增设备错误信息
     * @param deviceErrorLogDTO
     * @return
     */
    Boolean saveErrorLog(DeviceErrorLogDTO deviceErrorLogDTO);

    IPage<DeviceErrorLogDO> queryDeviceErrorLog(PageQueryModel pageQueryModel);
}
