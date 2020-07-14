package com.bank.manage.service;


import com.bank.manage.vo.*;

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

    Map<String, Object> queryOperation(String branch);

    RankInfo rankInfo(String orgId, String tellerId);

    /**
     * 获取网点设备列表
     * @param orgId
     * @return
     */
    List<DeviceInfoVo>  getOrgDeviceList(String orgId);

    /**
     * 根据设备deviceId查询设备信息信息
     * @param deviceId
     * @return
     */
    DeviceDetailInfoVo findByDeviceId(String deviceId);

    /**
     * 自助设备交易趋势
     * @param termNo
     * @return
     */
    List<DeviceTradeTrendVo> deviceTradeList(String orgId,String termNo, String queryType);

    /**
     * 网点自助设备交易趋势
     * @param orgId
     * @param queryType
     * @return
     */
    List<DeviceTradeTrendVo> deviceOrgTradeList(String orgId, String queryType);
}
