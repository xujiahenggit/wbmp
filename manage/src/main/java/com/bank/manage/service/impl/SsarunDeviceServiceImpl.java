package com.bank.manage.service.impl;

import com.bank.core.config.DataSource;
import com.bank.core.config.DynamicDataSourceSwitcher;
import com.bank.manage.dao.SsarunDeviceDao;
import com.bank.manage.dto.KioskDto;
import com.bank.manage.service.SsarunDeviceService;
import com.bank.manage.vo.SsarunDeviceVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SsarunDeviceServiceImpl implements SsarunDeviceService {


    @Resource
    SsarunDeviceDao ssarunDeviceDao;


    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_dev)
    public IPage<SsarunDeviceVo> ssarunDeviceList(KioskDto kioskDto) {
        Page<SsarunDeviceVo> page = new Page<>(kioskDto.getPageIndex(), kioskDto.getPageSize());
        return null;
    }
}
