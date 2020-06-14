package com.bank.manage.service;


import com.bank.manage.vo.AbsTellerInfo;
import com.bank.manage.vo.RankInfo;
import com.bank.manage.vo.TellerOnlineInfo;
import com.bank.manage.vo.TransCntInfo;

import java.util.List;
import java.util.Map;

/**
 * 运营看板统计 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-10
 */
public interface BusinessPanelService {


    List<TellerOnlineInfo> getTellerOnlineInfo();

    TellerOnlineInfo getTellerOnlineInfo(String tellerId);

    Map<String, Integer> deviceTotal(String orgId);

    List<Map<String, Integer>> deviceStatus(String orgId);

    List<TransCntInfo> devicetransCnfTop5(String orgId);

    List<TransCntInfo> tradeVolumeTop5(String orgId);

    List<AbsTellerInfo> tellertPageList(String orgId);

    List<Map<String, Object>> queryOperation(String branch);

    RankInfo rankInfo(String orgId, String tellerId);
}
