package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/4/9 9:44
 */
@ApiModel("行员信息")
@Data
@TableName("T_STAFF")
public class StaffDO extends Model<StaffDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 行员ID
     */
    @ApiModelProperty(value = "行员ID")
    @TableId(value = "STAFF_ID", type = IdType.AUTO)
    private Integer staffId;

    /**
     * 行员姓名
     */
    @ApiModelProperty(value = "行员姓名")
    private String staffName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "行员性别")
    private String staffSex;

    /**
     * 工号
     */
    @ApiModelProperty(value = "行员工号")
    private String staffUserid;

    /**
     * 荣誉介绍
     */
    @ApiModelProperty(value = "荣誉介绍")
    private String staffHonor;

    /**
     * 专家观点
     */
    @ApiModelProperty(value = "专家观点")
    private String staffViewpoint;

    /**
     * 正面照地址
     */
    @ApiModelProperty(value = "正面照地址")
    private String staffFullfacephoto;

    /**
     * 笑脸照地址
     */
    @ApiModelProperty(value = "笑脸照地址")
    private String staffSmilephotopath;

    /**
     * 海报地址
     */
    @ApiModelProperty(value = "海报地址")
    private String staffPosterpath;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String staffPhone;

    /**
     * 二维码地址
     */
    @ApiModelProperty(value = "二维码地址")
    private String staffQrcodepath;

    /**
     * 所属机构号
     */
    @ApiModelProperty(value = "所属机构号")
    private String staffOrgid;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人ID")
    private String updateUserid;

    /**
     * 更新人姓名
     */
    @ApiModelProperty(value = "更新人姓名")
    private String updateUsername;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 审核人ID
     */
    @ApiModelProperty(value = "审核人ID")
    private String auditorUserid;

    /**
     * 审核人姓名
     */
    @ApiModelProperty(value = "审核人姓名")
    private String auditorUsername;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditorTime;

    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见")
    private String auditorOption;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态 ---这个状态暂时不用")
    private String auditorStatus;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String staffIdentify;

    /**
     * 是否上屏
     */
    @ApiModelProperty(value = "是否上屏")
    private String isline;

    /**
     * 欢迎语
     */
    @ApiModelProperty(value = "欢迎语")
    private String welcome;

    /**
     * 岗位ID
     */
    @ApiModelProperty(value = "岗位ID")
    private String positionId;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称")
    private String position;

    /**
     * 资质
     */
    @ApiModelProperty(value = "资质")
    private String creditation;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String cnname;
    /**
     * 添加人ID
     */
    @ApiModelProperty(value = "添加人ID")
    private String createUserid;

    /**
     * 添加人姓名
     */
    @ApiModelProperty(value = "添加人姓名")
    private String createUsername;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    private LocalDateTime createTime;
}
