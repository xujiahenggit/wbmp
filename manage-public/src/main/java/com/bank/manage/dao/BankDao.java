package com.bank.manage.dao;

import com.bank.manage.dos.BankDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 银行表
 *
 * @author
 * @date 2020-7-7
 */
public interface BankDao extends BaseMapper<BankDO> {
    List<BankDO> queryBank(@Param("model") String powerNum);

    List<BankDO> queryBranch(@Param("model") String powerNum);

    List<BankDO> querySubBranch(@Param("model") String powerNum);

    List<BankDO> querySsbank(@Param("model") String powerNum);

    String selectBankNumByOrgcode(@Param("model") String orgcode);

    String queryBankName(@Param("model") String bankNum);

    String querySsbName(@Param("model") String ssbNum);
}
