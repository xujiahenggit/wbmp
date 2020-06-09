package com.bank.manage.dao;

import com.bank.manage.dos.DeviceDO;
import com.bank.manage.dto.DeviceDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备信息
 *
 * @author
 * @date 2020-4-1
 */
public interface DeviceDao extends BaseMapper<DeviceDO> {
    IPage<DeviceDTO> selectPageListByPageQueryModel(Page<DeviceDTO> page, @Param("groupId") String groupId,
                                                       @Param("deviceType")String deviceType);

    IPage<DeviceDTO> queryPageByListOrgIds(Page<DeviceDTO> page, @Param("groupId") String groupId,@Param("deviceType") String deviceType,
                                           @Param("ids") List<String> listOrgIds);
}
