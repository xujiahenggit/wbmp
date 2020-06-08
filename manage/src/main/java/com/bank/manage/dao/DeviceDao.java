package com.bank.manage.dao;

import com.bank.manage.dos.DeviceDO;
import com.bank.manage.dto.DeviceDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 设备信息
 *
 * @author
 * @date 2020-4-1
 */
public interface DeviceDao extends BaseMapper<DeviceDO> {
    IPage<DeviceDTO> selectPageListByPageQueryModel(Page<DeviceDTO> page, @Param("groupId") String groupId,
                                                    @Param("terminalNum")String terminalNum, @Param("orgId")String orgId, @Param("status")String status, @Param("deviceType")String deviceType);
}
