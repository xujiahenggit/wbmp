package com.bank.manage.dao;

import com.bank.manage.vo.TellerTransactionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WbmpTellerTransactionDao {
    List<TellerTransactionVo> queryTransaction(@Param("orgId") String orgId, @Param("tellerId") String tellerId);
}
