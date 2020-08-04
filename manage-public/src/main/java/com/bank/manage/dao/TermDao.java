package com.bank.manage.dao;

import com.bank.manage.dos.TermDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 终端表
 *
 * @author
 * @date 2020-7-7
 */
public interface TermDao extends BaseMapper<TermDO> {
    List<TermDO> queryList();
}
