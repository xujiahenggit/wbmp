package com.bank.manage.dao;

import com.bank.manage.dos.MsglogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 短信日志表
 *
 * @author
 * @date 2020-7-7
 */
public interface MsglogDao extends BaseMapper<MsglogDO> {

    List<MsglogDO> queryList(IPage page, @Param("model") MsglogDO msglogDO);
}
