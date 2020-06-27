package com.bank.manage.service;

import com.bank.manage.vo.TellerTransactionVo;

import java.util.List;
import java.util.Map;

public interface WbmpTellerTransactionService {
    List<Map<String,Object>> queryTransaction(String orgId, String tellerId);
}
