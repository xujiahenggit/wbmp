package com.bank.manage.service;

import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.StaffDO;
import com.bank.manage.vo.StaffQueryVo;
import com.bank.manage.vo.StaffVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: Andy
 * @Date: 2020/4/9 9:47
 */
public interface StaffService extends IService<StaffDO> {
    /**
     * 多条件复合查询 行员信息
     * @param staffQueryVo 查询条件
     * @return
     */
    IPage<StaffDO> getPage(StaffQueryVo staffQueryVo);

    /**
     * 添加行员 信息
     * @param staffVo 行员信息
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    Boolean add(StaffVo staffVo, TokenUserInfo tokenUserInfo);

    /**
     * 更新 行员信息
     * @param staffVo 行员信息
     * @param tokenUserInfo
     * @return
     */
    Boolean modify(StaffVo staffVo, TokenUserInfo tokenUserInfo);

    /**
     * 发起 删除审核请求
     * 通过ID 删除行员信息
     * @param staffId 行员ID
     * @param tokenUserInfo
     * @return
     */
    Boolean deleteById(Integer staffId, TokenUserInfo tokenUserInfo);

    /**
     * 正式删除 逻辑
     * 通过ID 删除行员信息
     * @param staffId 行员ID
     * @return
     */
    Boolean deleteByIdTrue(Integer staffId);
}
