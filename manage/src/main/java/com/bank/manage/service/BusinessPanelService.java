package com.bank.manage.service;


import com.bank.manage.vo.TellerOnlineInfo;

import java.util.List;

/**
 * 运营看板统计 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-10
 */
public interface BusinessPanelService {


    List<TellerOnlineInfo> getTellerOnlineInfo();

    TellerOnlineInfo getTellerOnlineInfo(String tellerId);
}
