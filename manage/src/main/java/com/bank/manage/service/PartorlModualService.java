package com.bank.manage.service;

import com.bank.manage.dos.PartorlModualDO;
import com.bank.manage.dto.PartorlDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/11 11:26
 */
public interface PartorlModualService extends IService<PartorlModualDO> {
    /**
     * 获取巡查内容列表
     * @return
     */
    List<PartorlDto> getList(Integer processId);
}
