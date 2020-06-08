package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/21 15:20
 */
@ApiModel("审核流程 查询用")
@Data
public class NewProcessQueryVo implements Serializable {

    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    @ApiModelProperty("提交人姓名")
    private String creatorName;

    @ApiModelProperty("类型")
    private String  tradingModule;
}
