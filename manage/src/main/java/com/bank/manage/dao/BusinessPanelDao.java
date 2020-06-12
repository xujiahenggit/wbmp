package com.bank.manage.dao;

import com.bank.manage.vo.AbsTellerInfo;
import com.bank.manage.vo.TellerOnlineInfo;
import com.bank.manage.vo.TransCntInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

    List<AbsTellerInfo> tellertPageList(IPage page,@Param("orgId") String orgId);
    List<Map<String, Object>> queryOperation(@Param("branch") String branch, @Param("nowStr") String nowStr);
}
