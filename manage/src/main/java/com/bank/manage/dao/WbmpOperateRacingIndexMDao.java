package com.bank.manage.dao;

import com.bank.manage.dos.WbmpOperateRacingIndexMDO;
import com.bank.manage.dto.HouseRaceItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/22 19:58
 */
public interface WbmpOperateRacingIndexMDao extends BaseMapper<WbmpOperateRacingIndexMDO> {
    /**
     * 获取赛马制 名称
     * @param orgId 机构号
     * @param date 日期
     * @return
     */
    List<HouseRaceItem> getItemNames(@Param(value = "orgId") String orgId,@Param(value = "date") String date);

    /**
     * 获取具体的数值
     * @param orgId 机构号
     * @param date 日期
     * @param id 赛马制编号
     * @return
     */
    float getData(@Param(value = "orgId") String orgId,@Param(value = "date") String date,@Param(value = "id") String id);

    /**
     * 定时任务用
     * 获取赛马制列表
     * @param orgId  机构号
     * @param date 日期
     * @return
     */
    List<WbmpOperateRacingIndexMDO> getRacingList(@Param(value = "orgId") String orgId,@Param(value = "date") String date);
}
