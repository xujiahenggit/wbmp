package com.bank.manage.service;

import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.SatisfactAttendDO;
import com.bank.manage.dto.CheckWorkRejectDto;
import com.bank.manage.dto.SatisfactAssessmentActDto;
import com.bank.manage.dto.SatisfactAttendDto;
import com.bank.manage.vo.CheckWorkAttendQueryVo;
import com.bank.manage.vo.SatisfactAttendQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: Andy
 * @Date: 2020/5/29 21:12
 */
public interface SatisfactAttendService extends IService<SatisfactAttendDO> {
    /**
     * 待办列表
     * @param satisfactAttendQueryVo 查询参数
     * @param tokenUserInfo 当前登录的用户
     * @return
     */
    IPage<SatisfactAttendDto> getWaitList(SatisfactAttendQueryVo satisfactAttendQueryVo, TokenUserInfo tokenUserInfo);

    /**
     * 已办列表
     * @param satisfactAttendQueryVo 查询参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    IPage<SatisfactAttendDto> getAreadyList(SatisfactAttendQueryVo satisfactAttendQueryVo, TokenUserInfo tokenUserInfo);

    /**
     * 获取详细信息
     * @param satisfactAttendId 满意度考核编号
     * @return
     */
    SatisfactAssessmentActDto SelectSatisfactInfo(Integer satisfactAttendId);

    /**
     * 提交满意度考核
     * @param satisfactAssessmentActDto 满意度考核提交参数
     * @param tokenUserInfo 当前登录的用户
     * @return
     */
    boolean submit(SatisfactAssessmentActDto satisfactAssessmentActDto,TokenUserInfo tokenUserInfo);


    /**
     * 获取 月度满意度 考勤人数
     * @param date 日期
     * @param processPass 状态
     * @return
     */
    int getatisfactAttendNum(String date, String processPass);

    /**
     * 月满意度 未完成人数
     * @param checkWorkAttendQueryVo 查询参数
     * @return
     */
    IPage<CheckWorkRejectDto> getSasifactWaitPerple(CheckWorkAttendQueryVo checkWorkAttendQueryVo);
}
