package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.DevicePlayDO;
import com.bank.manage.dto.DevicePlayDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

public interface DevicePlayService {
    /**
     * 新增设备播放流水
     * @param devicePlayDTO
     * @return
     */
    Boolean saveDevicePlay(DevicePlayDTO devicePlayDTO);

    IPage<DevicePlayDO> queryDevicePlay(PageQueryModel pageQueryModel);

    List<Map<String, Object>> queryPlayLatestTwo();
}
