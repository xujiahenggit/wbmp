package com.bank.role.service.impl;

import com.bank.role.dao.RoleDao;
import com.bank.role.dao.RolePermissionDao;
import com.bank.role.dos.RolePermissionDO;
import com.bank.role.dto.RolePermissionDTO;
import com.bank.role.service.RolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhangfuqiang
 * @Date 2020/04/02
 */
@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    public RolePermissionServiceImpl(RolePermissionDao rolePermissionDao) {
        this.rolePermissionDao = rolePermissionDao;
    }

    @Override
    public Integer insert(String permission, Integer roleId) {

        QueryWrapper<RolePermissionDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ROLE_ID",roleId);
        List<RolePermissionDO> list =  rolePermissionDao.selectList(queryWrapper);

      List<RolePermissionDO> rolePermissionList = new ArrayList<RolePermissionDO>();
      if(list.size() >0){
        //需要删除用户之前的权限
          rolePermissionDao.delete(queryWrapper);
//          rolePermissionDao.deleteByRoleId(roleId);
      }
        if(permission !=null && !"".equals(permission)){
                String [] menuIds = permission.split(",");
                Date date = new Date();
                for(String munuId : menuIds){
                    RolePermissionDO  rolePermissionDO = new RolePermissionDO();
                    rolePermissionDO.setRoleId(roleId);
                    rolePermissionDO.setPermissionId(Integer.parseInt(munuId));
                    rolePermissionDO.setCreateTime(date);
                    rolePermissionDO.setUpdateTime(date);
                    rolePermissionList.add(rolePermissionDO);
                    rolePermissionDao.insert(rolePermissionDO);
                }
        }
//      return rolePermissionDao.batchInsert(rolePermissionList);
        return 1;
    }

    @Override
    public Integer deleteByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public List<RolePermissionDO> listByRoleId(Integer roleId) {

        QueryWrapper<RolePermissionDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ROLE_ID",roleId);
        List<RolePermissionDO> list =  rolePermissionDao.selectList(queryWrapper);
        return list ;
    }
}
