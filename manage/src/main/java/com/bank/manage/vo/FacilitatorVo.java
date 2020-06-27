package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/24 10:57
 */
@Data
@ApiModel("查询引导员待办用")
public class FacilitatorVo implements Serializable {

    @ApiModelProperty("第几页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小")
    private Integer pageSize = 10;
}
