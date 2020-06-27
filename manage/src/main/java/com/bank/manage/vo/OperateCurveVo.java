package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Zhangfuqiang
 * @Date: 2020/6/15 10:15
 */
@Data
@ApiModel("运营曲线图查询条件")
public class OperateCurveVo {

    /**
     * 所属机构号
     */
    @ApiModelProperty("机构号必填")
    @NotBlank(message = "机构号不能为空！")
    private String orgId;


    /**
     * 名称a
     */
    @ApiModelProperty("查询条件 按h月查询：01，按季查询：03，按年查询：02")
    @NotBlank(message = "查询条件不能为空！")
    private String queryType;

}
