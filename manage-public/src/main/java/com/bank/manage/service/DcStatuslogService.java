package com.bank.manage.service;

import com.bank.manage.dos.DcStatuslogDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DcStatuslogService extends IService<DcStatuslogDO> {
    List<DcStatuslogDO> queryList(String termNum);

    Boolean log(DcStatuslogDO dcStatuslogDO);
}
