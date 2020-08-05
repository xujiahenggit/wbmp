package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.TransDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface TransService extends IService<TransDO> {
    IPage<TransDO> queryPage(PageQueryModel pageQueryModel);

    void export(PageQueryModel pageQueryModel, HttpServletResponse response);

    Map<String, Object> queryCount(String termNum);
}
