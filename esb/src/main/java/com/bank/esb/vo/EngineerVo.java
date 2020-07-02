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

}
