package com.bank.manage.dao;

import com.bank.manage.dos.PartorlRecordDO;
import com.bank.manage.excel.partorl.PartorlExcelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Andy
 * @Date: 2020/5/12 15:07
 */
public interface PartorlRecordDao extends BaseMapper<PartorlRecordDO> {
    /**
     * 用ID  获取巡查记录信息
     * @param recordId 巡查记录信息
     * @return
     */
    PartorlExcelEntity getRecordById(@Param(value = "recordId") Integer recordId);
}
