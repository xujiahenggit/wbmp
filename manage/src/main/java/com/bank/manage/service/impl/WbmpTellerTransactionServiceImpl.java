package com.bank.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import com.bank.manage.dao.WbmpTellerTransactionDao;
import com.bank.manage.service.WbmpTellerTransactionService;
import com.bank.manage.vo.TellerTransactionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WbmpTellerTransactionServiceImpl implements WbmpTellerTransactionService {

    @Resource
    private WbmpTellerTransactionDao wbmpTellerTransactionDao;

    @Override
    public List<Map<String,Object>> queryTransaction(String orgId, String tellerId) {
        List<TellerTransactionVo> tellerTransaction =  wbmpTellerTransactionDao.queryTransaction(orgId,tellerId);
        List<Map<String,Object>> list = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(tellerTransaction)){
            for (TellerTransactionVo vo : tellerTransaction) {
                Number tradeVolume = vo.getTradeVolume();
                String tranName = vo.getTranName();
                Number count = vo.getCount();
                Map<String,Object>  transactionMap = new HashMap<>();
                transactionMap.put("tran_name",tranName);
                transactionMap.put("trade_volume",tradeVolume);
                BigDecimal tran_lv = NumberUtil.div(tradeVolume, count, 2);
                transactionMap.put("tran_lv",tran_lv);
                transactionMap.put("count",count);
                list.add(transactionMap);
            }
        }
        return list;
    }
}
