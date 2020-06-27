package com.bank.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/9 18:05
 */
@ApiModel(description="用户信息 新增/更新时用")
@Data
public class UserSaveUpdateVo implements Serializable {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户工号 更新时 必填")
    private String userId;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称 新增/更新时 必填")
    private String userName;

    /**
     * 组织机构编号
     */
    @ApiModelProperty(value = "组织机构编号 新增/更新时 必填")
    private String orgId;

    /**
     * 组织机构名称
     */
    @ApiModelProperty(value = "组织机构名称 新增/更新时 必填")
    private String orgName;

    /**
     * 岗位ID
     */
    @ApiModelProperty(value = "岗位ID")
    private String positionId;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称")
    private String positionName;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private String departId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String departName;

    /**
     * 在岗状态
     */
    @ApiModelProperty(value = "在岗状态")
    private String userStatus;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String userPhone;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别 直接填写 男女")
    private String userGender;

    /**
     * 证件号码
     */
    @ApiModelProperty(value = "证件号码")
    private String userIdentiyno;

}
