package com.bank.manage.dao;

import com.bank.manage.dos.WbmpAtmpBasicInfoDO;
import com.bank.manage.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 运营看板统计 Mapper 接口
 *
 * @author zhaozhongyuan
 * @since 2020-06-10
 */
public interface BusinessPanelDao {

    List<TellerOnlineInfo> getTellerOnlineInfo(@Param("tellerId") String tellerId);

    Map<String, Integer> deviceTotal(@Param("orgId") String orgId);

    List<Map<String, Integer>> deviceStatus(@Param("orgId") String orgId);

    List<TransCntInfo> devicetransCnfTop5(@Param("orgId") String orgId);

    int devicetransCnfSum(@Param("orgId") String orgId);

    List<TransCntInfo> tradeVolumeTop5(@Param("orgId") String orgId);

    Integer tradeVolumeSum(@Param("orgId") String orgId);

    //    List<AbsTellerInfo> tellertPageList(IPage page,@Param("orgId") String orgId);
    List<AbsTellerInfo> tellertPageList(@Param("orgId") String orgId);

    List<Map<String, Object>> queryOperation(@Param("branch") String branch, @Param("nowStr") String nowStr);

    /**
     * 总在线柜员数
     * @param orgId，为null全行
     * @return
     */
    Integer onlineSum(@Param("orgId") String orgId);

    /**
     * 总交易量
     * @param orgId，为null全行
     * @return
     */
    Integer tradeSum(@Param("orgId") String orgId);

    /**
     * 交易量排名
     * @param orgId，为null全行排名
     * @param tellerId
     * @return 该柜员是第几名
     */
    Integer tradeNumRank(@Param("orgId") String orgId, @Param("tellerId") String tellerId);

    Float onlineTimeSum(@Param("orgId") String orgId);

    Integer onlineTimeRank(@Param("orgId") String orgId, @Param("tellerId") String tellerId);

    /**
     * 机构设备列表
     * @param orgId
     * @return
     */
    List<DeviceInfoVo> getOrgDeviceList(@Param("orgId")String orgId);

    /**
     * 根据设备deviceId查询设备信息信息
     * @param deviceId
     * @return
     */
    WbmpAtmpBasicInfoDO findByDeviceId(@Param("deviceId")String deviceId);

    /**
     * 自助设备按近一月查询交易趋势
     * @param termNo 终端编号
     * @return
     */
    List<DeviceTradeTrendVo>deviceMonthTradeList(@Param("orgId")String orgId,@Param("termNo")String termNo);

    /**
     * 自助设备查询近一年交易趋势
     * @param termNo 终端编号
     * @return
     */
   List<DeviceTradeTrendVo>deviceYearTradeList(@Param("orgId")String orgId,@Param("termNo")String termNo);

}
