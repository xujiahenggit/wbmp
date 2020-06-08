package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/28 10:57
 */
@ApiModel("网点引导员补卡申请查询用")
@Data
public class CardSuppleQueryVo implements Serializable {
    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    /**
     * 引导员手机号码
     */
    @ApiModelProperty("引导员手机号码")
    private String userPhone;
}
