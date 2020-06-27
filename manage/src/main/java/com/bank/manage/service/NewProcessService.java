package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.NewProcessDO;
import com.bank.manage.dto.NewProcessDTO;
import com.bank.manage.dto.NewProcessInfoDto;
import com.bank.manage.vo.NewProcessPassVo;
import com.bank.manage.vo.NewProcessQueryVo;
import com.bank.manage.vo.NewProcessRejectVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface NewProcessService extends IService<NewProcessDO> {
    /**
     * 待审核 流程列表
     * @param newProcessQueryVo 查询参数
     * @param tokenUserInfo 当前登录用户的信息
     * @return
     */
    IPage<NewProcessDO> getWaitProcessList(NewProcessQueryVo newProcessQueryVo,TokenUserInfo tokenUserInfo);

    /**
     * 获取首页审核待办数量
     * @param userInfo 当前登录用户信息
     * @return
     */
    Integer getWaitProcessNum(TokenUserInfo userInfo);

    /**
     * 已办理审核列表
     * @param newProcessQueryVo 查询参数
     * @param tokenUserInfo 当前用户所登录的信息
     * @return
     */
    IPage<NewProcessDO> getPassProcessList(NewProcessQueryVo newProcessQueryVo, TokenUserInfo tokenUserInfo);

    /**
     * 流程审核通过
     * @param newProcessPassVo 流程参数
     * @param tokenUserInfo 当前登录的用户信息
     * @return
     */
    boolean passPorcess(NewProcessPassVo newProcessPassVo, TokenUserInfo tokenUserInfo);

    /**
     * 驳回 审批
     * @param newProcessRejectVo 参数
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    boolean rejectProcess(NewProcessRejectVo newProcessRejectVo,TokenUserInfo tokenUserInfo);

    /**
     * 创建 流程
     * 1.创建流程
     * 2.创建历史
     * @param newProcessDO 流程主体
     * @param tokenUserInfo 创建用户信息
     * @return
     */
    boolean createProcess(NewProcessDO newProcessDO,TokenUserInfo tokenUserInfo);

    /**
     * 获取审批类型列表
     * @return
     */
    List<String> listProcessModual();

    /**
     * 获取 审核流程详细信息
     * @param processId 流程编号
     * @return
     */
    NewProcessInfoDto getProcessInfo(Integer processId);

    /**
     * 我的申请列表
     * @param newProcessQueryVo 查询参数
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    IPage<NewProcessDO> getSelfList(NewProcessQueryVo newProcessQueryVo, TokenUserInfo tokenUserInfo);

    /**
     * 撤销审核
     * @param processId 流程编号
     * @param tokenUserInfo
     * @return
     */
    boolean revokeProcess(Integer processId, TokenUserInfo tokenUserInfo);

    /**
     * 未办结 流程查询
     * @param newProcessQueryVo 查询条件
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    IPage<NewProcessDO> getMyProcess(NewProcessQueryVo newProcessQueryVo, TokenUserInfo tokenUserInfo);
}
