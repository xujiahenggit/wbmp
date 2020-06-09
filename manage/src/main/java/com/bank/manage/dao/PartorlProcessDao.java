package com.bank.manage.dao;

import com.bank.manage.dos.PartorlProcessDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Andy
 * @Date: 2020/5/27 21:04
 */
public interface PartorlProcessDao extends BaseMapper<PartorlProcessDO> {
    /**
     * 获取 待办数目
     * @param orgId 机构号
     * @return
     */
    int getWaitListNum(@Param(value = "orgId") String orgId);
}
