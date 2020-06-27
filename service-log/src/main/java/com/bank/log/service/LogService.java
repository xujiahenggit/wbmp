package com.bank.log.service;

import com.bank.log.dos.LogDO;
import com.bank.log.vo.LogQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/1 9:35
 */
public interface LogService extends IService<LogDO> {
    /**
     * 查询日志
     *
     * @param logQueryVo
     * @return
     */
    IPage<LogDO> SelectLogPage(LogQueryVo logQueryVo);

    /**
     * 获取操作类型 列表
     * @return
     */
    List<String> selectOptModuls();

    /**
     * 获取操作类型 列表
     * @return
     */
    List<String> selectOptTypes();

    /**
     * 获取 日志版本 列表
     * @return
     */
    List<String> selectLogVersions();
}
