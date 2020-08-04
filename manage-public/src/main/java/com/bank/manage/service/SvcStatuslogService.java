package com.bank.manage.service;

import com.bank.manage.dos.SvcStatuslogDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SvcStatuslogService extends IService<SvcStatuslogDO> {
    List<SvcStatuslogDO> queryList(String termNum);

    void setSvc(SvcStatuslogDO statuslogDO);
}
