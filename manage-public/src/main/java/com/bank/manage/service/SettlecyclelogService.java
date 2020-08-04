package com.bank.manage.service;

import com.bank.manage.dos.SettlecyclelogDO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SettlecyclelogService extends IService<SettlecyclelogDO> {
    SettlecyclelogDO getOne(String termNum);
}
