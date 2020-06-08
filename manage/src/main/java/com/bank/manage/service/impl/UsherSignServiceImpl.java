package com.bank.manage.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.hutool.core.collection.CollectionUtil;
import com.bank.core.utils.DateUtils;
import com.bank.manage.dao.CardSuppleDao;
import com.bank.manage.dao.UsherWorkDaysDao;
import com.bank.manage.dos.UsherWorkDaysDO;
import com.bank.manage.dos.WorkSuppleDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.core.entity.BizException;
import com.bank.core.utils.PropertyUtil;
import com.bank.manage.dao.UsherSignDao;
import com.bank.manage.dao.UsherSignLogDao;
import com.bank.manage.dos.UsherSignDO;
import com.bank.manage.dos.UsherSignLogDO;
import com.bank.manage.dto.UsherSignDTO;
import com.bank.manage.service.UsherSignService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 引导员签到业务处理
 * ClassName: UsherSignServiceImpl
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/23 15:36:03
 */
@Service
@Slf4j
public class UsherSignServiceImpl implements UsherSignService {

    @Autowired
    private UsherSignDao usherSignDao;

    @Autowired
    private UsherSignLogDao usherSignLogDao;

    @Autowired
    private UsherWorkDaysDao usherWorkDaysDao;

    @Autowired
    private CardSuppleDao cardSuppleDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean onDutySign(UsherSignDTO usherSignDTO) {
        try {
        //校验签到日期是否与当前日期一致
        String signDate = usherSignDTO.getSignDate();
        String currentDate = DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN);
        if (!StringUtils.equals(signDate, currentDate)) {
            throw new BizException("引导员签到日期[" + signDate + "]与服务器不匹配，上班签到操作失败");
        }

        QueryWrapper<UsherSignDO> queryWrapper = new QueryWrapper<>();
        //根据引导员ID和签到日期进行查询
        queryWrapper.eq("USHER_ID", usherSignDTO.getUsherId()).eq("SIGN_DATE", usherSignDTO.getSignDate());
        if (this.usherSignDao.selectCount(queryWrapper) > 0) {
            throw new BizException("当前日期[" + signDate + "]引导员已进行上班签到，不允许重复进行上班签到操作");
        }

        UsherSignDO usherSignDO = new UsherSignDO();
        PropertyUtil.copyProperties(usherSignDTO, usherSignDO, "usherSignId", "signDate");
        usherSignDO.setSignDate(DateUtil.parse(signDate, DatePattern.NORM_DATE_PATTERN));
        usherSignDO.setSignStatus("0");
        //上班签时间
        usherSignDO.setOnDutyTime(new Date());
        this.usherSignDao.insert(usherSignDO);
        return true;
        } catch (Exception e) {
            log.error("上班签到失败：{}",e.getMessage());
            throw new BizException("上班签到失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean offDutySign(UsherSignDTO usherSignDTO) {
        try {
            String signDate = usherSignDTO.getSignDate();
            QueryWrapper<UsherSignDO> queryWrapper = new QueryWrapper<>();
            //根据引导员ID和签到日期进行查询
            queryWrapper.eq("USHER_ID", usherSignDTO.getUsherId()).eq("SIGN_DATE", usherSignDTO.getSignDate());

            UsherSignDO usherSignDO = this.usherSignDao.selectOne(queryWrapper);
            if (usherSignDO == null) {
                throw new BizException("当前日期[" + signDate + "]引导员未进行上班签到，不允许进行下班签到操作");
            }

            //计算今日工时
            Date onDutyTime = usherSignDO.getOnDutyTime();
            BigDecimal workingHours = new BigDecimal(new Date().getTime() - onDutyTime.getTime())
                    .divide(new BigDecimal(DateUnit.HOUR.getMillis()), 2, BigDecimal.ROUND_HALF_UP);

            //long workHours = DateUtil.between(new Date(), onDutyTime, DateUnit.HOUR, false);
            usherSignDO.setWorkingHours(workingHours);
            //下班签时间
            usherSignDO.setOffDutyTime(new Date());
            this.usherSignDao.updateById(usherSignDO);

            //多次下班签日志
            UsherSignLogDO usherSignLogDO = new UsherSignLogDO();
            PropertyUtil.copyProperties(usherSignDO, usherSignLogDO);
            this.usherSignLogDao.insert(usherSignLogDO);
            return true;
        } catch (BizException e) {
            log.error("下班签退失败：{}",e.getMessage());
            throw new BizException("下班签退失败");
        }
    }

    /**
     * 平均工时：当月所有上班打卡到下班打卡时间段/上班天数，不考虑午休时间。（展示样式：2020-02-02（星期二）  7.6小时）
     * 应出勤天数：根据维护数据或默认显示的工作日天数，优先展示维护的数据。
     * 出勤天数：含打卡的天数。包含缺卡的日期（展示样式：2020-02-02（星期二））
     * 休息天数：未打卡（上班、下班卡都没有-考勤数据不存在数据库）的天数。（补卡申请通过后移除，天数增加到出勤天数）
     * 缺卡天数：缺卡的天数、未打卡的天数（补卡申请通过后移除），点击去处理需进行校验判断当前日期有没有审批中的补卡，若有则进行提示：“当前日期已提交补卡申请”。
     * 加班：申请加班显示的时间
     * @param usherId
     * @param month
     * @return
     */
    @Override
    public Map<String, Object> queryInformation(String usherId, String month) {
        Map<String,Object> map = new HashMap<>();

        List<Map<String, Object>> workDays = workDays(usherId, month);//引导员应出勤天数
        map.put("usherWorkDays",workDays);
        List<Map<String, Object>> avgWorkHour = avgWorkHour(usherId, month);//计算平均工时
        map.put("avgWorkHour",avgWorkHour);
        List<Map<String, Object>> requiredDays = requiredDays(usherId, month);//计算出勤天数
        if(CollectionUtil.isNotEmpty(requiredDays)){
            map.put("requiredDays",requiredDays);
        }
        List<Map<String, Object>> Sign = querySign(usherId);//当天引导员打卡信息
        if(CollectionUtil.isNotEmpty(Sign)){
            map.put("sign",Sign);
        }
        List<Map<String, Object>> rest = restDay(usherId, month);//计算休息天数
        if(CollectionUtil.isNotEmpty(rest)){
            map.put("restDay",rest);
        }
        List<Map<String, Object>> deckDay = deckDays(usherId, month);//查询缺卡天数
        map.put("deckDay",deckDay);
        List<Map<String, Object>> hardWork = hardWork(usherId, month);//加班时长
        map.put("hardWork",hardWork);
        //查询引导员月考勤信息
        List<UsherSignDO> usherSignList =  usherSignDao.selInfoByMonth(usherId,month);//签到盘日历
        if(CollectionUtil.isNotEmpty(usherSignList)){
            map.put("usherSign",usherSignList);
        }
        return map;
    }

    //加班
    public List<Map<String,Object>> hardWork(String usherId, String month){
        List<WorkSuppleDO> workSuppleDOList = usherSignDao.queryWorkSupple(usherId,month);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> workMap = new HashMap<>();
        List mapList = new ArrayList();
        BigDecimal total=new BigDecimal("0.00");
        for (WorkSuppleDO workSuppleDO : workSuppleDOList) {
            BigDecimal workSuppleLength = workSuppleDO.getWorkSuppleLength();
            total = total.add(workSuppleLength);

            Map<String,Object> map = new HashMap<>();
            String s = DateUtil.format(DateUtils.localDate2Date(workSuppleDO.getWorkSuppleDate()), DatePattern.NORM_DATE_PATTERN);
            map.put("workSuppleDate",s+"("+ DateUtils.dateToWeek(s) +")");
            map.put("workSuppleType",workSuppleDO.getWorkSuppleType());
            map.put("workSuppleLength",workSuppleDO.getWorkSuppleLength());
            mapList.add(map);
        }

        workMap.put("total",total);
        if(CollectionUtil.isNotEmpty(mapList)){
            workMap.put("hardWork",mapList);
        }
        list.add(workMap);
        return list;
    }

    //查询缺卡天数
    public List<Map<String,Object>> deckDays(String usherId, String month){
        List<Map<String,Object>> list = new ArrayList<>();
        List<UsherSignDO> usherSignList =  usherSignDao.selInfoByMonthAndOffIsNull(usherId,month);//查询引导员月考勤信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String sdfDate = sdf.format(new Date());
        List<String> workInMonth = null;
        if(month.equals(sdfDate)) {//判断当前月份是否是查询月份
            String nowDate = DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN);//获取当前日期
            workInMonth = DateUtils.getWorkInMonthByday(Integer.parseInt(nowDate.split("-")[0]),
                    Integer.parseInt(nowDate.split("-")[1]), Integer.parseInt(nowDate.split("-")[2]));//获取当前时间之前所有工作日
        }else{
            workInMonth = DateUtils.getWorkInMonth(Integer.parseInt(month.split("-")[0]), Integer.parseInt(month.split("-")[1]));
        }
        List<String> signList = new ArrayList<>();
        for (UsherSignDO usherSignDO : usherSignList) {
            String singDate = DateUtil.format(usherSignDO.getSignDate(), DatePattern.NORM_DATE_PATTERN);
            signList.add(singDate);
        }
        List<String> subtract = (List<String>)CollectionUtil.subtract(workInMonth, signList);//差集 去除正常打卡信息
        List<UsherSignDO> usherDeckSignList =  usherSignDao.selectDeckDays(usherId,month);//查询缺卡信息 只有上午签到信息
        List<Map<String,Object>> signList1 = new ArrayList<>();
        List<String> deckList = new ArrayList<>();//漏卡集合
        if(CollectionUtil.isNotEmpty(usherDeckSignList)){
            for (UsherSignDO usherSignDO : usherDeckSignList) {
                String singDate = DateUtil.format(usherSignDO.getSignDate(), DatePattern.NORM_DATE_PATTERN);
                deckList.add(singDate);
                Map<String,Object> map = new HashMap<>();
                map.put("singDate",singDate+"("+ DateUtils.dateToWeek(singDate) +")");
                map.put("onDutyTime",DateUtil.format(usherSignDO.getOnDutyTime(),DatePattern.NORM_DATETIME_PATTERN));
                signList1.add(map);
            }
        }
        List<String> deckDaysList = (List<String>) CollectionUtil.subtract(subtract, deckList);//差集 去除漏卡
        for (String s : deckDaysList) {
            Map<String,Object> map = new HashMap<>();
            map.put("singDate",s+"("+ DateUtils.dateToWeek(s) +")");
            signList1.add(map);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("total",subtract.size());
        if(CollectionUtil.isNotEmpty(signList1)){
            map.put("signList",signList1);
        }
        list.add(map);
        return list;
    }

    //计算休息天数
    public List<Map<String,Object>> restDay(String usherId, String month){
        List<Map<String,Object>> list = new ArrayList<>();
        //查询引导员月考勤信息
        List<UsherSignDO> usherSignList =  usherSignDao.selInfoByMonthAndOff(usherId,month);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String sdfDate = sdf.format(new Date());
        Map<String,Object> map = new HashMap<>();
        if(month.equals(sdfDate)){//判断查询月份是否是当前月份
            if(CollectionUtil.isNotEmpty(usherSignList)){
                String nowDate = DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN);//获取当前日期
                List<String> daysList = DateUtils.getDayListOfMonth();//获取当月所有日期
                int a = daysList.indexOf(nowDate);
                List<String> sub = CollectionUtil.sub(daysList, 0, a);//截取后的集合 不包含i
                sub.add(daysList.get(a));

                List<String> singDateList = new ArrayList();
                for (UsherSignDO signDO : usherSignList) {
                    String singDate = DateUtil.format(signDO.getSignDate(), DatePattern.NORM_DATE_PATTERN);
                    singDateList.add(singDate);//获取所有签到的日期
                }
                List<String> subtract = (List<String>)CollectionUtil.subtract(sub, singDateList);
                List<String> restDaysList = new ArrayList<>();
                for (int i = 0; i < subtract.size(); i++) {
                    String s = subtract.get(i);
                    restDaysList.add(s+"("+ DateUtils.dateToWeek(s) +")");
                }
                map.put("restTotal",subtract.size());
                map.put("restDay",restDaysList);
            }
        }else{
            if(CollectionUtil.isNotEmpty(usherSignList)){
                List<String> singDateList = new ArrayList();
                for (UsherSignDO signDO : usherSignList) {
                    String singDate = DateUtil.format(signDO.getSignDate(), DatePattern.NORM_DATE_PATTERN);
                    singDateList.add(singDate);//获取所有签到的日期
                }
                List<String> dayListOfMonth = DateUtils.geDaysInMonth(Integer.parseInt(month.split("-")[0]),Integer.parseInt(month.split("-")[1]));//获取当月所有日期
                List<String> subtract = (List<String>)CollectionUtil.subtract(dayListOfMonth, singDateList);
                List<String> restDaysList = new ArrayList<>();
                for (int i = 0; i < subtract.size(); i++) {
                    String s = subtract.get(i);
                    restDaysList.add(s+"("+ DateUtils.dateToWeek(s) +")");
                }
                map.put("restTotal",subtract.size());
                map.put("restDay",restDaysList);
            }
        }
        if(CollectionUtil.isNotEmpty(map)){
            list.add(map);
        }
        return list;
    }

