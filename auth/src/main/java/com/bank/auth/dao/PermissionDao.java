package com.bank.auth.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.bank.auth.dos.PermissionDO;
import com.bank.auth.dto.PermissionDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
public interface PermissionDao extends BaseMapper<PermissionDO> {

    /**
     * 获取权限信息
     *
     * @param code 权限标识码
     * @return 权限信息
     */
    PermissionDO getByCode(String code);

    /**
     * 获取角色拥有的权限
     *
     * @param ids 角色id集合
     * @return 权限对象集合
     */
    Set<PermissionDO> listByRoleIds(@Param("ids") Set<Integer> ids);

    List<Map<String, Object>> selectParentList();

    List<Map<String, Object>> selectParentList2();

    Set<PermissionDTO> listAllPermissions();

    Set<PermissionDTO> listByRoleId(Integer roleId);

    Set<PermissionDTO> listPermissionByRoleIds(@Param("ids") Set<Integer> ids);

    List<String> listButtonPermissionByUserId(@Param("userId") String userId);

}
