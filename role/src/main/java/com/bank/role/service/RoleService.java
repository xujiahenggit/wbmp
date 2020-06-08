package com.bank.role.service;

import com.bank.role.dos.RoleDO;
import com.bank.role.dto.RoleDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * 角色服务接口
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
public interface RoleService extends IService<RoleDO> {

    /**
     * 插入角色信息
     *
     * @param roleDTO 角色信息
     * @return 插入条数
     */
    Integer insert(RoleDTO roleDTO);

    /**
     * 更新角色信息
     *
     * @param roleDTO 角色信息
     * @return 更新条数
     */
    Integer updateById(RoleDTO roleDTO);

    /**
     * 删除角色信息
     *
     * @param id 角色唯一标识
     * @return 删除条数
     */
    Integer deleteById(Integer id);

    /**
     * 获取角色信息
     *
     * @param id 角色唯一标识
     * @return 符合条件的角色信息
     */
    RoleDTO getById(Integer id);

    /**
     * 根据用户id，获取角色信息
     *
     * @param id 用户id
     * @return 角色对象集合
     */
    Set<RoleDO> listByUserId(String id);

    List<RoleDO> getAllRole();
}
