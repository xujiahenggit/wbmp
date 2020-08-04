package com.bank.manage.service.impl;

import com.bank.manage.dao.DcStatusDao;
import com.bank.manage.dos.DcStatusDO;
import com.bank.manage.service.DcStatusService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 硬件状态表
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class DcStatusServiceImpl extends ServiceImpl<DcStatusDao, DcStatusDO> implements DcStatusService {

    @Autowired(required = false)
    private DcStatusDao dcStatusDao;


    @Override
    public List<DcStatusDO> queryList(String termNum) {
        List<DcStatusDO> list = dcStatusDao.queryList(termNum);
        log.info("硬件状态表：{}", list);
        return list;
    }

    @Override
    public LocalDateTime getNow(DcStatusDO dcStatusDO) {
        LocalDateTime now = LocalDateTime.now();
        QueryWrapper<DcStatusDO> wrapper = new QueryWrapper<>();
        wrapper.ge("STRTERMNUM", dcStatusDO.getStrTermNum());
        wrapper.ge("STRVMNAME", dcStatusDO.getStrVMName());
        DcStatusDO dcStatusDO1 = dcStatusDao.selectOne(wrapper);
        if (dcStatusDO1 != null) {
            dcStatusDO.setId(dcStatusDO1.getId());
            if (dcStatusDO1.getIhdwStatus().equals(dcStatusDO.getIhdwStatus())) {
                now = dcStatusDO1.getDtHDWStatusBegin();
            }
            dcStatusDO.setDtHDWStatusBegin(now);
            dcStatusDao.updateById(dcStatusDO);
        } else {
            dcStatusDO.setDtHDWStatusBegin(now);
            dcStatusDao.insert(dcStatusDO);
        }
        log.info("硬件状态更新：{}", dcStatusDO);
        return now;
    }
}
