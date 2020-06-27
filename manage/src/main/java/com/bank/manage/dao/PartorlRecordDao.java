package com.bank.manage.dao;

import com.bank.manage.dos.PartorlRecordDO;
import com.bank.manage.dto.PartorlRecordDto;
import com.bank.manage.excel.partorl.PartorlExcelEntity;
import com.bank.manage.vo.PartorlRecordQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Andy
 * @Date: 2020/5/12 15:07
 */
public interface PartorlRecordDao extends BaseMapper<PartorlRecordDO> {
    /**
     * 用ID  获取巡查记录信息
     * @param recordId 巡查记录信息
     * @return
     */
    PartorlExcelEntity getRecordById(@Param(value = "recordId") Integer recordId);

    /**
     * 查询巡查记录
     * @param page  分页对象
     * @param partorlRecordQueryVo 查询参数
     * @return
     */
    IPage<PartorlRecordDto> selectRecordPage(Page<PartorlRecordDto> page,@Param(value = "partorlRecordQueryVo") PartorlRecordQueryVo partorlRecordQueryVo);
}
