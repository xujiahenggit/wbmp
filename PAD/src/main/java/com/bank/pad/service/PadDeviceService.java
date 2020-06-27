package com.bank.pad.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dto.DeviceDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface PadDeviceService {
    IPage<DeviceDTO> queryList(PageQueryModel pageQueryModel);
}
