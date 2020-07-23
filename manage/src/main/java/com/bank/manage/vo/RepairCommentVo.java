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


    /**
     * 处理人id
     */
    @ApiModelProperty("处理人id")
    @NotBlank(message = "处理人id不能为空！")
    private String dealWithPeopleId;


    /**
     * 处理人姓名
     */
    @ApiModelProperty("处理人姓名")
    @NotBlank(message = "处理人姓名不能为空！")
    private String dealWithPeopleName;

    /**
     * 处理人角色
     */
    @ApiModelProperty("处理人角色")
    @NotBlank(message = "处理人角色不能为空！")
    private String dealWithPeopleRole;

    /**
     * 处理人机构号
     */
    @ApiModelProperty("处理人机构号")
    @NotBlank(message = "机构号不能为空！")
    private String orgId;

    @ApiModelProperty("处理人电话")
    @NotBlank(message = "处理人电话！")
    private String  phone ;
}
