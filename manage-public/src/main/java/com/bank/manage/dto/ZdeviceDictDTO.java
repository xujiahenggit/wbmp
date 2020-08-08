package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页设备统计
 * 
 * @author
 * @date 2020-7-27
 */
@Data
@Builder
@AllArgsConstructor
@ApiModel(description = "首页设备统计")
public class ZdeviceDictDTO implements Serializable {

    private static final long serialVersionUID = -1195916676620844439L;

    @ApiModelProperty(value = "字典名")
    private String dictname;

    @ApiModelProperty(value = "统计数")
    private Integer connum;
}
