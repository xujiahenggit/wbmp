package com.bank.auth.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统权限对象
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Data
@Builder
@TableName("S_PERMISSION")
public class PermissionDO implements Serializable {

    private static final long serialVersionUID = -6355624972115421108L;

    /**
     * 权限id主键
     */
    @TableId(value = "PERMISSION_ID", type = IdType.AUTO)
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限标识码
     */
    private String permissionCode;

    /**
     * 前端路由地址
     */
    private String routerPath;

    /**
     * 父ID
     */
    private Integer parentId;

    private String parentDesc;

    /**
     * 图标
     */
    private String icon;

    /**
     * component
     */
    private String component;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 权限类型 1：一级菜单 2：二级菜单 3：接口 4：超连接
     */
    private String permissionType;

    private String permissionTypeDesc;

    /**
     * 创建时间戳
     */
    private Date createTime;

    /**
     * 更新时间戳
     */
    private Date updateTime;

    /**
     * 删除标识 默认未 0  0：未删除 1：已删除
     */
    private String permissionDelflag;
}
