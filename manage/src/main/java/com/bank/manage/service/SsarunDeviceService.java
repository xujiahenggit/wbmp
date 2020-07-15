package com.bank.manage.service;


import com.bank.manage.dto.KioskDto;
import com.bank.manage.vo.SsarunDeviceVo;
import com.baomidou.mybatisplus.core.metadata.IPage;


public interface SsarunDeviceService {

    IPage<SsarunDeviceVo> ssarunDeviceList(KioskDto kioskDto);
}
