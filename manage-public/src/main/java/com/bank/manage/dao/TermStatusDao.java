package com.bank.manage.dao;

import com.bank.manage.dos.TermStatusDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 终端表
 *
 * @author
 * @date 2020-7-7
 */
public interface TermStatusDao extends BaseMapper<TermStatusDO> {
    void updateByTime(@Param("model") LocalDateTime agentTime);
}
