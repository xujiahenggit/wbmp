package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: Andy
 * @Date: 2020/5/27 21:01
 */
@ApiModel(value = "大堂经理巡查待办")
@TableName("T_PARTORL_PROCESS")
@Data
public class PartorlProcessDO extends Model<PartorlProcessDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "PARTORL_PROCESS_ID", type = IdType.AUTO)
    @ApiModelProperty(value = "编号")
    private Integer partorlProcessId;

    /**
     * 待办日期
     */
    @ApiModelProperty(value = "待办日期")
    private LocalDate partorlProcessDate;

    /**
     * 待办机构号
     */
    @ApiModelProperty(value = "待办机构号")
    private String partorlProcessOrgId;

    /**
     * 待办机构名称
     */
    @ApiModelProperty(value = "待办机构名称")
    private String partorlProcessOrgName;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private String partorlProcessNum;

    /**
     * 是否已办
     */
    @ApiModelProperty(value = "是否已办")
    private String partorlProcessState;

    /**
     * 待办记录ID
     */
    @ApiModelProperty(value = "待办记录ID")
    private Integer partorlRecordId;
}
