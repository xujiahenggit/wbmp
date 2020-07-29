package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Zhangfuqiang
 * @Date: 2020/6/15 10:15
 */
@Data
@ApiModel("工单评论")
public class RepairCommentVo {

    /**
     * 工单编号
     */
    @ApiModelProperty("工单编号")
    @NotBlank(message = "工单编号不能为空！")
    private String workOrderCode;

    @ApiModelProperty(value = "现场联系人工号")
    private String contactId;

    /**
     * 工单类型
     */
    @ApiModelProperty("工单类型")
    @NotBlank(message = "工单类型！")
    private String repairType;

    /**
     * 评论等级
     */
    @ApiModelProperty("评价等级（1：优 2：良 3：一般 4：差）")
    @NotBlank(message = "评论等级不能为空！")
    private String rating;


    /**
     * 退回原因
     */
    @ApiModelProperty("评价")
    @NotBlank(message = "评价不能为空！")
    private String  ratingNote ;


    @ApiModelProperty("退回原因")
    private String returnOpinion;

    @ApiModelProperty("分行取消意见")
    private String subCancelOpinion;

    @ApiModelProperty("总行取消意见")
    private String bankCancelOpinion;

    @ApiModelProperty("操作: 1:确认；2：取消;3：知悉；4：退回；5：评价；6：厂商回复")
    private String type;
}
