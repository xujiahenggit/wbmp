package com.bank.log.service;

import com.bank.log.dos.ErrorLogDO;
import com.bank.log.vo.ErrLogVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/1 10:39
 */
public interface ErrotLogService extends IService<ErrorLogDO> {
    /**
     * 错误日志 多维度查询
     * @param errLogVo
     * @return
     */
    IPage<ErrorLogDO> SelectErrLogPage(ErrLogVo errLogVo);

    /**
     * 获取错误日志版本
     * @return
     */
    List<String> getErrVersion();
}
