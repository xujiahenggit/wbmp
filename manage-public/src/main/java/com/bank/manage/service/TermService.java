package com.bank.manage.service;

import com.bank.manage.dos.TermDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TermService extends IService<TermDO> {
    List<TermDO> queryList();
}
