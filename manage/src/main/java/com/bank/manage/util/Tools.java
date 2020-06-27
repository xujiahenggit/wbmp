package com.bank.manage.util;

import cn.hutool.core.date.DateUtil;
import com.bank.core.sysConst.WbmpConstFile;
import oracle.sql.DATE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Tools {

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String date2Str(Date date) {
        return date2Str(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
     *
     * @param date
     * @return
     */
    public static Date str2Date(String date) {
        if (notEmpty(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date();
        } else {
            return null;
        }
    }

    /**
     * 检测字符串是否不为空(null,"","null")
     *
     * @param s
     * @return 不为空则返回true，否则返回false
     */
    public static boolean notEmpty(String s) {
        return s != null && !"".equals(s) && !"null".equals(s);
    }

    /**
     * 按照参数format的格式，日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    /**
     * 根据时间的类型，获取X轴数据
     */
    public static List<String> getXAxis(String tpye) {
        List<String> xAxis = new ArrayList<String>();
        switch (tpye) {
            case WbmpConstFile.DATE_TYPE_YEAR:
                int year = DateUtil.year(new Date());
                for (int i = 0; i <= 2; i++) {
                    xAxis.add(String.valueOf(year - i));
                }
                break;
            case WbmpConstFile.DATE_TYPE_JIDU:
                DateRange range = getLastQuarter(new Date());
                Date endDate =   range.getEnd();
                int month = DateUtil.month(endDate);
                int jidu =   (month+1)/3;
                xAxis.add(numFormatJd(jidu));
                   for(int i=3;i>0;i--){
                       range =  getLastQuarter(endDate);
                       endDate = range.getEnd();
                       month = DateUtil.month(endDate);
                       jidu =   (month+1)/3;
                       xAxis.add(numFormatJd(jidu));
                   }
                break;
            case WbmpConstFile.DATE_TYPE_MONTH:
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                for (int i = -1; i > -13; i--) {
                    c.setTime(new Date());
                    c.add(Calendar.MONTH, i);
                    Date m3 = c.getTime();
                    xAxis.add(format.format(m3));
                }
                break;
        }
        Collections.reverse(xAxis);
        return xAxis;
    }

    /**
     * 根据时间的类型，获取X轴数据
     */
    public static List<String> getXDateDetail(String tpye) {
        List<String> xDateDetail = new ArrayList<String>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        switch (tpye) {
            case WbmpConstFile.DATE_TYPE_YEAR:
                int year = DateUtil.year(new Date());
                for (int i = 0; i <= 2; i++) {
                    xDateDetail.add(String.valueOf(year - i));
                }
                break;
            case WbmpConstFile.DATE_TYPE_JIDU:
                DateRange range = getLastQuarter(new Date());
                Date endDate =   range.getEnd();
                String date = format.format(endDate);
                xDateDetail.add(date);
                for(int i=3;i>0;i--){
                    range =  getLastQuarter(endDate);
                    endDate = range.getEnd();
                    date = format.format(endDate);
                    xDateDetail.add(date);
                }
                break;
            case WbmpConstFile.DATE_TYPE_MONTH:
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                for (int i = -1; i > -13; i--) {
                    c.setTime(new Date());
                    c.add(Calendar.MONTH, i);
                    Date m3 = c.getTime();
                    xDateDetail.add(format.format(m3));
                }
                break;
        }
        Collections.reverse(xDateDetail);
        return xDateDetail;
    }

    /**
     * 获取date的月份的时间范围
     *
     * @param date
     * @return
     */
    public static DateRange getMonthRange(Date date) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(date);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        setMaxTime(startCalendar);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(date);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setMaxTime(endCalendar);

        return new DateRange(startCalendar.getTime(), endCalendar.getTime());
    }

    /**
     * 获取当前季度的时间范围
     *
     * @return current quarter
     */
    public static DateRange getThisQuarter(Date date) {
        Calendar startCalendar = null;
        Calendar endCalendar = null ;
        if(date ==null || "".equals(date)){
            startCalendar = Calendar.getInstance();
            endCalendar = Calendar.getInstance();
        }else{
            startCalendar = dateToCalendar(date);
            endCalendar = dateToCalendar(date);
        }
        startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        setMinTime(startCalendar);
        endCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3 + 2);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setMaxTime(endCalendar);
        return new DateRange(startCalendar.getTime(), endCalendar.getTime());
    }

    /**
     * 获取昨天的时间范围
     *
     * @return
     */
    public static DateRange getYesterdayRange() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.DAY_OF_MONTH, -1);
        setMinTime(startCalendar);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.DAY_OF_MONTH, -1);
        setMaxTime(endCalendar);

        return new DateRange(startCalendar.getTime(), endCalendar.getTime());
    }

    /**
     * 获取当前月份的时间范围
     *
     * @return
     */
    public static DateRange getThisMonth() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        setMinTime(startCalendar);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setMaxTime(endCalendar);

        return new DateRange(startCalendar.getTime(), endCalendar.getTime());
    }

    /**
     * 获取上个月的时间范围
     *
     * @return
     */
    public static DateRange getLastMonth() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.MONTH, -1);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        setMinTime(startCalendar);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.MONTH, -1);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setMaxTime(endCalendar);

        return new DateRange(startCalendar.getTime(), endCalendar.getTime());
    }

    /**
     * 获取上个季度的时间范围
     *
     * @return
     */
    public static DateRange getLastQuarter(Date date) {
        Calendar startCalendar ;
        Calendar endCalendar ;
        if(date ==null || "".equals(date)){
            startCalendar = Calendar.getInstance();
            endCalendar = Calendar.getInstance();
        }else{
            startCalendar = dateToCalendar(date);
            endCalendar = dateToCalendar(date);
        }
        startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3 - 1) * 3);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        setMinTime(startCalendar);
        endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 - 1) * 3 + 2);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setMaxTime(endCalendar);

        return new DateRange(startCalendar.getTime(), endCalendar.getTime());
    }

    private static void setMinTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setMaxTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
    }

    public final static String DEFAULT_PATTERN = "yyyy-MM-dd";

    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
        return sdf.format(date);
    }

    public static String numFormatJd(int num) {
        String jidu = "";
        switch (num) {
            case 1:
                jidu = "一季度";
                break;
            case 2:
                jidu = "二季度";
                break;
            case 3:
                jidu = "三季度";
                break;
            case 4:
                jidu = "四季度";
                break;
        }
        return  jidu;
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return  cal;
    }

    public static void main(String[] args) {
//        DateRange rage = getThisQuarter(null);
//
//        System.out.println(format(rage.getStart()));
//        System.out.println(format(rage.getEnd()));
        int num = (DateUtil.month(new Date())+1)%3;
        System.out.println("num:"+num);
        int jidu =   ((DateUtil.month(new Date())+1)%3)+1;
        System.out.println("jidu:"+jidu);

        int e =   1/3;
        System.out.println("e:"+e);
    }
}
