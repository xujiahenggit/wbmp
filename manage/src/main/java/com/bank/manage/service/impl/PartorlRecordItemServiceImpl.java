package com.bank.manage.service.impl;

import com.bank.manage.dao.PartorlRecordItemDao;
import com.bank.manage.dos.PartorlRecordItemDO;
import com.bank.manage.excel.partorl.PartorlContentExcelEntity;
import com.bank.manage.excel.partorl.PartorlModualExcelEntity;
import com.bank.manage.service.PartorlRecordItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/12 15:11
 */
@Service
public class PartorlRecordItemServiceImpl extends ServiceImpl<PartorlRecordItemDao, PartorlRecordItemDO> implements PartorlRecordItemService {

    @Resource
    private PartorlRecordItemDao partorlRecordItemDao;

    /**
     * 查询 巡查记录列表
     * @param recordId 巡查记录编号
     * @param modualId 巡查模块ID
     * @return
     */
    @Override
    public List<PartorlContentExcelEntity> getListByRecordIds(Integer recordId,Integer modualId) {
        return partorlRecordItemDao.getListByRecordIds(recordId,modualId);
    }

    /**
     * 查找所有的巡查模块
     * @return
     */
    @Override
    public List<PartorlModualExcelEntity> getListModual() {
        return partorlRecordItemDao.getListModual();
    }
}
