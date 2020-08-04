package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 帐号指定
 *
 * @author
 * @date 2020-7-4
 */
@Data
@ApiModel(description = "帐号指定")
public class TmapActverifyDTO implements Serializable {

	private static final long serialVersionUID = 3724656268296968552L;

    @ApiModelProperty(value = "主键自增ID")
    private Integer id;

	@ApiModelProperty(value = "帐号")
    private String acctno;

    @ApiModelProperty(value = "录入柜员")
    private String tellerInsert;

    @ApiModelProperty(value = "录入时间")
    private LocalDateTime timeInsert;

    @ApiModelProperty(value = "机构号", required = true)
    private String branch;

}
