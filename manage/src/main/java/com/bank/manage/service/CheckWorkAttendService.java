package com.bank.manage.service;

import com.bank.manage.dto.CheckWorkAttendDto;
import com.bank.manage.dto.CheckWorkRejectDto;
import com.bank.manage.vo.CheckWorkAttendQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Author: Andy
 * @Date: 2020/6/2 14:53
 */
public interface CheckWorkAttendService {
    /**
     * 月度考勤数据
     * @param date 日期
     * @return
     */
    CheckWorkAttendDto getCheckWorkAttendData(String date);

    /**
     * 下载月度考勤数据
     * @param date
     * @return
     */
    String getDownFile(String date);

    /**
     * 月度考核驳回人数
     * @param checkWorkAttendQueryVo 查询参数
     * @param type 类型
     * @return
     */
    IPage<CheckWorkRejectDto> getRejectList(CheckWorkAttendQueryVo checkWorkAttendQueryVo,Integer type);

    /**
     * 月满意度 未完成人数
     * @param checkWorkAttendQueryVo 查询参数
     * @return
     */
    IPage<CheckWorkRejectDto> getSasifactWaitPerple(CheckWorkAttendQueryVo checkWorkAttendQueryVo);
}
