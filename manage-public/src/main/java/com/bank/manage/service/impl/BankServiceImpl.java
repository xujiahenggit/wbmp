package com.bank.manage.service.impl;

import com.bank.manage.dao.BankDao;
import com.bank.manage.dos.BankDO;
import com.bank.manage.service.BankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 银行列表查询
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class BankServiceImpl extends ServiceImpl<BankDao, BankDO> implements BankService {

    @Autowired(required = false)
    private BankDao bankDao;

    @Override
    public List<BankDO> queryList() {
        ArrayList<BankDO> list = new ArrayList<>();
        List<BankDO> bankDOS = bankDao.queryBank();
        for (BankDO bankDO : bankDOS) {
            bankDO.setType("bank");
            bankDO.setTitle(bankDO.getStrBankName());
            bankDO.setKey(bankDO.getStrBankNum());
        }
        List<BankDO> bankDOS1 = bankDao.queryBranch();
        for (BankDO bankDO : bankDOS1) {

            bankDO.setType("branch");
            bankDO.setTitle(bankDO.getStrBranchName());
            bankDO.setKey(bankDO.getStrBranchNum());
            bankDO.setParentKey(bankDO.getStrBankNum());
        }
        List<BankDO> bankDOS2 = bankDao.querySubBranch();
        for (BankDO bankDO : bankDOS2) {
            bankDO.setType("subbranch");
            bankDO.setTitle(bankDO.getStrSubBranchName());
            bankDO.setKey(bankDO.getStrSubBranchNum());
            bankDO.setParentKey(bankDO.getStrBranchNum());
        }
        List<BankDO> bankDOS3 = bankDao.querySsbank();
        for (BankDO bankDO : bankDOS3) {
            bankDO.setType("ssb");
            bankDO.setTitle(bankDO.getStrSsbName());
            bankDO.setKey(bankDO.getStrSsbNum());
            bankDO.setParentKey(bankDO.getStrSubBranchNum());
        }
        list.addAll(bankDOS);
        list.addAll(bankDOS1);
        list.addAll(bankDOS2);
        list.addAll(bankDOS3);
        log.info("银行列表查询：{}", list);
        return list;
    }
}
