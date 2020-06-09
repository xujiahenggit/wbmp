package com.bank.manage.dao;

import com.bank.manage.dos.CardSuppleDO;
import com.bank.manage.dto.CardSuppleDto;
import com.bank.manage.dto.InfoMessageDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Andy
 * @Date: 2020/5/28 10:43
 */
public interface CardSuppleDao extends BaseMapper<CardSuppleDO> {
    /**
     * 获取待办列表
     * @param page 分页对象
     * @param orgId 机构号
     * @return
     */
    IPage<CardSuppleDto> getList(Page<CardSuppleDto> page,@Param(value = "orgId") String orgId);

    /**
     * 获取通知列表
     * @param page 分页参数
     * @param uherId 引导员编号
     * @return
     */
    IPage<InfoMessageDto> getInfomationList(Page<CardSuppleDto> page, @Param(value = "uherId") Integer uherId);

    /**
     * 通过ID 获取详细信息
     * @param cardSuppleId 编号
     * @return
     */
    CardSuppleDto getInfo(@Param(value = "cardSuppleId") Integer cardSuppleId);

    /**
     * 获取已办列表
     * @param page 分页对象
     * @param orgId 机构ID
     * @return
     */
    IPage<CardSuppleDto> getAreadyList(Page<CardSuppleDto> page,@Param(value = "orgId") String orgId);
}
