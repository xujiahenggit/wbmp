package com.bank.manage.dao;

import com.bank.manage.dos.TransDO;
import com.bank.manage.dto.TransInfoDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 交易表
 *
 * @author
 * @date 2020-7-7
 */
public interface TransDao extends BaseMapper<TransDO> {
    List<TransDO> queryList(IPage page, @Param("model") TransDO transDO);

    List<TransInfoDTO> transCount(@Param("model") String termNum);

}
