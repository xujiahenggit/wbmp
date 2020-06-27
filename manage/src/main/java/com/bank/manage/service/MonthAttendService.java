package com.bank.manage.service;

import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.MonthAttendDO;
import com.bank.manage.dto.CheckWorkRejectDto;
import com.bank.manage.dto.MonthAttendDto;
import com.bank.manage.vo.CheckWorkAttendQueryVo;
import com.bank.manage.vo.MonthAttendPassRejectVo;
import com.bank.manage.vo.MonthAttendQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: Andy
 * @Date: 2020/5/29 16:34
 */
public interface MonthAttendService extends IService<MonthAttendDO> {
    /**
     * 待办列表
     * @param monthAttendQueryVo 查询参数
     * @return
     */
    IPage<MonthAttendDO> getWaitList(MonthAttendQueryVo monthAttendQueryVo);

    /**
     * 已办列表
     * @param monthAttendQueryVo 参数参数
     * @return
     */
    IPage<MonthAttendDO> getAreadyList(MonthAttendQueryVo monthAttendQueryVo);

    /**
     * 审核通过
     * @param monthAttendPassRejectVo 审核参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    boolean passMonthAttend(MonthAttendPassRejectVo monthAttendPassRejectVo, TokenUserInfo tokenUserInfo);

    /**
     * 驳回审批
     * @param monthAttendPassRejectVo 审核参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    boolean rejectMonthAttend(MonthAttendPassRejectVo monthAttendPassRejectVo, TokenUserInfo tokenUserInfo);

    /**
     * 获取详细信息
     * @param monthAttendId 月度考勤编号
     * @return
     */
    MonthAttendDto getInfo(Integer monthAttendId);

    /**
     * 查询 月度考核 人数
     * @param date 日期
     * @param processPass 状态
     * @return
     */
    int getMonthAttendNum(String date, String processPass);

    /**
     * 获取月度考核驳回人数列表
     * @param checkWorkAttendQueryVo 查询参数
     * @param type
     * @return
     */
    IPage<CheckWorkRejectDto> getRejectList(CheckWorkAttendQueryVo checkWorkAttendQueryVo, Integer type);
}
