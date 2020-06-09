package com.bank.role.dao;

import com.bank.role.dos.RoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
public interface RoleDao extends BaseMapper<RoleDO> {

    /**
     * 根据角色code，获取角色信息
     *
     * @param code 角色标识码
     * @return 角色信息
     */
    RoleDO getByCode(String code);

    /**
     * 根据用户id，获取角色信息
     * @param id 用户id
     * @return 角色信息集合
     */
    Set<RoleDO> listByUserId(Integer id);

    /**
     * 查询 当前用户是否具有 品宣部 角色权限
     * @param userId 用户编号
     * @param roleCode 角色编码
     * @return
     */
    List<RoleDO> getHeadOfficeUserRole(@Param(value = "userId") String userId,@Param(value = "roleCode") String roleCode);
}
