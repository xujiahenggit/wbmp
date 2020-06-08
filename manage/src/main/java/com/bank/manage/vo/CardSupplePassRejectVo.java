package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/28 14:48
 */
@Data
@ApiModel("补卡申请审核用")
public class CardSupplePassRejectVo implements Serializable {
    @ApiModelProperty("编号")
    private Integer cardSuppleId;

    /**
     * 驳回原因
     */
    @ApiModelProperty("驳回原因-驳回时必填")
    private String cartSuppleRejectResion;
}
