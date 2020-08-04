package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.CommandlogDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CommandlogService extends IService<CommandlogDO> {
    IPage<CommandlogDO> queryList(PageQueryModel pageQueryModel);
}
