package com.bank.manage.dao;

import com.bank.manage.dos.PartorlRecordItemDO;
import com.bank.manage.excel.partorl.PartorlContentExcelEntity;
import com.bank.manage.excel.partorl.PartorlModualExcelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/12 15:07
 */
public interface PartorlRecordItemDao extends BaseMapper<PartorlRecordItemDO> {
    /**
     * 查询 巡查记录列表
     * @param recordId 巡查记录编号
     * @param modualId 巡查模块ID
     * @return
     */
    List<PartorlContentExcelEntity> getListByRecordIds(@Param(value = "recordId") Integer recordId,@Param(value = "modualId") Integer modualId);

    /**
     *
     * @return
     */
    List<PartorlModualExcelEntity> getListModual();
}
