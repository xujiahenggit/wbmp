package com.bank.core.utils;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;

/**
 * @Author: Andy
 * @Date: 2020/6/12 18:01
 */
public class WbmpOperRateUtils {


    /**
     * 小数点保留 2 位
     * @param num
     * @return
     */
    public static float Maht2digit(float num){
        BigDecimal b_num=new BigDecimal(num);
        float t_num=b_num.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue(); ;
        return t_num;
    }


    /**
     * 客户满意度 计算
     * 客户满意度=（1-弃号率）*40%+（1-历史平均等待时长/60）*60%
     * @param abandonedLv 弃号率
     * @param indexCnt  平均等待时长
     * @return 返回结果 向上取整
     */
    public static float ComputeCustomerAvg(BigDecimal abandonedLv, BigDecimal indexCnt){
        //转 float 类型
        float f_abandonedLv=abandonedLv.floatValue();

        float f_indexCnt=indexCnt.floatValue();

        float customerAvg= (float) ((1-f_abandonedLv)*0.4+(1-f_indexCnt/60/60)*0.6);

        return Maht2digit(customerAvg);
    }

    /**
     * S311211客户平均等待时间得分
     * S311211客户平均等待时间得分=客户柜面平均等待时间得分=100-（网点客户平均等待时间-标准等待时间）/标准等待时间*100，满分100分
     * @return
     */
    public static float getCustomerWaitTimeAvgScore(BigDecimal outsiteAvgTime,BigDecimal stardTime){
        //转float
        float f_outsiteAvgTime=outsiteAvgTime.floatValue();
        float f_stardTime=stardTime.floatValue();
        float customerWaitTimeAvG=0;
        customerWaitTimeAvG=100-((f_outsiteAvgTime-f_stardTime)/f_stardTime*100);
        return Maht2digit(customerWaitTimeAvG);
    }

    /**
     *  网点日均业务量 >= 全行日均标准业务量    100分
     * 网点日均业务量 < 全行日均标准业务量，得分=100-（全行日均标准业务量-当前日均业务量)/全行日均标准业务量*100%
     * @param outsiteTranAvgNum 网点日均业务量
     * @param stardNum 网点标准业务量
     * @return 返回值 保留2位小数
     */
    public static float getTranNumAvg(BigDecimal outsiteTranAvgNum,BigDecimal stardNum){
        // 转 float
        float f_outsiteTranAvgNum=outsiteTranAvgNum.floatValue();
        float f_stardNum=stardNum.floatValue();
        float TranNumAvg=0;
        if(f_outsiteTranAvgNum>f_stardNum){
            TranNumAvg=100;
        }else{
            TranNumAvg= (float) (100-(f_stardNum-f_outsiteTranAvgNum)/f_stardNum*0.01);
        }
        return Maht2digit(TranNumAvg);
    }

    /**
     * 赛马制 计算
     * S21211=无纸化业务取消率(12.5%)
     * S21212=银企对账率(12.5%)
     * S21213=电子对账率(12.5%)
     * S21214=对公账户线上开户率(12.5%)
     * S21215=企业客户线上开通率(12.5%)
     * S21216=现金业务分流率(12.5%)
     * S21217=自助设备业务分担率(12.5%)
     * S21218=自助设备可用率(12.5%)
     * 以上加和
     * @return 小数点 保留2位
     */
    public static float smz(float wzh,float yq,float dz,float dg,float qy,float xj,float zzft,float zzky){
        return Maht2digit(wzh+yq+dz+dg+qy+xj+zzft+zzky);
    }


