package com.bank.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.bank.core.utils.DateUtils;
import com.bank.manage.dao.BusinessPanelDao;
import com.bank.manage.service.BusinessPanelService;
import com.bank.manage.vo.AbsTellerInfo;
import com.bank.manage.vo.RankInfo;
import com.bank.manage.vo.TellerOnlineInfo;
import com.bank.manage.vo.TransCntInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 运营看板统计 服务实现类
 *
 * @author zhaozhongyuan
 * @since 2020-06-10
 */
@Service
public class BusinessPanelServiceImpl implements BusinessPanelService {

    @Resource
    BusinessPanelDao businessPanelDao;

    @Override
    public List<TellerOnlineInfo> getTellerOnlineInfo() {
        return businessPanelDao.getTellerOnlineInfo(null);
    }

    @Override
    public TellerOnlineInfo getTellerOnlineInfo(String tellerId) {
        List<TellerOnlineInfo> tellerOnlineInfo = businessPanelDao.getTellerOnlineInfo(tellerId);
        if (tellerOnlineInfo.size() == 1) {
            return tellerOnlineInfo.get(0);
        }
        return null;
    }

    @Override
    public Map<String, Integer> deviceTotal(String orgId) {
        return businessPanelDao.deviceTotal(orgId);
    }

    @Override
    public List<Map<String, Integer>> deviceStatus(String orgId) {
        return businessPanelDao.deviceStatus(orgId);
    }

    @Override
    public List<TransCntInfo> devicetransCnfTop5(String orgId) {
        List<TransCntInfo> list = businessPanelDao.devicetransCnfTop5(orgId);
        Number sum = businessPanelDao.devicetransCnfSum(orgId);
        list.stream().forEach(e -> e.setPercent(NumberUtil.div(e.getSum(), sum, 4)));
        return list;
    }

    @Override
    public List<TransCntInfo> tradeVolumeTop5(String orgId) {
        List<TransCntInfo> list = businessPanelDao.tradeVolumeTop5(orgId);
        Number sum = businessPanelDao.tradeVolumeSum(orgId);
        list.stream().forEach(e -> e.setPercent(NumberUtil.div(e.getSum(), sum, 4)));
        return list;
    }

    @Override
    public List<AbsTellerInfo> tellertPageList(String orgId) {
//        Page<AbsTellerInfo> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
//        page.setRecords(businessPanelDao.tellertPageList(page, orgId));
        return businessPanelDao.tellertPageList(orgId)
                .stream()
                .map(e -> {
                    e.setOnLineTimeStr(DateUtils.formatSeconds(e.getOnLineTime()));
                    return e;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> queryOperation(String branch) {
        String nowStr = DateUtil.format(LocalDateTime.now(), "yyyy-MM-dd");
        List<Map<String, Object>> operationMap = businessPanelDao.queryOperation(branch, nowStr);
        List<Map<String, Object>> list = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(operationMap)) {
            for (Map<String, Object> map : operationMap) {
                Long take_cnt = (Long) map.get("take_cnt");//取号总数
                Long queue_cnt = (Long) map.get("queue_cnt");//排队总数
                Long already_handle_cnt = (Long) map.get("already_handle_cnt");//已办理总数
                Long progress_handle_cnt = (Long) map.get("progress_handle_cnt");//办理中总数
                Long abandoned_cnt = (Long) map.get("abandoned_cnt");//弃号总数
                BigDecimal abandoned_lv = (BigDecimal) map.get("abandoned_lv");//弃号率
                Long num = (Long) map.get("num");//窗口数
                /**
                 * 排队预计等待时长=（等待人数/窗口数）*历史平均等待时长*历史1个月平均弃号率
                 */
                //TODO 历史平均等待时长  历史1个月平均弃号率 未定义
                Double wait_time = (queue_cnt / num) * 40 * 0.01;
                /*DecimalFormat df = new DecimalFormat("0.00%");
                String format = df.format(abandoned_lv);*/
                Map<String, Object> panelMap = new HashMap<>();
                panelMap.put("queue_cnt", queue_cnt);
                panelMap.put("wait_time", wait_time);
                panelMap.put("abandoned_lv", abandoned_lv);
                list.add(panelMap);

                Map<String, Object> bargraphMap = new HashMap<>();
                bargraphMap.put("take_cnt", take_cnt);
                bargraphMap.put("queue_cnt", queue_cnt);
                bargraphMap.put("progress_handle_cnt", progress_handle_cnt);
                bargraphMap.put("already_handle_cnt", already_handle_cnt);
                bargraphMap.put("abandoned_cnt", abandoned_cnt);
                list.add(bargraphMap);
            }
        }
        return list;
    }

    @Override
    public RankInfo rankInfo(String orgId, String tellerId) {
        Integer onlineSum = businessPanelDao.onlineSum(orgId);//在线人数
        Number tradeSum = businessPanelDao.tradeSum(orgId);
        Number onlineTimeSum = businessPanelDao.onlineTimeSum(orgId);//总在线时长,秒钟
        Number onlineTimeSumMinute = NumberUtil.div(onlineTimeSum, 60, 0);//总在线时长,转为分钟
        return RankInfo
                .builder()
                .tellerId(tellerId)
                .onlineSum(onlineSum)
                .tradeNumAverage(NumberUtil.div(tradeSum, onlineSum, 0))
                .tradeNumRank(businessPanelDao.tradeNumRank(orgId, tellerId))
                .onlineTimeAverage(NumberUtil.div(onlineTimeSumMinute, onlineSum, 0))//计算均值
                .onlineTimeRank(businessPanelDao.onlineTimeRank(orgId, tellerId))
                .build();
    }


}
