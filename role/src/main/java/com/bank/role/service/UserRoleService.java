package com.bank.role.service;

import com.bank.role.dos.UserRoleDO;
import com.bank.role.vo.UserRoleVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/7 15:28
 */
public interface UserRoleService extends IService<UserRoleDO> {

    /**
     * 设置用户角色
     * @param userRoleVo
     * @return
     */
    Boolean setUserRole(UserRoleVo userRoleVo);

    /**
     * 获取用户 角色列表
     * @param userId 用户ID
     * @return
     */
    List<UserRoleDO> getUserRoleList(String userId);
}
