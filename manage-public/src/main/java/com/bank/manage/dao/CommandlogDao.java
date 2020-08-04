package com.bank.manage.dao;

import com.bank.manage.dos.CommandlogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 命令日志表
 *
 * @author
 * @date 2020-7-7
 */
public interface CommandlogDao extends BaseMapper<CommandlogDO> {
    List<CommandlogDO> queryList(IPage page, @Param("model") CommandlogDO commandlogDO);
}
