package com.bank.manage.service;

import com.bank.manage.dos.WbmpOperateRacingIndexMDO;
import com.bank.manage.dto.HouseRaceDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/22 19:58
 */
public interface WbmpOperateRacingIndexMService extends IService<WbmpOperateRacingIndexMDO> {
    /**
     * 获取赛马制数据
     * @param orgId 机构号
     * @return
     */
    HouseRaceDto racingAssessIndex(String orgId);

    /**
     * 定时任务计算用
     * 查询赛马制
     * @param orgId 机构编号
     * @param date 日期
     * @return
     */
    List<WbmpOperateRacingIndexMDO> getRacingList(String orgId, String date);
}
