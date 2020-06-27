package com.bank.role.dos;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统角色对象
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Data
@Builder
@TableName("S_ROLE")
@NoArgsConstructor                 //无参构造
@AllArgsConstructor
public class RoleDO implements Serializable {

    private static final long serialVersionUID = 447541602506520717L;

    /**
     * 角色id
     */
    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private int roleId;

    /**
     * 角色名
     */

    private String roleName;

    /**
     * 角色标识码
     */
    private String roleCode;

    /**
     * 创建时间戳
     */
    private Date createTime;

    /**
     * 更新时间戳
     */
    private Date updateTime;

    /**
     * 删除标识 默认为 0  0：未删除 1：已删除
     */
    private String roleDelflag;

    /**
     * 是否为系统角色
     */
    private String system;
}
