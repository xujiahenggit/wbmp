package com.bank.log.dao;

import com.bank.log.dos.ErrorLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/1 10:38
 */
public interface ErrorLogDao extends BaseMapper<ErrorLogDO> {
    /**
     * 获取错误日志版本列表
     * @return
     */
    List<String> getErrVersion();
}
