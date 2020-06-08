package com.bank.manage.dao;

import com.bank.manage.dos.PartorlProveDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/12 15:06
 */
public interface PartorlProveDao extends BaseMapper<PartorlProveDO> {
    /**
     * 用 巡查记录内容ID 批量删除 证明文件
     * @param listRecordItemIds 巡查内容ID
     */
    void deleteProveByRecordItemIds(@Param(value = "listRecordItemIds") List<Integer> listRecordItemIds);

    /**
     * 用ID 查找证明文件路径列表
     * @param partorlRecordId 巡查记录ID
     * @return
     */
    List<String> selectFilePathByRecordId(@Param(value = "partorlRecordId") Integer partorlRecordId);
}
