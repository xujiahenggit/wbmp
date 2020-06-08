package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/28 17:21
 */
@Data
@ApiModel(value = "加班申请审核用")
public class WorkSupplePassRejectVo implements Serializable {

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Integer workSuppleId;

    /**
     * 驳回原因
     */
    @ApiModelProperty(value = "驳回原因")
    private String workSuppleRejectResion;
}
