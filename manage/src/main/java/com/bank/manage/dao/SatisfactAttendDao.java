package com.bank.manage.dao;

import com.bank.manage.dos.SatisfactAttendDO;
import com.bank.manage.dto.CheckWorkRejectDto;
import com.bank.manage.dto.SatisfactAssessmentActDto;
import com.bank.manage.dto.SatisfactAttendDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/29 21:09
 */
public interface SatisfactAttendDao extends BaseMapper<SatisfactAttendDO> {
    /**
     * 按 不同类型 查找类别
     * @param page 分页对象
     * @param orgId 机构号
     * @param queryType 查询类别
     * @return
     */
    IPage<SatisfactAttendDto> getList(Page<SatisfactAttendDto> page, @Param(value = "orgId") String orgId,@Param(value = "queryType") String queryType);

    /**
     * 获取满意度考核 信息
     * @param satisfactAttendId 满意度考核编号
     * @return
     */
    SatisfactAssessmentActDto SelectSatisfactInfo(@Param(value = "satisfactAttendId") Integer satisfactAttendId);

    /**
     * 查询 基本信息
     * @param satisfactAttendId 满意度考核编号
     * @return
     */
    SatisfactAssessmentActDto SelectSatisfactBasicInfo(@Param(value = "satisfactAttendId") Integer satisfactAttendId);

    /**
     * 获取 当月已经审核通过的满意度条数
     * @param orgId
     * @return
     */
    List<SatisfactAttendDO> getSatisfactAttendPassSize(@Param(value = "orgId") String orgId, @Param(value = "queryDate") String queryDate);

    /**
     * 月度考勤数据
     * @param date 日期
     * @param processPass 类型 10 待办  20 已办
     * @return
     */
    int getatisfactAttendNum(@Param(value = "date") String date,@Param(value = "processPass") String processPass);

    /**
     * 月满意度 未完成人数列表
     * @param page 分页对象
     * @param date 日期
     * @return
     */
    IPage<CheckWorkRejectDto> getSasifactWaitPerple(Page<CheckWorkRejectDto> page,@Param(value = "date") String date);

    /**
     * 月度满意度 未完成人数
     * @param orgId
     * @param queryDate
     * @return
     */
    List<SatisfactAttendDO> getSatisfactAttendRejectSize(@Param(value = "orgId") String orgId,@Param(value = "queryDate") String queryDate);
}
