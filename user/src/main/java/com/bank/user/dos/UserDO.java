package com.bank.user.dos;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户对象
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Data
@TableName("T_USER")
public class UserDO extends Model<UserDO> {
    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.UUID)
    private String userId;

    /**
     * 密码
     */
    @JSONField(serialize=false)
    private String password;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 组织机构编号
     */
    private String orgId;

    /**
     * 组织机构名称
     */
    private String orgName;

    /**
     * 岗位ID
     */
    private String positionId;

    /**
     * 岗位名称
     */
    private String positionName;

    /**
     * 部门ID
     */
    private String departId;

    /**
     * 部门名称
     */
    private String departName;

    /**
     * 在岗状态
     */
    private String userStatus;

    /**
     * 手机号码
     */
    private String userPhone;

    /**
     * 性别 默认未 0  0：女 1：男
     */
    private String userGender;

    /**
     * 证件号码
     */
    private String userIdentiyno;

    /**
     * 添加用户ID
     */
    private String userCreateUserid;

    /**
     * 添加用姓名
     */
    private String userCreateUsername;

    /**
     * 添加时间
     */
    private LocalDateTime userCreateTime;

    /**
     * 更新用户ID
     */
    private String userUpdateUserid;

    /**
     * 更新用户姓名
     */
    private String userUpdateUsername;

    /**
     * 更新时间
     */
    private LocalDateTime userUpdateTime;

}

