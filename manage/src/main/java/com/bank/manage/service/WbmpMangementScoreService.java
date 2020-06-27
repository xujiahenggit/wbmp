package com.bank.manage.service;

import com.bank.manage.dos.WbmpMangementScoreDO;
import com.bank.manage.vo.OperateRankVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 运营分数
 * @Author: Andy
 * @Date: 2020/6/19 9:36
 */
public interface WbmpMangementScoreService extends IService<WbmpMangementScoreDO> {
    /**
     * 查询红榜 top5
     * @return
     */
    List<OperateRankVO> findRedTop();

    /**
     * 查询灰榜 top5
     * @return
     */
    List<OperateRankVO> findGreyTop();

    /**
     * 查询运营分数
     */
    List<Float> calcManageScore(String orgId,List<String>times,String queryType);

}
