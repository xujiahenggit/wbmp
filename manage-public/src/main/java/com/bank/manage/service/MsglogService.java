package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.MsglogDO;
import com.bank.manage.dos.SparamDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface MsglogService extends IService<MsglogDO> {
    IPage<MsglogDO> queryList(PageQueryModel pageQueryModel);
}
