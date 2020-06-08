package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.StatusLogDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface StatusLogService extends IService<StatusLogDO> {
    IPage<StatusLogDO> queryStatusLog(PageQueryModel pageQueryModel);
}
