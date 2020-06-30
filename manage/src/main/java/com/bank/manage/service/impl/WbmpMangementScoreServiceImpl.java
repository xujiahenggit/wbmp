package com.bank.manage.service.impl;

import com.bank.core.sysConst.WbmpConstFile;
import com.bank.manage.dao.WbmpMangementScoreDao;
import com.bank.manage.dos.WbmpMangementScoreDO;
import com.bank.manage.service.WbmpMangementScoreService;
import com.bank.manage.vo.OperateRankVO;
import com.bank.manage.vo.OrgScoreVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<String> calcManageScore(String orgId, List<String> times, String queryType) {
        List<String> scores = new ArrayList<String>();

        List<OrgScoreVo> queryResult = new ArrayList<OrgScoreVo>();
        if(WbmpConstFile.DATE_TYPE_YEAR.equals(queryType)){

            queryResult = wbmpMangementScoreDao.queryByYear(orgId);

        }else if(WbmpConstFile.DATE_TYPE_JIDU.equals(queryType)){

            queryResult = wbmpMangementScoreDao.queryByQuart(orgId);

        }else if(WbmpConstFile.DATE_TYPE_MONTH.equals(queryType)){

            queryResult =wbmpMangementScoreDao.queryByMonth(orgId);
        }

        //更加x轴的查询条件筛选记录，如果未找到记录，则记录赋值为0
        for (String str:times){
            String scoreStr = "0";
           for(OrgScoreVo scoreVo:queryResult){
                if(str.equals(scoreVo.getDateDt())){
                    scoreStr = scoreVo.getScore();
                }
           }
            scores.add(scoreStr);
        }
        return scores;
    }
}