    //计算应出勤天数
    public List<Map<String,Object>> workDays(String usherId, String month){
        List<Map<String,Object>> list = new ArrayList<>();
        /**查询引导员应出勤天数**/
        QueryWrapper<UsherWorkDaysDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USHER_ID",usherId);
        queryWrapper.eq("WORK_YEAR_MONTH",month);
        UsherWorkDaysDO usherWorkDaysDO = usherWorkDaysDao.selectOne(queryWrapper);
        Map<String,Object> workDay = new HashMap<>();
        if(usherWorkDaysDO != null){//不为空直接使用维护的，为空使用当月工作日天数
            workDay.put("workDay",usherWorkDaysDO.getWorkDays());
        }else{
            workDay.put("workDay",DateUtils.getCuruentMonthWorkDays());
        }
        list.add(workDay);
        return list;
    }

    //计算出勤天数
    public List<Map<String,Object>> requiredDays(String usherId, String month){
        List<Map<String,Object>> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //查询引导员月考勤信息
        List<UsherSignDO> usherSignList =  usherSignDao.selInfoByMonth(usherId,month);
        Map<String,Object> rquDaysMap = new HashMap<>();/**出勤天数**/
        if(CollectionUtil.isNotEmpty(usherSignList)){
            List rquDays = new ArrayList();///出勤天数
            usherSignList.forEach(s ->{
                String singDate = DateUtil.format(s.getSignDate(), DatePattern.NORM_DATE_PATTERN);
                String s1 = DateUtils.dateToWeek(singDate);
                rquDays.add(sdf.format(s.getSignDate())+"("+s1+")");
            });
            rquDaysMap.put("rquTotal",usherSignList.size());
            rquDaysMap.put("total",rquDays);
        }
        if(CollectionUtil.isNotEmpty(rquDaysMap)){
            list.add(rquDaysMap);
        }
        return list;
    }

