package com.bank.core.utils;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;

/**
 * @Author: Andy
 * @Date: 2020/6/12 18:01
 */
public class WbmpOperRateUtils {

    /**
     * 客户满意度 计算
     * 客户满意度=（1-弃号率）*40%+（1-历史平均等待时长/60）*60%
     * @param abandonedLv 弃号率
     * @param indexCnt  平均等待时长
     * @return 返回结果 向上取整
     */
    public static String ComputeCustomerAvg(BigDecimal abandonedLv, BigDecimal indexCnt){
//        BigDecimal one=new BigDecimal(1);
//        BigDecimal sixten=new BigDecimal(60);
//        BigDecimal CustomerAvg;
//        CustomerAvg=(one.subtract(abandonedLv)).multiply(new BigDecimal(0.4)).add(one.subtract(indexCnt.divide(sixten)).multiply(new BigDecimal(0.6)));

        float f_abandonedLv=abandonedLv.floatValue();

        float f_indexCnt=indexCnt.floatValue();

        double customerAvg=((1-f_abandonedLv)*0.4+(1-f_indexCnt/60)*0.6)*100;

        return NumberUtil.roundStr(customerAvg,0);
    }


    public static void main(String[] args) {
        BigDecimal one=new BigDecimal(1);
        BigDecimal sixten=new BigDecimal(60);
        BigDecimal a=new BigDecimal(28);
        System.out.println(a.divide(sixten));
    }
}
