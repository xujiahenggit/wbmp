package com.bank.manage.service.impl;

import com.bank.manage.dao.TermDao;
import com.bank.manage.dos.TermDO;
import com.bank.manage.service.TermService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 终端设备
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class TermServiceImpl extends ServiceImpl<TermDao, TermDO> implements TermService {

    @Autowired(required = false)
    private TermDao bankDao;

    @Override
    public List<TermDO> queryList() {
        List<TermDO> termDOS = bankDao.queryList();
        log.info("终端设备查询：{}", termDOS);
        return termDOS;
    }

    @Override
    public boolean save(TermDO termDO) {
        QueryWrapper<TermDO> wrapper = new QueryWrapper<>();
        wrapper.eq("strtermnum", termDO.getStrTermNum());
        if (bankDao.selectCount(wrapper) > 0) {
            TermDO termDO1 = bankDao.selectOne(wrapper);
            termDO.setId(termDO1.getId());
            bankDao.updateById(termDO);
        } else {
            bankDao.insert(termDO);
        }
        return true;
    }
}
