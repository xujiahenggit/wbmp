package com.bank.manage.service;

import com.bank.manage.dto.DeductDTO;
import com.bank.manage.vo.HappyParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 运营看板数据清理Service
 */
@Service
public interface WbmpDataClearService {

    /**
     * 步骤一：备份
     * 备份wbmp_abs_transinfo上日的数据 到 wbmp_abs_transinfo_h表中
     * @param date【上一日的日期】
     * @return
     */
    Boolean bakWbmpAbsTransinfo(@Param(value = "date") String date);

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

    /**
     * 删除3天前的数据【柜员交易耗时表】
     * @param date
     * @return
     */
    int deleteWbmpAbsTradeTime(@Param(value = "date") String date);


    /**
     * 备份wbmp_abs_transinfo  date-上一日的日期【自助设备交易表1】
     * @param date
     * @return
     */
    int bakWbmpAtmpTranInfo(@Param(value = "date") String date);

    /**
     * 删除上一日的数据 date-上一日的日期 【自助设备交易表2】
     * @param date
     * @return
     */
    int deleteWbmpAtmpTranInfo(@Param(value = "date") String date);

    /**
     * 删除历史表小于一年前的数据 date-上一年的数据 【自助设备交易表3】
     * @param date
     * @return
     */
    int deleteWbmpAtmpTranInfoH(@Param(value = "date") String date);

    /**
     * 删除上一日的数据 date-上一日的日期 【排队机】
     * @param date
     * @return
     */
    int deleteWbmpBqmsQueue(@Param(value = "date") String date);

    /**
     * 删除三个月前的数据 date-三月前的日期 【客户满意度参数表T+1】
     * @param date
     * @return
     */
    int deleteWbmpOperateBqmsQueueAvg(@Param(value = "date") String date);

    /**
     * 删除上个月的数据 date-上个月的日期 【网点AUM表T+1】
     * @param date
     * @return
     */
    int deleteWbmpOperateIndexAum(@Param(value = "date") String date);

    /**
     * 删除三个月前的数据 date-三月前的日期 【赛马制表T+1】
     * @param date
     * @return
     */
    int deleteWbmpOperateRacingIndexM(@Param(value = "date") String date);

    /**
     * 删除三天前的数据 date-三天前的日期 【网点实时余额】
     * @param date
     * @return
     */
    int deleteWbmpOrgBalance(@Param(value = "date") String date);

    /**
     * 删除一个月前的数据 date-一月前的日期 【网点历史离线余额】
     * @param date
     * @return
     */
    int deleteWbmpOrgBatchBalance(@Param(value = "date") String date);

    /**
     * 删除一个月前的数据 date-一月前的日期 【汇率表】
     * @param date
     * @return
     */
    int deleteWbmpOrgCurrency(@Param(value = "date") String date);

    /**
     * 备份wbmp_operate_cust  date-上一月的日期【客群指标表 T+1】1
     * @param date
     * @return
     */
    int bakWbmpOperateCust(@Param(value = "date") String date);

    /**
     * 删除wbmp_operate_cust  date-上一月的日期【客群指标表 T+1】2
     * @param date
     * @return
     */
    int deleteWbmpOperateCust(@Param(value = "date") String date);

    /**
     * 删除wbmp_operate_cust_h  date-三年前的日期【客群指标表 T+1】3
     * @param date
     * @return
     */
    int deleteWbmpOperateCustH(@Param(value = "date") String date);

}
