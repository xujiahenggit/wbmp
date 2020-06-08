package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/2 16:39
 */
@ApiModel("月度考勤 驳回列表")
@Data
public class CheckWorkRejectDto implements Serializable {

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String uName;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String orgName;
    /**
     * 驳回理由
     */
    @ApiModelProperty(value = "驳回理由")
    private String rejectResion;
}
