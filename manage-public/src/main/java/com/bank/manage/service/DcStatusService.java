package com.bank.manage.service;

import com.bank.manage.dos.DcStatusDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

public interface DcStatusService extends IService<DcStatusDO> {
    List<DcStatusDO> queryList(String termNum);
    LocalDateTime getNow(DcStatusDO dcStatusDO);

}
