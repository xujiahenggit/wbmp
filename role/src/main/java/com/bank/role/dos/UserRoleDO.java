package com.bank.role.dos;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/4/7 15:27
 */
@Data
@TableName("S_USER_ROLE")
public class UserRoleDO extends Model<UserRoleDO> {

    /**
     *
     */
    private static final long serialVersionUID = -6388961046639675608L;

    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.INPUT)
    private String userId;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
