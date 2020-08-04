package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 柜员操作日志表
 *
 * @author
 * @date 2020-7-8
 */
@Data
@ApiModel(description = "客户操作日志表")
public class TlogUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键自增ID")
    private Integer id;

    @ApiModelProperty(value = "机构号")
    private String jyId;

    @ApiModelProperty(value = "组织机构名称")
    private String userId;

    @ApiModelProperty(value = "备注")
    private String telNo;

    @ApiModelProperty(value = "开始交易时间")
    private Date beginTime;

    @ApiModelProperty(value = "结束交易时间")
    private Date endTime;

    @ApiModelProperty(value = "交易时间")
    private Date modifyTime;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "日志类型")
    private String czbz;

    @ApiModelProperty(value = "日志类型")
    private String chanlid;
}
