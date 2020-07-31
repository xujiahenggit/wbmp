package com.bank.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.Value;
import org.springframework.boot.SpringApplication;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/7 16:43
 */
@ApiModel("用户信息 查询时用")
@Data
public class UserVO implements Serializable {
    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    @ApiModelProperty("组织机构ID")
    private String orgId;

    @ApiModelProperty("组织机构名称")
    private String orgName;

    @ApiModelProperty("用户编号")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("角色编号")
    private Integer roleId;

    @ApiModelProperty("关键字")
    private String keyWork;
}
