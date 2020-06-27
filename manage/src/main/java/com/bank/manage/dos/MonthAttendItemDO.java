package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/5/29 20:59
 */
@TableName("T_MONTH_ATTEND_ITEM")
@Data
@ApiModel("月度考勤数据确认从表")
public class MonthAttendItemDO extends Model<MonthAttendItemDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "MONTH_ATTEND_ITEM_ID", type = IdType.AUTO)
    private Integer monthAttendItemId;

    /**
     * 月度考勤数据确认主表ID
     */
    private Integer monthAttendId;

    /**
     * 引导员ID
     */
    private Integer usherId;

    /**
     * 满意度考核得分
     */
    private Integer monthAttendScore;

    /**
     * 应出勤天数
     */
    private Integer monthAttendMustDays;

    /**
     * 出勤天数
     */
    private Integer monthAttendRealDays;

    /**
     * 工作日加班
     */
    private Float monthAttendWorkLength;

    /**
     * 法定节假日加班
     */
    private Float monthAttendRestLenght;
}
