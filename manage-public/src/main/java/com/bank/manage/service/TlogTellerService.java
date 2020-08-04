package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.TlogTellerDO;
import com.bank.manage.vo.TlogTellerVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface TlogTellerService extends IService<TlogTellerDO> {

    /**
     * 查询柜员操作日志信息
     * @param pageQueryModel
     * @return
     */
    IPage<TlogTellerVO> queryList(PageQueryModel pageQueryModel);
}
