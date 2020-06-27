package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.DeviceDO;
import com.bank.manage.dto.DeviceDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DeviceService extends IService<DeviceDO> {

    /**
     * 插入设备信息
     * @param deviceDTO
     * @return
     */
    Boolean save(DeviceDTO deviceDTO, Integer groupId, TokenUserInfo tokenUserInfo);

    /**
     * 修改设备信息
     * @param deviceDTO
     * @return
     */
    Boolean update(DeviceDTO deviceDTO, TokenUserInfo tokenUserInfo);

    /**
     * 删除设备信息
     * @param id
     * @return
     */
    Boolean delete(Integer id);

    /**
     * 修改设备状态
     * @param deviceDTO
     * @return
     */
    Integer updateStatus(DeviceDTO deviceDTO);

    /**
     * 查询设备信息列表
     * @param pageQueryModel
     * @return
     */
    IPage<DeviceDTO> queryList(PageQueryModel pageQueryModel);

    /**
     * 设备编号校验
     * @param terminalNum
     * @return
     */
    Integer checkTerminalNum(String terminalNum);

    DeviceDTO queryDeviceByMac(String macAddress);
}
