package com.bank.manage.dao;

import com.bank.manage.dos.DcStatusDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 硬件状态表
 *
 * @author
 * @date 2020-7-7
 */
public interface DcStatusDao extends BaseMapper<DcStatusDO> {
    List<DcStatusDO> queryList(@Param("model") String termNum);
}