package com.bank.role.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色权限表
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Data
@Builder
@TableName("S_ROLE_PERMISSION")
@NoArgsConstructor                 //无参构造
@AllArgsConstructor
public class RolePermissionDO implements Serializable {

    private static final long serialVersionUID = 447541602506520717L;


    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限ID
     */
    private Integer permissionId;

    /**
     * 创建时间戳
     */
    private Date createTime;

    /**
     * 更新时间戳
     */
    private Date updateTime;
}
