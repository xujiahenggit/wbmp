package com.bank.manage.dao;

import com.bank.manage.dos.WorkSuppleDO;
import com.bank.manage.dto.WorkSuppleDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * @Author: Andy
 * @Date: 2020/5/28 16:28
 */
public interface WorkSuppleDao extends BaseMapper<WorkSuppleDO> {

    /**
     * 查询 列表
     * @param page 分页参数
     * @param orgId 机构号
     * @param queryType 查询类型 WAIT 待办 AREADY 已办
     * @return
     */
    IPage<WorkSuppleDto> getList(Page<WorkSuppleDO> page, @Param("orgId") String orgId,@Param("queryType") String queryType);

    /**
     * 获取详细信息
     * @param workSuppleId 编号
     * @return
     */
    WorkSuppleDto getDetailInfo(@Param(value = "workSuppleId") Integer workSuppleId);

    /**
     * 获取休息日加班时长
     * @param usherId 引导员编号
     * @param satisfactAttendYear 日期
     * @return
     */
    float getRestWorkLenghth(@Param(value = "usherId") Integer usherId,@Param(value = "satisfactAttendYear") LocalDate satisfactAttendYear,@Param(value = "type") Integer type);
}
