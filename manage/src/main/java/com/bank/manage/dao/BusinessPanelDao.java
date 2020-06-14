package com.bank.manage.dao;

import com.bank.manage.vo.AbsTellerInfo;
import com.bank.manage.vo.TellerOnlineInfo;
import com.bank.manage.vo.TransCntInfo;
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

    Map<String, Integer> deviceTotal(@Param("orgId")String orgId);

    List<Map<String, Integer>> deviceStatus(@Param("orgId")String orgId);

    List<TransCntInfo> devicetransCnfTop5(@Param("orgId")String orgId);

    int devicetransCnfSum(@Param("orgId")String orgId);

    List<TransCntInfo> tradeVolumeTop5(@Param("orgId")String orgId);

    Number tradeVolumeSum(@Param("orgId")String orgId);

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
    Number tradeSum(String orgId);

    /**
     * 交易量排名
     * @param orgId，为null全行排名
     * @param tellerId
     * @return 该柜员是第几名
     */
    Integer tradeNumRank(@Param("orgId")String orgId,@Param("tellerId") String tellerId);

    Number onlineTimeSum(String orgId);

    Integer onlineTimeRank(String orgId, String tellerId);
}
