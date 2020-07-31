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
@ApiModel("工单退回")
public class RepairRebackVo {



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



    /**
     * 工单编号
     */
    @ApiModelProperty("工单编号")
    @NotBlank(message = "工单编号不能为空！")
    private String workOrderCode;


    /**
     * 退回原因
     */
    @ApiModelProperty("退回原因")
    @NotBlank(message = "退回原因！")
    private String  dealWithNote ;


    @ApiModelProperty("处理人电话")
    @NotBlank(message = "处理人电话！")
    private String  phone ;

}
