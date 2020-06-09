package com.bank.manage.dao;

import com.bank.manage.vo.TellerOnlineInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 运营看板统计 Mapper 接口
 *
 * @author zhaozhongyuan
 * @since 2020-06-10
 */
public interface BusinessPanelDao {

    List<TellerOnlineInfo> getTellerOnlineInfo(@Param("tellerId") String tellerId);

}
