package com.bank.manage.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/2 15:53
 */
@Data
public class CheckWorkAttendExcel implements Serializable {

    @Excel(name = "状态")
    private String state;

    @Excel(name = "满意度月度考核")
    private int satisfactAttentNum;

    @Excel(name = "月度考核确认")
    private int monthAttendNum;
}
