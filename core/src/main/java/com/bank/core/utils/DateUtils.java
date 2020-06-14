package com.bank.core.utils;

import cn.hutool.core.util.NumberUtil;
import com.bank.core.entity.BizException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/3/31 17:31
 * 日期类工具列
 */
@Slf4j
public class DateUtils {

    private static final String DATE_yyyyMMdd = "yyyyMMdd" ;

    private static final String DATE_yyyyMMddWithT = "yyyy-MM-DD" ;

    private static final String DATE_yyyyMMddHHmm = "yyyy-MM-dd HH:mm" ;

    private static final String DATE_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss" ;

    /**
     * 日期格式化 yyyyMMdd
     *
     * @param date
     * @return 当前时间减一天
     */
    public static String getDATE_yyyyMMdd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_yyyyMMdd);
        return sdf.format(calendar.getTime());
    }



    /**
     * 节目 更新时日期格式化
     *
     * @param date 日期参数
     * @return
     */
    public static String getProgrameDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_yyyyMMddHHmm);
            Date d = sdf.parse(date);
            return sdf.format(d) + ":00" ;
        } catch (Exception e) {
            throw new BizException("日期参数异常");
        }
    }

    /**
     * 两个时间比较大小
     *
     * @param datestart 第一个时间
     * @param dateend   第二个时间
     * @return 如果 第一个时间>=第二个时间 返回true
     */
    public static boolean conparDate(String datestart, String dateend, String DateFormatStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DateFormatStr);
            Date timestart = sdf.parse(datestart);
            log.info("时间1：" + timestart);
            Date timeend = sdf.parse(dateend);
            log.info("时间2：" + timeend);
            boolean flag = timestart.getTime() >= timeend.getTime();
            log.info("时间1》=时间2：" + flag);
            return flag;
        } catch (Exception e) {
            throw new BizException("日期格式化异常");
        }
    }

    /**
     * @return 当前月的工作日数
     */
    public static int getCuruentMonthWorkDays() {
        // 计算指定月有多少工作日
        int workDays = 0;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,1);
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < days; i++) {
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
                workDays++;
            }
            cal.add(Calendar.DATE, 1);
        }
        return workDays;
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     * java获取 当月所有的日期集合
     * @return
     */
    public static List<String> getDayListOfMonth() {
        List list = new ArrayList();
        Calendar aCalendar = Calendar.getInstance();
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        String monthStr="0";
        if(month<10){
            monthStr="0"+month;
        }else{
            monthStr=String.valueOf(month);
        }
        for (int i = 1; i <= day; i++) {
            String days="0";
            if(i<10){
                days="0"+i;
            }else {
                days=String.valueOf(i);
            }
            String aDate = String.valueOf(year)+"-"+monthStr+"-"+days;
            try {
                list.add(aDate);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return list;
    }
    //获取指定年份 月份的全部日期
    public static List<String> geDaysInMonth(int year, int month) {
        List list = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);// 不设置的话默认为当年
        calendar.set(Calendar.MONTH, month - 1);// 设置月份
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为当月第一天
        int daySize = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);// 当月最大天数
        for (int i = 0; i < daySize; i++) {
            calendar.add(Calendar.DATE, 1);//在第一天的基础上加1
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            if (day < 10){
                if(month<10){
                    list.add(year+"-0"+month+"-0"+day);// 得到当天是一个月的第几天
                }else{
                    list.add(year+"-"+month+"-0"+day);// 得到当天是一个月的第几天
                }
            }else{
                if(month<10){
                    list.add(year+"-0"+month+"-"+day);// 得到当天是一个月的第几天
                }else{
                    list.add(year+"-"+month+"-"+day);// 得到当天是一个月的第几天
                }
            }
        }
        return list;
    }


    /**
     * 获取当月的所有周末
     * @param year
     * @param month
     * @return
     */
    public static List getWeekendInMonth(int year, int month) {
        List list = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);// 不设置的话默认为当年
        calendar.set(Calendar.MONTH, month - 1);// 设置月份
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为当月第一天
        int daySize = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);// 当月最大天数
        for (int i = 0; i < daySize-1; i++) {
            calendar.add(Calendar.DATE, 1);//在第一天的基础上加1
            int week = calendar.get(Calendar.DAY_OF_WEEK);
            if (week == Calendar.SATURDAY || week == Calendar.SUNDAY) {// 1代表周日，7代表周六 判断这是一个星期的第几天从而判断是否是周末
                list.add(year+"-"+month+"-"+calendar.get(Calendar.DAY_OF_MONTH));// 得到当天是一个月的第几天
            }
        }
        return list;
    }

    /**
     * 获取当前日期之前所有工作日
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static List getWorkInMonthByday(int year, int month,int day) {
        List list = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);// 不设置的话默认为当年
        calendar.set(Calendar.MONTH, month - 1);// 设置月份
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为当月第一天
        int daySize = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);// 当月最大天数
        for (int i = 0; i < daySize; i++) {
            calendar.add(Calendar.DATE, 1);//在第一天的基础上加1
            int week = calendar.get(Calendar.DAY_OF_WEEK);
            if(calendar.get(Calendar.DAY_OF_MONTH) <= day){
                if (!(week == Calendar.SATURDAY || week == Calendar.SUNDAY)) {// 1代表周日，7代表周六 判断这是一个星期的第几天从而判断是否是周末
                    if(calendar.get(Calendar.DAY_OF_MONTH) <10){
                        if(month<10){
                            list.add(year+"-"+"0"+month+"-"+"0"+calendar.get(Calendar.DAY_OF_MONTH));
                        }else{
                            list.add(year+"-"+month+"-"+"0"+calendar.get(Calendar.DAY_OF_MONTH));
                        }
                    }else{
                        if(month<10){
                            list.add(year+"-"+"0"+month+"-"+calendar.get(Calendar.DAY_OF_MONTH));
                        }else{
                            list.add(year+"-"+month+"-"+calendar.get(Calendar.DAY_OF_MONTH));
                        }
                    }
                }
            }
        }
        return list;
    }


    /**
     * 获取当月的所有工作日
     * @param year
     * @param month
     * @return
     */
    public static List getWorkInMonth(int year, int month) {
        List dates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH,  month - 1);
        cal.set(Calendar.DATE, 1);
        while(cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) < month){
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){
                if(cal.get(Calendar.DAY_OF_MONTH) <10){
                    if(month<10){
                        dates.add(year+"-"+"0"+month+"-"+"0"+cal.get(Calendar.DAY_OF_MONTH));
                    }else{
                        dates.add(year+"-"+month+"-"+"0"+cal.get(Calendar.DAY_OF_MONTH));
                    }
                }else{
                    if(month<10){
                        dates.add(year+"-"+"0"+month+"-"+cal.get(Calendar.DAY_OF_MONTH));
                    }else{
                        dates.add(year+"-"+month+"-"+cal.get(Calendar.DAY_OF_MONTH));
                    }
                }
            }
            cal.add(Calendar.DATE, 1);
        }
        return dates;
    }

    /**
     * 获取两个时间差
     * @param lastTime 结束时间
     * @param firstTime 开始时间
     * @return 小时数  小数点保存2位
     */
    public static BigDecimal getTime(LocalDateTime lastTime, LocalDateTime firstTime) {
        Date lastDateTime = localDateTime2Date(lastTime);
        Date firstDateTime = localDateTime2Date(firstTime);
        //一天的毫秒数
        long nd = 1000 * 24 * 60 * 60;
        //一小时的毫秒数
        long nh = 1000 * 60 * 60;
        //一分钟的毫秒数
        long nm = 1000 * 60;
        long diff;
        //获得两个时间的毫秒时间差异
        diff = lastDateTime.getTime() - firstDateTime.getTime();
        //计算差多少天
        float day = diff / nd;
        //计算差多少小时
        float hour = diff % nd / nh;
        //计算差多少分钟
        float min = diff % nd % nh / nm;
        //分钟转小时
        float temHout = (min / 60);
        return NumberUtil.round(hour + temHout, 2);
    }

    public static String dateToWeek(String datetime)  {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = (Date) f.parse(datetime);
        } catch (ParseException e) {e.printStackTrace(); }
        cal.setTime(datet);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }



    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate) {
        if(null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }


    /**
     * 获取 月初 日期
     * @param date
     * @return
     */
   public static String getFirstDayOfMounth(String date){
       //获取月初
       LocalDate monthOfFirstDate=LocalDate.parse(date,
               DateTimeFormatter.ofPattern("yyyy-MM-dd")).with(TemporalAdjusters.firstDayOfMonth());
       return monthOfFirstDate.toString();
   }

    /**
     * 获取 月末日期
     * @param date
     * @return
     */
   public static String getLastDayOfMonth(String date){
       //获取月初
       //获取月末
       LocalDate monthOfLastDate=LocalDate.parse(date,
               DateTimeFormatter.ofPattern("yyyy-MM-dd")).with(TemporalAdjusters.lastDayOfMonth());
       return monthOfLastDate.toString();
   }

}
