package com.bank.manage.service;

import com.bank.manage.dos.PartorlRecordItemDO;
import com.bank.manage.excel.partorl.PartorlContentExcelEntity;
import com.bank.manage.excel.partorl.PartorlModualExcelEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/12 15:08
 */
public interface PartorlRecordItemService extends IService<PartorlRecordItemDO> {
    /**
     * 查找 巡查内容结果
     * @param recordId 巡查记录编号
     * @param modualId 巡查模块ID
     * @return
     */
    List<PartorlContentExcelEntity> getListByRecordIds(Integer recordId,Integer modualId);

    /**
     * 查找 巡查模块
     * @return
     */
    List<PartorlModualExcelEntity> getListModual();
}
