package com.bank.manage.service;

import com.bank.manage.dos.WbmpMangementScoreDO;
import com.bank.manage.dos.WbmpOperateScoreDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/19 9:36
 */
public interface WbmpOperateScoreService extends IService<WbmpOperateScoreDO> {

    /**
     * 查询经营分数
     * @param orgId 机构号
     * @param times 时间list
     * @param queryType 查询的条件【年-02，季-03，月-01】
     * @return
     */
    List<String> calcOperateScore(String orgId, List<String>times, String queryType);
    /**
     * 保存综合得分
     * @param listManagement 经营得分数据
     * @param listOperate 运营得分数据
     * @param date 日期
     */
    void saveScore(List<WbmpMangementScoreDO> listManagement, List<WbmpOperateScoreDO> listOperate,String date);

    /**
     * 计算运营得分情况
     * @param orgId 机构编号
     * @param date 时间
     * @return
     */
    float calOperScore(String orgId, String date);
}
