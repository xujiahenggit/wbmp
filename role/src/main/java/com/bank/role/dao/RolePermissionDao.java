package com.bank.role.dao;

import com.bank.role.dos.RolePermissionDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 系统角色权限表
 * @author zhangfuqiang
 * @Date 2020/04/02
 */
public interface RolePermissionDao extends BaseMapper<RolePermissionDO> {


    /**
     * 根据roleId 获取角色权限列表
     * @param roleId
     * @return
     */
    List<RolePermissionDO>  getByRoleId(Serializable roleId);

    /**
     * 根据角色信息删除角色权限
     * @param roleId
     */
   void deleteByRoleId(@Param("roleId") Integer roleId);

    /**
     * 批量插入角色权限表
     */
    int batchInsert(List<RolePermissionDO> list);

    /**
     * 查询 已经赋予权限的角色 数
     * @param roleId 角色ID
     * @return
     */
    Integer selectRoleCount(@Param(value = "permissionId") Integer roleId);
}
