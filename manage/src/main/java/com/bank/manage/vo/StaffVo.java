package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @Author: Andy
 * @Date: 2020/4/9 10:15
 */
@Data
@ApiModel("行员信息 添加更新时用")
public class StaffVo {

    @ApiModelProperty("行员ID 更新时必填")
    private Integer staffId;

    /**
     * 行员姓名
     */
    @ApiModelProperty("行员姓名 添加/更新时 必填")
    private String staffName;

    /**
     * 性别
     */
    @ApiModelProperty("行员性别 添加/更新时 必填")
    private String staffSex;

    /**
     * 工号
     */
    @ApiModelProperty("行员工号 添加/更新时 必填")
    @NotBlank(message = "行员工号不能为空")
    private String staffUserid;

    /**
     * 荣誉介绍
     */
    @ApiModelProperty("荣誉介绍")
    private String staffHonor;

    /**
     * 专家观点
     */
    @ApiModelProperty("专家观点 添加/更新时 必填")
    @NotBlank(message = "专家观点 不能为空")
    private String staffViewpoint;

    /**
     * 正面照地址
     */
    @ApiModelProperty("正面照地址 添加/更新时 必填")
    @NotBlank(message = "正面照地址 不能为空")
    private String staffFullfacephoto;

    /**
     * 笑脸照地址
     */
    @ApiModelProperty("笑脸照地址 添加/更新时 必填")
    @NotBlank(message = "笑脸照地址 不能为空")
    private String staffSmilephotopath;

    /**
     * 海报地址
     */
    @ApiModelProperty("海报地址 添加/更新时 必填")
    @NotBlank(message = "海报地址不能为空")
    private String staffPosterpath;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码 添加/更新时 必填")
    @NotBlank(message = "手机号码不能为空")
    private String staffPhone;

    /**
     * 二维码地址
     */
    @ApiModelProperty("二维码地址")
    private String staffQrcodepath;

    /**
     * 所属机构号
     */
    @ApiModelProperty("所属机构号 添加/更新 时必填")
    @NotBlank(message = "机构号不能为空")
    private String staffOrgid;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String staffIdentify;

    /**
     * 是否上屏
     */
    @ApiModelProperty("是否上屏")
    private String isline;

    /**
     * 欢迎语
     */
    @ApiModelProperty("欢迎语")
    private String welcome;

    /**
     * 岗位ID
     */
    @ApiModelProperty("岗位ID")
    private String positionId;

    /**
     * 岗位名称
     */
    @ApiModelProperty("岗位名称 添加/更新 时必填")
    @NotBlank(message = "岗位名称不能为空")
    private String position;

    /**
     * 资质
     */
    @ApiModelProperty("资质 添加/更新 时必填")
    @NotBlank(message = "资质 不能为空")
    private String creditation;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String cnname;
}
