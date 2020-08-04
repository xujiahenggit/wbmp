package com.bank.manage.dao;

import com.bank.manage.dos.BankDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 银行表
 *
 * @author
 * @date 2020-7-7
 */
public interface BankDao extends BaseMapper<BankDO> {
    List<BankDO> queryBank();
    List<BankDO> queryBranch ();
    List<BankDO> querySubBranch();
    List<BankDO> querySsbank();
}
