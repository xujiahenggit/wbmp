package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 指定重点账号对账表
 *
 * @author
 * @date 2020-7-5
 */
@Data
@ApiModel(description = "指定重点账号")
public class TmapKeyacctverifyDTO implements Serializable {

    private static final long serialVersionUID = 928262673151315393L;

    @ApiModelProperty(value = "主键自增ID")
    private Integer id;

	@ApiModelProperty(value = "帐号", required = true)
    private String acctno;

    @ApiModelProperty(value = "登记人")
    private String tellerInsert;

    @ApiModelProperty(value = "登记时间")
    private LocalDateTime timeInsert;

    @ApiModelProperty(value = "修改人")
    private String tellerUpdate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime timeUpdate;

    @ApiModelProperty(value = "机构号", required = true)
    private String branch;

    @ApiModelProperty(value = "状态0启用1停用")
    private String status;
}