    //计算平均工时
    public List<Map<String,Object>> avgWorkHour(String usherId, String month){
        List<Map<String,Object>> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //查询引导员月考勤信息
        List<UsherSignDO> usherSignList =  usherSignDao.selInfoByMonth(usherId,month);
        Map<String,Object> workHour = new HashMap<>();
        if(CollectionUtil.isNotEmpty(usherSignList)){
            /**平均工时计算**/
            BigDecimal total=new BigDecimal("0.00");
            int a = 0;//正常签到统计
            for (UsherSignDO signDO : usherSignList) {
                if(signDO.getOffDutyTime() != null){
                    BigDecimal workingHours = signDO.getWorkingHours();
                    total = total.add(workingHours);
                    a++;
                }
            }
            if(a != 0){
                BigDecimal divide = total.divide(BigDecimal.valueOf(a), 2);
                workHour.put("WorkHour",divide);//平均工时
            }else{
                workHour.put("WorkHour",a);//平均工时
            }
            List workList = new ArrayList();//平均工时
            usherSignList.forEach(s ->{
                String singDate = DateUtil.format(s.getSignDate(), DatePattern.NORM_DATE_PATTERN);
                String s1 = DateUtils.dateToWeek(singDate);
                if(s.getOffDutyTime() != null){
                    Map<String,Object> workHourList = new HashMap<>();
                    workHourList.put("singDate",sdf.format(s.getSignDate())+"("+s1+")");
                    workHourList.put("workHour",s.getWorkingHours());
                    workList.add(workHourList);
                }
            });
            if(CollectionUtil.isNotEmpty(workList)){
                workHour.put("workList",workList);
            }
        }else{
            workHour.put("WorkHour",0);//平均工时
        }
        list.add(workHour);
        return list;
    }

    //查询当天引导员打卡信息
    public List<Map<String,Object>> querySign(String usherId){
        List<Map<String,Object>> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(new Date());
        UsherSignDO usherSignDO =  usherSignDao.selInfoByNow(dateNowStr,usherId);
        if(usherSignDO != null){
            Map<String,Object> ONOFFSign = new HashMap<>();
            ONOFFSign.put("onSign",usherSignDO.getOnDutyTime());
            ONOFFSign.put("offSign",usherSignDO.getOffDutyTime());
            list.add(ONOFFSign);
        }
        return list;
    }


}
