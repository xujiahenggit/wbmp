package com.bank.manage.service;


import com.bank.manage.dto.KioskDto;
import com.bank.manage.vo.SsaViewTermStatusVo;
import com.bank.manage.vo.SsarunDeviceVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SsarunDeviceService {

    /**
     * 查询设备列表[自助设备管理监控平台 ssarun库]
     * @param kioskDto
     * @return
     */
    IPage<SsarunDeviceVo> ssarunDeviceList(KioskDto kioskDto);

    /**
     * 根据终端编号list查询终端状态list
     * @param list
     * @return
     */
    List<SsaViewTermStatusVo>termStatusList(@Param("list") List<String> list);
}
