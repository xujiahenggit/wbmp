package com.bank.role.service;

import com.bank.role.dos.RolePermissionDO;

import java.util.List;

/**
 * 角色权限服务接口
 * @author zhangfuqiang
 * @Date 2020/04/02
 */
public interface RolePermissionService {

    /**
     * 插入角色权限
     * @param permission,roleId 角色权限信息
     * @return 插入条数
     */
    Integer insert(String permission,Integer roleId);


    /**
     * 删除角色信息
     *
     * @param roleId 角色唯一标识
     * @return 删除条数
     */
    Integer deleteByRoleId(Integer roleId);


    /**
     * 根据用户id，获取角色信息
     * @param roleId 用户id
     * @return 角色对象集合
     */
    List<RolePermissionDO> listByRoleId(Integer roleId);

}
