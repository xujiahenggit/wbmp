package com.bank.manage.service;

import com.bank.core.entity.FileDo;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.WorkSuppleDO;
import com.bank.manage.dto.FacilitatorDto;
import com.bank.manage.dto.WorkSuppleDto;
import com.bank.manage.vo.FacilitatorVo;
import com.bank.manage.vo.WorkSupplePassRejectVo;
import com.bank.manage.vo.WorkSuppleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/28 16:28
 */
public interface WorkSuppleService extends IService<WorkSuppleDO> {

    /**
     * 待办列表
     * @param workSuppleQueryVo 查询参数
     * @return
     */
    IPage<WorkSuppleDto> getWaitList(WorkSuppleQueryVo workSuppleQueryVo);

    /**
     * 已办列表
     * @param workSuppleQueryVo 查询参数
     * @return
     */
    IPage<WorkSuppleDto> getPassList(WorkSuppleQueryVo workSuppleQueryVo);

    /**
     * 审核通过
     * @param workSupplePassRejectVo 审核参数
     * @param tokenUserInfo 当前用户信息
     * @return
     */
    Boolean passProcess(WorkSupplePassRejectVo workSupplePassRejectVo, TokenUserInfo tokenUserInfo);

    /**
     * 审核驳回
     * @param workSupplePassRejectVo 审核参数
     * @param tokenUserInfo 当前用户信息
     * @return
     */
    Boolean rejectProcess(WorkSupplePassRejectVo workSupplePassRejectVo, TokenUserInfo tokenUserInfo);

    /**
     * 通过ID 获取详情
     * @param workSuppleId 编号
     * @return
     */
    WorkSuppleDto getDetailInfo(Integer workSuppleId);

    /**
     * 上传图片
     * @param file 文件
     * @return
     */
    FileDo uploadFile(MultipartFile file);

    /**
     * 新增加班申请
     * @param workSuppleDto 参数
     * @return
     */
    boolean saveWorkSupple(WorkSuppleDto workSuppleDto);

    /**
     * 统计加班时长
     * @param usherId 引导员编号
     * @param satisfactAttendYear 日期
     * @param type 0：工作日加班；1：节假日加班
     * @return
     */
    float getRestWorkLenghth(Integer usherId, LocalDate satisfactAttendYear,Integer type);

    /**
     * 引导员 -待办列表
     * @param facilitatorVo 查询参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    IPage<FacilitatorDto> getAllWaitList(FacilitatorVo facilitatorVo, TokenUserInfo tokenUserInfo);

    /**
     * 引导员 已办列表
     * @param facilitatorVo 查询参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    IPage<FacilitatorDto> getAllAredyList(FacilitatorVo facilitatorVo, TokenUserInfo tokenUserInfo);
}
