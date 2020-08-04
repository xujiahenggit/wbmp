package com.bank.manage.service.impl;

import com.bank.manage.dao.DcStatuslogDao;
import com.bank.manage.dos.DcStatuslogDO;
import com.bank.manage.service.DcStatuslogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 硬件故障历史
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class DcStatuslogServiceImpl extends ServiceImpl<DcStatuslogDao, DcStatuslogDO> implements DcStatuslogService {

    @Autowired(required = false)
    private DcStatuslogDao dcStatuslogDao;

    @Override
    public List<DcStatuslogDO> queryList(String termNum) {

        List<DcStatuslogDO> list = dcStatuslogDao.queryList(termNum);
        log.info("DcStatusServiceImpl：{}", list);
        return list;
    }

    @Override
    public Boolean log(DcStatuslogDO dcStatuslogDO) {
        QueryWrapper<DcStatuslogDO> wrapper = new QueryWrapper<DcStatuslogDO>()
                .ge("STRTERMNUM", dcStatuslogDO.getStrTermNum())
                .ge("STRVMNAME", dcStatuslogDO.getStrVMName())
                .ge("STRHDWSTATUS", dcStatuslogDO.getStrhdwstatus())
                .ge("DTBEGIN", dcStatuslogDO.getDtBegin());
        Integer count = dcStatuslogDao.selectCount(wrapper);
        if (count == 0) {
            DcStatuslogDO last = dcStatuslogDao.queryLast(dcStatuslogDO);
            if (last != null) {
                last.setDtEnd(dcStatuslogDO.getDtBegin());
                dcStatuslogDao.updateById(last);
            }
            dcStatuslogDO.setDtEnd(LocalDateTime.now());
            dcStatuslogDao.insert(dcStatuslogDO);
        } else {
            DcStatuslogDO dcStatuslogDO1 = dcStatuslogDao.selectOne(wrapper);
            dcStatuslogDO1.setDtEnd(LocalDateTime.now());
            dcStatuslogDao.insert(dcStatuslogDO1);
        }
        return null;
    }

    @Override
    public boolean save(DcStatuslogDO dcStatuslogDO) {
        dcStatuslogDao.insert(dcStatuslogDO);
        log.info("硬件故障历史插入：{}", dcStatuslogDO);
        return true;
    }
}
