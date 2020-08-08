package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.DcStatuslogDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DcStatuslogService extends IService<DcStatuslogDO> {
    IPage<DcStatuslogDO> queryList(PageQueryModel pageQueryModel);

    Boolean log(DcStatuslogDO dcStatuslogDO);
}
