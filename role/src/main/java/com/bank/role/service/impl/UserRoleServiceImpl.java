package com.bank.role.service.impl;

import com.bank.core.entity.BizException;
import com.bank.role.dao.UserRoleDao;
import com.bank.role.dos.UserRoleDO;
import com.bank.role.service.UserRoleService;
import com.bank.role.vo.UserRoleVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/7 15:29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleDO> implements UserRoleService {

    @Resource
    private UserRoleDao userRoleDao;
    /**
     * 设置用户角色
     * 1.删除用户当前所有的角色关联
     * 2.在进行 关联插入
     * @param userRoleVo
     * @return 成功返回True  失败直接抛出异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean setUserRole(UserRoleVo userRoleVo) {
        try{
            //1.删除 用户关联的所有角色
            UpdateWrapper<UserRoleDO> userRoleDOUpdateWrapper=new UpdateWrapper<>();
            userRoleDOUpdateWrapper.eq("USER_ID",userRoleVo.getUserId());
            userRoleDao.delete(userRoleDOUpdateWrapper);
            if(userRoleVo.getRoleIds().size()>0){
                //2.保存角色关联 关系
                for (Integer item:userRoleVo.getRoleIds()) {
                    UserRoleDO userRoleDO=new UserRoleDO();
                    userRoleDO.setUserId(userRoleVo.getUserId());
                    userRoleDO.setRoleId(item);
                    userRoleDO.setCreateTime(LocalDateTime.now());
                    userRoleDO.setUpdateTime(LocalDateTime.now());
                    userRoleDao.insert(userRoleDO);
                }
            }
            return true;
        }catch (Exception e){
            throw new BizException("设置用户角色失败");
        }
    }

    /**
     * 获取用户 角色列表
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<UserRoleDO> getUserRoleList(String userId) {
        QueryWrapper<UserRoleDO> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("ROLE_ID");
        queryWrapper.eq("USER_ID",userId);
        userRoleDao.selectList(queryWrapper);
        return userRoleDao.selectList(queryWrapper);
    }

}
