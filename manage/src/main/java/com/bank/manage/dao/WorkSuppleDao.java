package com.bank.manage.dao;

import com.bank.manage.dos.WorkSuppleDO;
import com.bank.manage.dto.FacilitatorDto;
import com.bank.manage.dto.WorkSuppleDto;
import com.bank.manage.vo.FacilitatorVo;
import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

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

    /**
     * 查询所有引导员 待办列表
     * @param page  分页对象
     * @param queryType 查询类型  分 待办 已办
     * @param orgId 机构号
     * @param roleType 角色信息
     * @return
     */
    @SqlParser(filter=true)
    IPage<FacilitatorDto> getAllList(Page<FacilitatorDto> page,@Param(value = "queryType") String queryType,@Param(value = "orgId") String orgId,@Param(value = "roleType") boolean roleType);

    /**
     * 查询引导员已办列表
     * @param page 分页对象
     * @param orgId 机构编号
     * @param flag 是否具有权限
     * @return
     */
    IPage<FacilitatorDto> getAeadyList(Page<FacilitatorDto> page,@Param(value = "orgId") String orgId, @Param(value = "roleType") boolean flag);
}
