package com.bank.user.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/4/8 17:07
 */
@Data
@TableName("T_USER_TEMP")
public class UserTempDO extends Model<UserTempDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.UUID)
    private String userId;

    /**
     * 密码
     */
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
     * 性别
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
