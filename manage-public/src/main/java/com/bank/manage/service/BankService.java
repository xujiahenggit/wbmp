package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.BankDO;
import com.bank.manage.dos.MsglogDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BankService extends IService<BankDO> {
    List<BankDO> queryList();
}
