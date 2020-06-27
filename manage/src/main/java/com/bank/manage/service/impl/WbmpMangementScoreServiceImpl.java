package com.bank.manage.service.impl;

import com.bank.core.sysConst.WbmpConstFile;
import com.bank.manage.dao.WbmpMangementScoreDao;
import com.bank.manage.dos.WbmpMangementScoreDO;
import com.bank.manage.service.WbmpMangementScoreService;
import com.bank.manage.vo.OperateRankVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/19 9:38
 */
@Service
public class WbmpMangementScoreServiceImpl extends ServiceImpl<WbmpMangementScoreDao,WbmpMangementScoreDO> implements WbmpMangementScoreService {


    @Resource
    private WbmpMangementScoreDao wbmpMangementScoreDao;

    /**
     * 查询运营红榜top5
     * @return
     */
    @Override
    public List<OperateRankVO> findRedTop() {
        String now= LocalDate.now().minusDays(1).toString();
        return wbmpMangementScoreDao.findRedTop(now);
    }

    /**
     * 查询运营灰榜top5
     * @return
     */
    @Override
    public List<OperateRankVO> findGreyTop() {
        String now=LocalDate.now().minusDays(1).toString();
        return wbmpMangementScoreDao.findGreyTop(now);
    }

    @Override
    public List<Float> calcManageScore(String orgId, List<String> times, String queryType) {
        List<Float> scores = new ArrayList<Float>();
        for (String str:times){
            if(WbmpConstFile.DATE_TYPE_YEAR.equals(queryType)){
                Float score = wbmpMangementScoreDao.findYearOrgScore(orgId,str);
                scores.add(score);
            }else{
                Float score = wbmpMangementScoreDao.findOrgMouthScore(orgId,str);
                scores.add(score);
            }
        }
        return scores;
    }
}
