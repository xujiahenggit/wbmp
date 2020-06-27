package com.bank.manage.service;

import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.PartorlProcessDO;
import com.bank.manage.vo.PartorlProcessQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * @Author: Andy
 * @Date: 2020/5/27 21:05
 */
public interface PartorlProcessService extends IService<PartorlProcessDO> {

    /**
     * 查询待办列表
     * @param partorlProcessQueryVo 查询参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    IPage<PartorlProcessDO> getWaitList(PartorlProcessQueryVo partorlProcessQueryVo, TokenUserInfo tokenUserInfo);

    /**
     * 查询已办列表
     * @param partorlProcessQueryVo 查询参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    IPage<PartorlProcessDO> getAreadyList(PartorlProcessQueryVo partorlProcessQueryVo, TokenUserInfo tokenUserInfo);

    /**
     * 获取待办数目
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    int getWaitListNum(TokenUserInfo tokenUserInfo);
}
