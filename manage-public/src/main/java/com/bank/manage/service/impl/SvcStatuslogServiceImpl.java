package com.bank.manage.service.impl;

import com.bank.manage.dao.SvcStatuslogDao;
import com.bank.manage.dos.SvcStatuslogDO;
import com.bank.manage.service.SvcStatuslogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 服务故障历史
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class SvcStatuslogServiceImpl extends ServiceImpl<SvcStatuslogDao, SvcStatuslogDO> implements SvcStatuslogService {

    @Autowired
    private SvcStatuslogDao svcStatuslogDao;


    @Override
    public List<SvcStatuslogDO> queryList(String termNum) {
        List<SvcStatuslogDO> list = svcStatuslogDao.queryList(termNum);
        log.info("服务故障历史查询：{}", list);
        return list;
    }

    @Override
    public boolean save(SvcStatuslogDO svcStatuslogDO) {
        svcStatuslogDao.insert(svcStatuslogDO);
        log.info("服务故障历史插入：{}", svcStatuslogDO);
        return true;
    }

    @Override
    public void setSvc(SvcStatuslogDO statuslogDO) {
        QueryWrapper<SvcStatuslogDO> queryWrapper1 = new QueryWrapper<SvcStatuslogDO>()
                .eq("STRTERMNUM", statuslogDO.getStrtermnum());
        Integer count1 = svcStatuslogDao.selectCount(queryWrapper1);
        QueryWrapper<SvcStatuslogDO> queryWrapper = new QueryWrapper<SvcStatuslogDO>()
                .eq("STRTERMNUM", statuslogDO.getStrtermnum())
                .eq("SVCSTATUS", statuslogDO.getSvcstatus())
                .eq("DTBEGIN", statuslogDO.getDtbegin());
        Integer count = svcStatuslogDao.selectCount(queryWrapper);
        SvcStatuslogDO last = svcStatuslogDao.queryLast(statuslogDO.getStrtermnum());

        if (count1 == 0) {
            statuslogDO.setDtend(statuslogDO.getDtbegin());
            svcStatuslogDao.insert(statuslogDO);
        } else if (count == 0) {
            last.setDtend(statuslogDO.getDtbegin());
            svcStatuslogDao.updateById(last);
            statuslogDO.setDtend(statuslogDO.getDtbegin());
            svcStatuslogDao.insert(statuslogDO);
        } else {
            last.setDtend(LocalDateTime.now());
            svcStatuslogDao.updateById(last);
        }
    }
}
