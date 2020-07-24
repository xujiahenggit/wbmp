package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * (t_repair)
 */
@Data
@ApiModel
public class ContactVo {
    @ApiModelProperty(value = "现场联系人")
    private String contactName;
    @ApiModelProperty(value = "现场联系人号码")
    private String contactPhone;
}
