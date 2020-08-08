package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.SvcStatuslogDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SvcStatuslogService extends IService<SvcStatuslogDO> {
    IPage<SvcStatuslogDO> queryList(PageQueryModel pageQueryModel);

    void setSvc(SvcStatuslogDO statuslogDO);
}
