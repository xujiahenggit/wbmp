package com.bank.esb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "工程师Vo")
@Data
public class EngineerVo {

    @ApiModelProperty(value = "服务主管编号")
    private String engineerMId;

    @ApiModelProperty(value = "查询文本：该字段可以为工程师名称、工程师电话")
    private String seachTxt;

}
