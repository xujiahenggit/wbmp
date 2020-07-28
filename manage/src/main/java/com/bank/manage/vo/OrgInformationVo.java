package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
/**
 * cq
 */
public class OrgInformationVo {
    @ApiModelProperty("编码")
    private  String code;
    @ApiModelProperty("名称")
    private  String name;
    @ApiModelProperty("1：总行；2：分行；3：支行")
    private  String flog;

}
