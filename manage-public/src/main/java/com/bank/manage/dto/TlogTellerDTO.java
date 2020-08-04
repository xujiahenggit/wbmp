package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 柜员操作日志表
 *
 * @author
 * @date 2020-7-8
 */
@Data
@ApiModel(description = "柜员操作日志表")
public class TlogTellerDTO implements Serializable {

    private static final long serialVersionUID = 6809277310107447960L;

    @ApiModelProperty(value = "主键自增ID")
    private Integer id;

    @ApiModelProperty(value = "机构号")
    private String branchCode;

    @ApiModelProperty(value = "组织机构名称")
    private String orgName;

    @ApiModelProperty(value = "柜员编号")
    private String userCode;

    @ApiModelProperty(value = "开始交易时间")
    private LocalDateTime beginModifyTime;

    @ApiModelProperty(value = "结束交易时间")
    private LocalDateTime endModifyTime;

    @ApiModelProperty(value = "交易时间")
    private LocalDateTime modifyTime;

    @ApiModelProperty(value = "节点编号")
    private String menuId;

    @ApiModelProperty(value = "功能类型")
    private String menuName;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "日志类型")
    private String czbz;
}
