package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/9 9:52
 */
@ApiModel("行员信息查询用")
@Data
public class StaffQueryVo implements Serializable {

    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    @ApiModelProperty("行员姓名")
    private String userName;

    @ApiModelProperty("行员工号")
    private String userId;

    @ApiModelProperty("行员电话")
    private String phone;

    @ApiModelProperty("行员性别  男/女")
    private String sex;

    @ApiModelProperty("机构号码")
    private String orgId;
}
