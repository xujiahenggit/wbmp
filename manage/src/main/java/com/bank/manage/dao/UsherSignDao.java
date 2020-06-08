package com.bank.manage.dao;

import com.bank.manage.dos.UsherSignDO;
import com.bank.manage.dos.WorkSuppleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * ClassName: UsherSignDao
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/23 15:07:45
 */
public interface UsherSignDao extends BaseMapper<UsherSignDO> {

    /**
     *查询当天引导员打卡信息
     * @param dateNowStr
     * @return
     */
    UsherSignDO selInfoByNow(@Param("dateNowStr") String dateNowStr,@Param("usherId") String usherId);

    /**
     * 查询月考勤信息
     * @param usherId
     * @param month
     * @return
     */
    List<UsherSignDO> selInfoByMonth(@Param("usherId") String usherId, @Param("month")String month);

    List<UsherSignDO> selInfoByMonthAndOff(@Param("usherId")String usherId, @Param("month")String month);

    List<UsherSignDO> selInfoByMonthAndOffIsNull(@Param("usherId")String usherId, @Param("month")String month);

    List<WorkSuppleDO> queryWorkSupple(@Param("usherId")String usherId,@Param("month") String month);

    List<UsherSignDO> selectDeckDays(@Param("usherId")String usherId, @Param("month")String month);
}
