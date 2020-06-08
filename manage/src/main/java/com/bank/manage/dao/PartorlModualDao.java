package com.bank.manage.dao;

import com.bank.manage.dos.PartorlContentDO;
import com.bank.manage.dos.PartorlModualDO;
import com.bank.manage.dto.PartorlContentDto;
import com.bank.manage.dto.PartorlDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/11 11:24
 */
public interface PartorlModualDao extends BaseMapper<PartorlModualDO> {

    /**
     * 直接用 模块ID 获取查询内容列表
     * @param partorlModualId
     * @return
     */
    List<PartorlContentDto> SelectPartorlContentNoRecord(@Param(value = "partorlModualId") Integer partorlModualId);

    /**
     * 用巡查记录编号获取巡查内容列表
     * @param partorlRecordId 巡查记录编号
     * @return
     */
    List<PartorlDto> getPartorlListByRecordIdList(@Param(value = "partorlRecordId") Integer partorlRecordId);

}
