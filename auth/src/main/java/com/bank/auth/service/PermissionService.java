package com.bank.auth.service;

import com.bank.auth.dos.PermissionDO;
import com.bank.auth.dto.AuthDTO;
import com.bank.auth.dto.PermissionDTO;
import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * ClassName: PermissionService
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/03/31 19:16:56
 */
public interface PermissionService {

    /**
     * 插入权限信息
     *
     * @param PermissionDTO 权限信息
     * @return 插入条数
     */
    Integer insert(PermissionDTO permissionDTO);

    /**
     * 更新权限信息
     *
     * @param PermissionDTO 权限信息
     * @return 更新条数
     */
    Integer updateById(PermissionDTO permissionDTO);

    /**
     *删除权限信息-逻辑删除
     * @param id 权限id
     * @return 删除条数
     */
    Integer deleteById(Integer id);

    /**
     * 分页查询权限信息
     * @param pageIndex 第几页
     * @param pageSize 一页多少行
     * @param permissionDTO 查询条件
     * @return
     */
    IPage<PermissionDO> selectPage(PageQueryModel pageQueryModel);

    /**
     * 主键查询权限信息
     * @param id 权限id
     * @return 权限信息
     */
    PermissionDO selectById(Integer id);

    /**
     * 获取角色拥有的权限信息
     *
     * @param ids 角色id集合
     * @return 权限对象集合
     */
    Set<PermissionDTO> listByRoleIds(Set<Integer> ids);

    /**
     * 获取所有可用的权限信息
     * @return 权限对象集合
     */
    Set<PermissionDTO> listAllPermissions();

    /**
     * 查询权限根与一级菜单（功能模块）
     * @return
     */
    List<Map<String, Object>> selectParentList();

    /**
     * 查询二级菜单
     * @return
     */
    List<Map<String, Object>> selectParentList2();

    Set<PermissionDTO> listByRoleId(Integer ids);

    Set<PermissionDTO> listPermissionByRoleIds(Set<Integer> ids);

    /**
     * 获取用户 菜单
     * @param id 用户编号
     * @return
     */
    Object getUserMenu(String id);

    Object getAppMenus(String contextPath, String id);

    AuthDTO getAuthDTO(String userId);

    String[] getButtonPermission(String userId);

    /**
     * 获取手机待办 菜单列表
     *
     * @param contextPath
     * @param menuId 菜单编号
     * @return
     */
    Object getAppDealtListMenu(String contextPath, String menuId);
}
