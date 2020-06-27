package com.bank.log.dao;

import com.bank.log.dos.LogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/1 9:50
 */
public interface LogDao extends BaseMapper<LogDO> {
    /**
     * 获取所有的 模块列表
     * @return
     */
    List<String> selectModuleList();

    /**
     * 获取所有的 操作类型列表
     * @return
     */
    List<String> selectOptTypes();

    /**
     * 获取日志版本 列表
     * @return
     */
    List<String> selectLogVersions();
}
