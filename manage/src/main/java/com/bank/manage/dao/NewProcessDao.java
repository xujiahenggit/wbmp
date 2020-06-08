package com.bank.manage.dao;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.NewProcessDO;
import com.bank.manage.dto.NewProcessInfoDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewProcessDao extends BaseMapper<NewProcessDO> {

    /**
     * 获取 审批类型列表
     * @return
     */
    List<String> listProcessModual();

    /**
     * 获取流程详细信息
     * @param processId 流程编号
     * @return
     */
    NewProcessInfoDto getProcessInfo(@Param(value = "processId") Integer processId);
}
