package com.bank.manage.service.impl;

import com.bank.core.sysConst.WbmpConstFile;
import com.bank.core.utils.DateUtils;
import com.bank.manage.dao.WbmpOperateRacingIndexMDao;
import com.bank.manage.dos.WbmpOperateRacingIndexMDO;
import com.bank.manage.dto.HouseRaceCharts;
import com.bank.manage.dto.HouseRaceDto;
import com.bank.manage.dto.HouseRaceItem;
import com.bank.manage.service.WbmpOperateRacingIndexMService;
import com.bank.manage.util.Tools;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String date= LocalDate.now().minusMonths(1).toString().substring(0, 7);
        List<HouseRaceItem> sortItem=new ArrayList<>();
        HouseRaceItem houseRaceItem_008=new HouseRaceItem();
        houseRaceItem_008.setId(WbmpConstFile.SMZ_RACING_008);
        houseRaceItem_008.setMax("100");
        houseRaceItem_008.setName("个人及企业客户线上开通率");
        sortItem.add(houseRaceItem_008);

        HouseRaceItem houseRaceItem_005=new HouseRaceItem();
        houseRaceItem_005.setId(WbmpConstFile.SMZ_RACING_005);
        houseRaceItem_005.setMax("100");
        houseRaceItem_005.setName("现金业务分流率");
        sortItem.add(houseRaceItem_005);


        HouseRaceItem houseRaceItem_004=new HouseRaceItem();
        houseRaceItem_004.setId(WbmpConstFile.SMZ_RACING_004);
        houseRaceItem_004.setMax("100");
        houseRaceItem_004.setName("电子对账率");
        sortItem.add(houseRaceItem_004);


        HouseRaceItem houseRaceItem_006=new HouseRaceItem();
        houseRaceItem_006.setId(WbmpConstFile.SMZ_RACING_006);
        houseRaceItem_006.setMax("100");
        houseRaceItem_006.setName("分担率");
        sortItem.add(houseRaceItem_006);

        HouseRaceItem houseRaceItem_007=new HouseRaceItem();
        houseRaceItem_007.setId(WbmpConstFile.SMZ_RACING_007);
        houseRaceItem_007.setMax("100");
        houseRaceItem_007.setName("对公账户线上开户率");
        sortItem.add(houseRaceItem_007);


        HouseRaceItem houseRaceItem_002=new HouseRaceItem();
        houseRaceItem_002.setId(WbmpConstFile.SMZ_RACING_002);
        houseRaceItem_002.setMax("100");
        houseRaceItem_002.setName("自助设备可用率");
        sortItem.add(houseRaceItem_002);


        HouseRaceItem houseRaceItem_003=new HouseRaceItem();
        houseRaceItem_003.setId(WbmpConstFile.SMZ_RACING_003);
        houseRaceItem_003.setMax("100");
        houseRaceItem_003.setName("银企对账率");
        sortItem.add(houseRaceItem_003);

        //2020-07-20 取消无纸化率 改为【完成无纸化率】
        HouseRaceItem houseRaceItem_001=new HouseRaceItem();
        houseRaceItem_001.setId(WbmpConstFile.SMZ_RACING_001);
        houseRaceItem_001.setMax("100");
        houseRaceItem_001.setName("完成无纸化率");
        sortItem.add(houseRaceItem_001);
        //机构正常的赛马值数据
        List<Float> data=new ArrayList<>();
        //全行月度最大的赛马制数据
        List<Float> maxData=new ArrayList<>();

        //结果数据
        List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();

        if(sortItem.size()>0){
            for(HouseRaceItem item:sortItem){
                float dataNumber= Tools.formatFloat(wbmpOperateRacingIndexMDao.getData(orgId,date,item.getId()));

                float maxDateNum = Tools.formatFloat(wbmpOperateRacingIndexMDao.getMouthMaxData(date,item.getId()));
                if(item.getId().equals("RACING_004")){
                    //电子对账率 源系统已经乘以了100
                    dataNumber = dataNumber/100;
                    maxDateNum = maxDateNum/100;
                }else if(item.getId().equals("RACING_001")){
                    //完成无纸化比率 = 1-取消无纸化比率
                    dataNumber = 100 - dataNumber;
                    maxDateNum = 100 -Tools.formatFloat(wbmpOperateRacingIndexMDao.getMouthMinData(date,item.getId()));
                }
                data.add(dataNumber);
                maxData.add(maxDateNum);
            }
        }
        Map<String,Object> datemap = new HashMap<String,Object>();
        datemap.put("value",data);
        Map<String,Object> maxMap = new HashMap<String,Object>();
        maxMap.put("value",maxData);
        list.add(datemap);
        list.add(maxMap);
        //按顺序 排序
        houseRaceCharts.setIndicator(sortItem);
        houseRaceCharts.setData(list);
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
