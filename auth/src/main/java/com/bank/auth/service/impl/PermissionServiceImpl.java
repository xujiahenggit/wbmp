package com.bank.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bank.auth.dao.PermissionDao;
import com.bank.auth.dos.PermissionDO;
import com.bank.auth.dto.AuthDTO;
import com.bank.auth.dto.PermissionDTO;
import com.bank.auth.service.PermissionService;
import com.bank.auth.util.MenuTree;
import com.bank.core.entity.AuthException;
import com.bank.core.entity.BizException;
import com.bank.core.entity.Menu;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.PropertyUtil;
import com.bank.role.dao.RolePermissionDao;
import com.bank.role.dos.RoleDO;
import com.bank.role.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * ClassName: PermissionServiceImpl
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/03/31 17:24:34
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionDao permissionDao;

    @Resource
    private RolePermissionDao rolePermissionDao;

    @Resource
    private RoleService roleService;

    @Override
    public Integer insert(PermissionDTO permissionDTO) {
        PermissionDO permissionDO = PermissionDO.builder()
                .permissionName(permissionDTO.getPermissionName())
                .permissionCode(permissionDTO.getPermissionCode())
                .routerPath(StringUtils.isBlank(permissionDTO.getRouterPath()) ? null : permissionDTO.getRouterPath())
                .parentId(permissionDTO.getParentId())
                .parentDesc(permissionDTO.getParentDesc())
                .icon(StringUtils.isBlank(permissionDTO.getIcon()) ? null : permissionDTO.getIcon())
                .component(StringUtils.isBlank(permissionDTO.getComponent()) ? null : permissionDTO.getComponent())
                .sort(permissionDTO.getSort())
                .permissionType(permissionDTO.getPermissionType())
                .permissionTypeDesc(permissionDTO.getPermissionTypeDesc())
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        return this.permissionDao.insert(permissionDO);
    }

    @Override
    public Integer updateById(PermissionDTO permissionDTO) {
        PermissionDO permissionDO = PermissionDO.builder()
                .permissionId(permissionDTO.getPermissionId())
                .permissionCode(permissionDTO.getPermissionCode())
                .permissionName(permissionDTO.getPermissionName())
                .routerPath(StringUtils.isBlank(permissionDTO.getRouterPath()) ? null : permissionDTO.getRouterPath())
                .parentId(permissionDTO.getParentId())
                .parentDesc(permissionDTO.getParentDesc())
                .icon(StringUtils.isBlank(permissionDTO.getIcon()) ? null : permissionDTO.getIcon())
                .component(StringUtils.isBlank(permissionDTO.getComponent()) ? null : permissionDTO.getComponent())
                .sort(permissionDTO.getSort())
                .permissionType(permissionDTO.getPermissionType())
                .permissionTypeDesc(permissionDTO.getPermissionTypeDesc())
                .updateTime(new Date())
                .build();

        return this.permissionDao.updateById(permissionDO);
    }

    @Override
    public Integer deleteById(Integer id) {
        /**
         * 校验该权限是否已分配给角色
         */
        Integer count = this.rolePermissionDao.selectRoleCount(id);
        if (count > 0) {
            throw new BizException("该权限已被赋予角色，不允许删除");
        }
        PermissionDO permissionDO = PermissionDO.builder()
                .permissionId(id)
                .updateTime(new Date())
                .permissionDelflag("1")
                .build();

        return this.permissionDao.updateById(permissionDO);
    }

    @Override
    public IPage<PermissionDO> selectPage(PageQueryModel pageQueryModel) {
        Page<PermissionDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }

        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String permissionName = (String) queryParam.get("permissionName");
        String permissionCode = (String) queryParam.get("permissionCode");
        String permissionType = (String) queryParam.get("permissionType");

        QueryWrapper<PermissionDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(permissionName), "PERMISSION_NAME", permissionName)
                .likeRight(StringUtils.isNotBlank(permissionCode), "PERMISSION_CODE", permissionCode)
                .eq(StringUtils.isNotBlank(permissionType), "PERMISSION_TYPE", permissionType)
                .ne("PERMISSION_CODE", "root")
                .eq("PERMISSION_DELFLAG", "0");

        return this.permissionDao.selectPage(page, queryWrapper);
    }

    @Override
    public PermissionDO selectById(Integer id) {
        return this.permissionDao.selectById(id);
    }

    @Override
    public Set<PermissionDTO> listByRoleIds(Set<Integer> ids) {
        Set<PermissionDO> permissionDOs = this.permissionDao.listByRoleIds(ids);
        Set<PermissionDTO> permissionsDTOs = new HashSet<>();
        for (PermissionDO permissionDO : permissionDOs) {
            PermissionDTO permissionDTO = new PermissionDTO();
            PropertyUtil.copyProperties(permissionDO, permissionDTO);
            permissionsDTOs.add(permissionDTO);
        }
        return permissionsDTOs;
    }

    @Override
    public Set<PermissionDTO> listAllPermissions() {
        return this.permissionDao.listAllPermissions();
    }

    @Override
    public List<Map<String, Object>> selectParentList() {
        return this.permissionDao.selectParentList();
    }

    @Override
    public Set<PermissionDTO> listByRoleId(Integer roleId) {
        return this.permissionDao.listByRoleId(roleId);
    }

    @Override
    public Set<PermissionDTO> listPermissionByRoleIds(Set<Integer> ids) {
        return this.permissionDao.listPermissionByRoleIds(ids);
    }

    /**
     * 获取用户菜单
     * @param id 用户编号
     * @return
     */
    @Override
    public Object getUserMenu(String id) {
        Set<PermissionDTO> permissionDTOS = checkPermission(id);
        try {
            permissionDTOS = permissionDTOS.stream().filter(e -> !e.getPermissionCode().startsWith("app")).collect(Collectors.toSet());
            TreeSet<Menu> menuTreeSet = MenuTree.initMenuTree(permissionDTOS, null, 2);
            return menuTreeSet;
        }
        catch (Exception e) {
            if (e instanceof AuthException) {
                throw new AuthException(((AuthException) e).getErrorMsg());
            }
            else {
                throw new BizException("获取用户菜单失败");
            }
        }
    }

    @Resource
    ConfigFileReader configFileReader;

    @Override
    public Object getAppMenus(String contextPath, String id) {
        String ip = this.configFileReader.getTomcatBaseIp();
        String suffix=ip+contextPath;
        Set<PermissionDTO> permissionDTOS = checkPermission(id).stream()
                .filter(e -> e.getPermissionCode().startsWith("app"))
                .map(e -> {
                    if (!StrUtil.isBlankIfStr(e.getIcon())) {
                        e.setIcon(suffix + e.getIcon());
                    }
                    return e;
                })
                .collect(Collectors.toSet());
        TreeSet<PermissionDTO> treeSet = new TreeSet<>(Comparator.comparing(PermissionDTO::getSort).thenComparing(PermissionDTO::getPermissionId));
        treeSet.addAll(permissionDTOS);
        return treeSet;
    }

    @Override
    @Cacheable(cacheNames = "com.bank.auth.service.impl.PermissionServiceImpl.getAuthDTO", key = "#id")
    public AuthDTO getAuthDTO(String id) {
        Set<RoleDO> roles = this.roleService.listByUserId(id);
        Set<Integer> roleIds = new HashSet<>();
        for (RoleDO role : roles) {
            roleIds.add(role.getRoleId());
        }
        AuthDTO authDTO = new AuthDTO();
        Set<PermissionDTO> perms = new HashSet<>();
        if (roleIds.size() == 0) {
            authDTO.setRoles(roles);
            authDTO.setPermissions(perms);
        }
        else {
            // 设置角色
            authDTO.setRoles(roles);
            // 设置权限
            perms = listByRoleIds(roleIds);
            authDTO.setPermissions(perms);
        }
        return authDTO;
    }

    private Set<PermissionDTO> checkPermission(String id) {
        Set<RoleDO> list = this.roleService.listByUserId(id);
        if (list.size() <= 0) {
            throw new AuthException("当前用户未授权，禁止登录。");
        }
        Set<Integer> ids = new HashSet<>();
        for (RoleDO roleDO : list) {
            ids.add(roleDO.getRoleId());
        }
        Set<PermissionDTO> permissionDTOS = listPermissionByRoleIds(ids);
        if (permissionDTOS.size() <= 0) {
            throw new AuthException("用户权限不足，禁止登录。");
        }
        return permissionDTOS;
    }

    @Override
    public List<Map<String, Object>> selectParentList2() {
        return this.permissionDao.selectParentList2();
    }

    @Override
    public String[] getButtonPermission(String userId) {
        List<String> permissions = this.permissionDao.listButtonPermissionByUserId(userId);
        return permissions.toArray(new String[permissions.size()]);
    }

    /**
     * 获取手机端APP 待办菜单列表
     *
     * @param contextPath
     * @param userId 当前登录用户信息
     * @return
     */
    @Override
    public Object getAppDealtListMenu(String contextPath, String userId) {
        String ip = this.configFileReader.getTomcatBaseIp();
        String suffix=ip+contextPath;
        Set<PermissionDTO> permissionDTOS = checkPermission(userId).stream()
                .filter(e -> e.getPermissionCode().startsWith("todo"))
                .map(e -> {
                    if (!StrUtil.isBlankIfStr(e.getIcon())) {
                        e.setIcon(suffix + e.getIcon());
                    }
                    return e;
                })
                .collect(Collectors.toSet());
        TreeSet<PermissionDTO> treeSet = new TreeSet<>(Comparator.comparing(PermissionDTO::getSort));
        treeSet.addAll(permissionDTOS);
        return treeSet;
    }

}
