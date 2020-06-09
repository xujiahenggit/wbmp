package com.bank.manage.service.impl;

import com.bank.manage.dao.BusinessPanelDao;
import com.bank.manage.service.BusinessPanelService;
import com.bank.manage.vo.TellerOnlineInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 运营看板统计 服务实现类
 *
 * @author zhaozhongyuan
 * @since 2020-06-10
 */
@Service
public class BusinessPanelServiceImpl implements BusinessPanelService {

    @Resource
    BusinessPanelDao businessPanelDao;

    @Override
    public List<TellerOnlineInfo> getTellerOnlineInfo() {
        return businessPanelDao.getTellerOnlineInfo(null);
    }

    @Override
    public TellerOnlineInfo getTellerOnlineInfo(String tellerId) {
        List<TellerOnlineInfo> tellerOnlineInfo = businessPanelDao.getTellerOnlineInfo(tellerId);
        if (tellerOnlineInfo.size()==1){
            return tellerOnlineInfo.get(0);
        }
        return null;
    }
}
