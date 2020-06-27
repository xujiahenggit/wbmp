package com.bank.manage.dao;

import com.bank.manage.dos.MonthAttendDO;
import com.bank.manage.dto.CheckWorkRejectDto;
import com.bank.manage.dto.MonthAttendDto;
import com.bank.manage.vo.CheckWorkAttendQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Andy
 * @Date: 2020/5/29 16:33
 */
public interface MonthAttendDao extends BaseMapper<MonthAttendDO> {
    /**
     * 获取详情
     * @param monthAttendId 月度考勤编号
     * @return
     */
    MonthAttendDto getInfo(@Param(value = "monthAttendId") Integer monthAttendId);

    /**
     * 查询月度考勤 人数
     * @param date 日期
     * @param processPass 状态
     * @return
     */
    int getMonthAttendNum(@Param(value = "date") String date,@Param(value = "processPass") String processPass);

    /**
     * 查询驳回人数列表
     * @param page 分页对象
     * @param date 日期
     * @param type 类型
     * @return
     */
    IPage<CheckWorkRejectDto> getRejectList(Page<CheckWorkRejectDto> page,@Param(value = "date") String date,@Param(value = "type") Integer type);
}
