package com.bank.manage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 运营看板数据清理DAO
 */
@Repository
public interface WbmpDataClearDao {

    /**
     * 步骤一：备份
     * 备份wbmp_abs_transinfo上日的数据 到 wbmp_abs_transinfo_h表中
     * @param date【上一日的日期】
     * @return
     */
    int bakWbmpAbsTransinfo(@Param(value = "date") String date);

    /**
     * 步骤二：删除原表数据
     * 删除wbmp_abs_transinfo表中上一日的数据
     * @param date
     * @return
     */
    int deleteWbmpAbsTransinfo(@Param(value = "date") String date);

    /**
     * 步骤三：删除历史表数据
     * 删除wbmp_abs_transinfo_h表中小于前三天的数据
     * @param date
     * @return
     */
    int deleteWbmpAbsTransinfoH(@Param(value = "date") String date);


    /**
     * 柜员在线时长明细表清理策略【删除3天前的数据】
     * @param date
     * @return
     */
    int deleteWbmpAbsTellerOnlineTime(@Param(value = "date") String date);



}
