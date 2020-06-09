package com.bank.role.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.core.entity.BizException;
import com.bank.core.utils.PropertyUtil;
import com.bank.role.dao.RoleDao;
import com.bank.role.dao.RolePermissionDao;
import com.bank.role.dao.UserRoleDao;
import com.bank.role.dos.RoleDO;
import com.bank.role.dos.UserRoleDO;
import com.bank.role.dto.RoleDTO;
import com.bank.role.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleDO> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Resource
    RolePermissionDao rolePermissionDao;

    @Override
    public Integer insert(RoleDTO roleDTO) {
        /**
        String code = roleDTO.getRoleCode();
        RoleDO exitRole = this.roleDao.getByCode(code);
        if (null != exitRole) {
            throw new BizException("角色CODE已存在");
        }
        **/
        RoleDO roleDO = new RoleDO();
        roleDO.setRoleName(roleDTO.getRoleName());
        //自定义角色，自动生成角色编码
        roleDO.setRoleCode("ROLE" + System.currentTimeMillis());
        Date date = new Date();
        roleDO.setCreateTime(date);
        roleDO.setUpdateTime(date);
        return this.roleDao.insert(roleDO);
    }

    @Override
    public Integer updateById(RoleDTO roleDTO) {
        RoleDO roleDO = new RoleDO();
        roleDO.setRoleName(roleDTO.getRoleName());
        Date date = new Date();
        roleDO.setUpdateTime(date);
        return this.roleDao.updateById(roleDO);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deleteById(Integer id) {
        // 系统角色不允许删除
        RoleDO role = this.roleDao.selectById(id);
        if (role != null && role.getSystem().equals("1")) {
            throw new BizException("该角色为系统角色，不允许删除！");
        }

        //查看是否有用户拥有该角色，有的话不允许删除
        QueryWrapper<UserRoleDO> userRoleDOWrapper = new QueryWrapper<>();
        userRoleDOWrapper.eq("ROLE_ID", id);
        Integer selectCount = this.userRoleDao.selectCount(userRoleDOWrapper);
        if (selectCount > 0) {
            throw new BizException("该角色的用户不为空，删除角色失败！");
        }

        RoleDO roleDO = this.roleDao.selectById(id);
        if (roleDO != null) {
            roleDO.setRoleDelflag("1");
            roleDO.setUpdateTime(new Date());
            this.rolePermissionDao.deleteById(id);

            return this.roleDao.updateById(roleDO);
        }
        else {
            return 0;
        }
    }

    @Override
    public RoleDTO getById(Integer id) {
        RoleDO roleDO = this.roleDao.selectById(id);
        RoleDTO roleDTO = new RoleDTO();
        PropertyUtil.copyProperties(roleDO, roleDTO);
        return roleDTO;
    }

    @Override
    public Set<RoleDO> listByUserId(String id) {
        QueryWrapper<UserRoleDO> queryWrapperUserRole = new QueryWrapper<>();
        queryWrapperUserRole.eq("USER_ID", id);
        List<UserRoleDO> userRoleDos = this.userRoleDao.selectList(queryWrapperUserRole);

        Set<RoleDO> roleDTOs = new HashSet<>();
        for (int i = 0; i < userRoleDos.size(); i++) {
            QueryWrapper<RoleDO> queryWrapperRole = new QueryWrapper<>();
            queryWrapperRole.eq("ROLE_ID", userRoleDos.get(i).getRoleId());
            List<RoleDO> roleDos = this.roleDao.selectList(queryWrapperRole);
            for (RoleDO role : roleDos) {
                RoleDO roleDTO = new RoleDO();
                PropertyUtil.copyProperties(role, roleDTO);
                roleDTOs.add(roleDTO);
            }
        }
        return roleDTOs;
    }

    @Override
    public List<RoleDO> getAllRole() {
        QueryWrapper<RoleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ROLE_DELFLAG", 0);
        queryWrapper.orderByAsc("ROLE_ID");
        return this.roleDao.selectList(queryWrapper);
    }

    /**
     * 查询 当前用户是否具有 品宣部 角色权限
     * @param userId 用户编号
     * @param roleCode 角色编码
     * @return
     */
    @Override
    public List<RoleDO> getHeadOfficeUserRole(String userId, String roleCode) {
        return roleDao.getHeadOfficeUserRole(userId,roleCode);
    }
}
