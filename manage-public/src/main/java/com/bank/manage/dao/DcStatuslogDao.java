package com.bank.manage.dao;

import com.bank.manage.dos.DcStatuslogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 硬件故障历史表
 *
 * @author
 * @date 2020-7-7
 */
public interface DcStatuslogDao extends BaseMapper<DcStatuslogDO> {
    List<DcStatuslogDO> queryList(@Param("model") String termNum);

    DcStatuslogDO queryLast(DcStatuslogDO dcStatuslogDO);
}
