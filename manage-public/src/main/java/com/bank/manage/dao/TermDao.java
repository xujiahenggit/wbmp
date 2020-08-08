package com.bank.manage.dao;

import com.bank.manage.dos.TermDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 终端表
 *
 * @author
 * @date 2020-7-7
 */
public interface TermDao extends BaseMapper<TermDO> {
    List<TermDO> queryList(@Param("model") String model);
}
