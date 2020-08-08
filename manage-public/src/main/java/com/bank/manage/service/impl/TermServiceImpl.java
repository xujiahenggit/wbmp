package com.bank.manage.service.impl;

import com.bank.manage.dao.TermDao;
import com.bank.manage.dos.TermDO;
import com.bank.manage.service.BankService;
import com.bank.manage.service.TermService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
    private TermDao termDao;

    @Autowired(required = false)
    private BankService bankService;

    @Override
    public List<TermDO> queryList(String powerNum) {
        List<TermDO> termDOS = termDao.queryList(powerNum);
        for (TermDO termDO : termDOS) {
            String strBankNum = termDO.getStrBankNum();
            if (StringUtils.isNotBlank(strBankNum)) {
                String bankName = bankService.getBankName(strBankNum);
                termDO.setStrBankName(bankName);
                String strBranchNum = termDO.getStrBranchNum();
                if (StringUtils.isNotBlank(strBranchNum)) {
                    String branchName = bankService.getBranchName(strBranchNum);
                    termDO.setStrBranchName(branchName);
                    String strSubBranchNum = termDO.getStrSubBranchNum();
                    if (StringUtils.isNotBlank(strSubBranchNum)) {
                        String subBranchName = bankService.getSubBranchName(strSubBranchNum);
                        termDO.setStrSubBranchName(subBranchName);
                        String strSsbNum = termDO.getStrSsbNum();
                            if (StringUtils.isNotBlank(strSsbNum)) {
                                String ssbName = bankService.getSsbName(strSsbNum);
                                termDO.setStrSsbName(ssbName);
                        }
                    }
                }
            }
        }

        log.info("终端设备查询：{}", termDOS);
        return termDOS;
    }

    @Override
    public boolean save(TermDO termDO) {
        QueryWrapper<TermDO> wrapper = new QueryWrapper<>();
        wrapper.eq("strtermnum", termDO.getStrTermNum());
        if (termDao.selectCount(wrapper) > 0) {
            TermDO termDO1 = termDao.selectOne(wrapper);
            termDO.setId(termDO1.getId());
            termDao.updateById(termDO);
        } else {
            termDao.insert(termDO);
        }
        return true;
    }
}
