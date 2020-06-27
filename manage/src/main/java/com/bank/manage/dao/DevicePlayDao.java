package com.bank.manage.dao;

import com.bank.manage.dos.DevicePlayDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DevicePlayDao extends BaseMapper<DevicePlayDO> {
    List<DevicePlayDO> queryPlayLatestTwo();
}
