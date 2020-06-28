package com.bank.manage.service.impl;

import com.bank.core.sysConst.WbmpConstFile;
import com.bank.core.utils.DateUtils;
import com.bank.manage.dao.WbmpOperateRacingIndexMDao;
import com.bank.manage.dos.WbmpOperateRacingIndexMDO;
import com.bank.manage.dto.HouseRaceCharts;
import com.bank.manage.dto.HouseRaceDto;
import com.bank.manage.dto.HouseRaceItem;
import com.bank.manage.service.WbmpOperateRacingIndexMService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/22 19:59
 */
@Service
public class WbmpOperateRacingIndexMServiceImpl extends ServiceImpl<WbmpOperateRacingIndexMDao, WbmpOperateRacingIndexMDO> implements WbmpOperateRacingIndexMService {

    @Resource
    private WbmpOperateRacingIndexMDao wbmpOperateRacingIndexMDao;

    /**
     * 查询赛马制
     * @param orgId 机构号
     * @return
     */
    @Override
    public HouseRaceDto racingAssessIndex(String orgId) {
        HouseRaceDto houseRaceDto=new HouseRaceDto();
        HouseRaceCharts houseRaceCharts=new HouseRaceCharts();
        String date= LocalDate.now().minusDays(1).toString().substring(0, 7);
        List<HouseRaceItem> listItem=wbmpOperateRacingIndexMDao.getItemNames(orgId,date);
        List<HouseRaceItem> sortItem=new ArrayList<>();
        sortItem.addAll(listItem);
        for (HouseRaceItem item:listItem){
            if(item.getId().equals(WbmpConstFile.SMZ_RACING_008)){
                sortItem.set(0,item);
            }
            if(item.getId().equals(WbmpConstFile.SMZ_RACING_005)){
                sortItem.set(1,item);
            }
            if(item.getId().equals(WbmpConstFile.SMZ_RACING_004)){
                sortItem.set(2,item);
            }
            if(item.getId().equals(WbmpConstFile.SMZ_RACING_006)){
                sortItem.set(3,item);
            }
            if(item.getId().equals(WbmpConstFile.SMZ_RACING_007)){
                sortItem.set(4,item);
            }
            if(item.getId().equals(WbmpConstFile.SMZ_RACING_002)){
                sortItem.set(5,item);
            }
            if(item.getId().equals(WbmpConstFile.SMZ_RACING_003)){
                sortItem.set(6,item);
            }
            if(item.getId().equals(WbmpConstFile.SMZ_RACING_001)){
                sortItem.set(7,item);
            }
        }
        List<Float> data=new ArrayList<>();
        if(listItem.size()>0){
            for(HouseRaceItem item:sortItem){

                float dataNumber=wbmpOperateRacingIndexMDao.getData(orgId,date,item.getId());
                data.add(dataNumber);

            }
        }
        //按顺序 排序
        houseRaceCharts.setIndicator(sortItem);
        houseRaceCharts.setData(data);
        houseRaceDto.setCharts(houseRaceCharts);
        return houseRaceDto;
    }

    /**
     * 定时任务计算用
     * 查询赛马制
     * @param orgId 机构编号
     * @param date 日期
     * @return
     */
    @Override
    public List<WbmpOperateRacingIndexMDO> getRacingList(String orgId, String date) {
        return wbmpOperateRacingIndexMDao.getRacingList(orgId,date);
    }
}
