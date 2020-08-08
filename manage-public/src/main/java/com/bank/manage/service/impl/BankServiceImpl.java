package com.bank.manage.service.impl;

import com.bank.core.config.DataSource;
import com.bank.core.config.DynamicDataSourceSwitcher;
import com.bank.manage.dao.BankDao;
import com.bank.manage.dao.BranchDao;
import com.bank.manage.dao.SubBranchDao;
import com.bank.manage.dos.BankDO;
import com.bank.manage.dos.BranchDO;
import com.bank.manage.dos.SubBranchDO;
import com.bank.manage.service.BankService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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

    @Autowired(required = false)
    private BranchDao branchDao;

    @Autowired(required = false)
    private SubBranchDao subBranchDao;

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public String selectByOrgcode(String orgId) {
        //超管返回空
        if (StringUtils.isBlank(orgId)) {
            return "-1";
        } else if (orgId.equals("100")) {
            return "";
        }

        String bankNum = bankDao.selectBankNumByOrgcode(orgId);
        if (StringUtils.isNotBlank(bankNum)) {
            return bankNum;
        }
        //根据orgcode查询原监控的分支行编号
        QueryWrapper<BranchDO> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq(StringUtils.isNotBlank(orgId), "ORG_CODE", orgId);
        BranchDO branchDO = branchDao.selectOne(queryWrapper1);
        if (branchDO != null && StringUtils.isNotBlank(branchDO.getStrbranchnum())) {
            return branchDO.getStrbranchnum();
        }

        QueryWrapper<SubBranchDO> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq(StringUtils.isNotBlank(orgId), "ORG_CODE", orgId);
        SubBranchDO subBranchDO = subBranchDao.selectOne(queryWrapper2);
        if (subBranchDO != null && StringUtils.isNotBlank(subBranchDO.getStrbranchnum())) {
            return subBranchDO.getStrsubbranchnum();
        }
        return "-1";
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<BankDO> queryList(String powerNum) {
        ArrayList<BankDO> list = new ArrayList<>();
        List<BankDO> bankDOS = bankDao.queryBank(powerNum);
        for (BankDO bankDO : bankDOS) {
            bankDO.setType("bank");
            bankDO.setTitle(bankDO.getStrBankName());
            bankDO.setKey(bankDO.getStrBankNum());
        }
        List<BankDO> bankDOS1 = bankDao.queryBranch(powerNum);
        for (BankDO bankDO : bankDOS1) {

            bankDO.setType("branch");
            bankDO.setTitle(bankDO.getStrBranchName());
            bankDO.setKey(bankDO.getStrBranchNum());
            bankDO.setParentKey(bankDO.getStrBankNum());
        }
        List<BankDO> bankDOS2 = bankDao.querySubBranch(powerNum);
        for (BankDO bankDO : bankDOS2) {
            bankDO.setType("subbranch");
            bankDO.setTitle(bankDO.getStrSubBranchName());
            bankDO.setKey(bankDO.getStrSubBranchNum());
            bankDO.setParentKey(bankDO.getStrBranchNum());
        }
        List<BankDO> bankDOS3 = bankDao.querySsbank(powerNum);
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

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public String getBankName(String bankNum) {
        return bankDao.queryBankName(bankNum);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public String getSsbName(String ssbNum) {
        return bankDao.querySsbName(ssbNum);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public String getBranchName(String branchNum) {
        QueryWrapper<BranchDO> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq(StringUtils.isNotBlank(branchNum), "STRBRANCHNUM", branchNum);
        BranchDO branchDO = branchDao.selectOne(queryWrapper1);
        if (branchDO != null && StringUtils.isNotBlank(branchDO.getStrbranchname())) {
            return branchDO.getStrbranchname();
        }
        return null;
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public String getSubBranchName(String subBranchNum) {
        QueryWrapper<SubBranchDO> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq(StringUtils.isNotBlank(subBranchNum), "STRSUBBRANCHNUM", subBranchNum);
        SubBranchDO subBranchDO = subBranchDao.selectOne(queryWrapper2);
        if (subBranchDO != null && StringUtils.isNotBlank(subBranchDO.getStrsubbranchname())) {
            return subBranchDO.getStrsubbranchname();
        }
        return null;
    }
}
