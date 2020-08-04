package com.bank.manage.dao;

import com.bank.manage.dos.SvcStatuslogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务故障历史表
 *
 * @author
 * @date 2020-7-7
 */
public interface SvcStatuslogDao extends BaseMapper<SvcStatuslogDO> {

    List<SvcStatuslogDO> queryList(@Param("model") String termNum);

    SvcStatuslogDO queryLast(@Param("model")String strTermNum);
}