    /**
     * 计算月日均存款值【S111】
     *  网点月日均存款值 >= 全行标准月日均存款值    100分
     * 网点月日均存款值 < 全行标准月日均存款值，得分=100-（全行标准月日均存款值-网点月日均存款值)/网点月日均存款值*100%
     * @param standAvgMouthBal 全行标准月日均存款值
     * @param orgAvgMouthBal 网点月日均存款值
     * @return 返回值 保留2位小数
     */
    public static float calcAvgMouthBal( BigDecimal orgAvgMouthBal,BigDecimal standAvgMouthBal){
        // 转 float
        float f_orgAvgMouthBal=orgAvgMouthBal.floatValue();
        float f_standAvgMouthBal=standAvgMouthBal.floatValue();
        float TranNumAvg=0;
        if(f_orgAvgMouthBal>f_standAvgMouthBal){
            TranNumAvg=100;
        }else{
            TranNumAvg= (float) (100-(f_standAvgMouthBal-f_orgAvgMouthBal)/f_orgAvgMouthBal*100);
        }
        return Maht2digit(TranNumAvg);
    }

    /**
     * 计算AUM值【S112】
     *  网点AUM值 >= 全行标准AUM值    100分
     * 网点AUM值 < 全行标准AUM值，得分=100-（全行标准AUM值-网点AUM值)/网点AUM值*100
     * @param standAumNum 全行标准月日均存款值
     * @param orgAumNum 网点月日均存款值
     * @return 返回值 保留2位小数
     */
    public static float calcOrgAum( BigDecimal orgAumNum,BigDecimal standAumNum){
        // 转 float
        float f_orgAumNum=orgAumNum.floatValue();
        float f_standAumNum=standAumNum.floatValue();
        float TranNumAvg=0;
        if(f_orgAumNum>f_standAumNum){
            TranNumAvg=100;
        }else{
            TranNumAvg= (float) (100-(f_standAumNum-f_orgAumNum)/f_orgAumNum*100);
        }
        return Maht2digit(TranNumAvg);
    }

    /**
     * 计算存款值【S112】
     *  存款 =S11*70%+S12*30%
     * @param avgMouthBal 全行标准月日均存款值
     * @param   avgMouthBalPer s111月日均存款百分比
     * @param orgAumNum 网点月日均存款值
     * @param aumPer s112aum百分比
     * @return 返回值 保留2位小数
     */
    public static float calcOrgBal( float avgMouthBal,BigDecimal avgMouthBalPer, float orgAumNum , BigDecimal aumPer){
        // 转 float
       float f_avgMouthBalPer = avgMouthBalPer.floatValue();
       float f_aumPer = aumPer.floatValue();
       float  orgBalScore = avgMouthBal*f_avgMouthBalPer + orgAumNum*f_aumPer;
       return Maht2digit(orgBalScore);
    }

    /**
     * 客户量分数计算
     * @param
     */
    public static float calcCustNumScore( float custNum,BigDecimal standNum){
        // 转 float
        float stand =standNum.floatValue();
        float TranNumAvg=0;
        if(custNum>stand){
            TranNumAvg=100;
        }else{
            TranNumAvg= (float) (100-(stand-custNum)/custNum*100);
        }
        return Maht2digit(TranNumAvg);
    }

    /**
     * s12客群分数计算
     * S12=S121*10%+S122*40%+S123*30%+S124*20%
     */
    public static float calcCustScore( float normalScore,BigDecimal normalCusPer, float goldScore , BigDecimal goldCusPer,float platinumScore , BigDecimal platinumCusPer,float diamonScore , BigDecimal diamonCusPer){
        // 转 float
        float normalCusPerV = normalCusPer.floatValue();
        float goldCusPerV = goldCusPer.floatValue();
        float platinumCusPerV = platinumCusPer.floatValue();
        float diamonCusPerV = diamonCusPer.floatValue();
        float  custScore = normalScore * normalCusPerV + goldScore * goldCusPerV +platinumScore * platinumCusPerV+ diamonScore * diamonCusPerV;
        return Maht2digit(custScore);
    }

    /**
     * 经营分数计算【S1】
     * S1=S11*70%+S12*30%
     */
    public static float calcOpenRateScore( float balScore,BigDecimal bal_per, float custScore , BigDecimal cus_per){
        // 转 float
        float balPer = bal_per.floatValue();
        float cusPer = cus_per.floatValue();
        float  score = balScore * balPer + custScore * cusPer;
        return Maht2digit(score);
    }

    public static void main(String[] args) {
        System.out.println(Maht2digit(20.7909328409239804f));
    }
}
