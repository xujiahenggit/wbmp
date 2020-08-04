package com.bank.manage.dao;

import com.bank.manage.dos.SettlecyclelogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 未清机信息
 *
 * @author
 * @date 2020-7-7
 */
public interface SettlecyclelogDao extends BaseMapper<SettlecyclelogDO> {
    SettlecyclelogDO querySettlecyclelog(@Param("model") String termNum);
    SettlecyclelogDO queryDeposit(@Param("model") String termNum);
    SettlecyclelogDO queryWithdraw(@Param("model") String termNum);
}