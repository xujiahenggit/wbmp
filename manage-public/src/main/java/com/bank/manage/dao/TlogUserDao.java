package com.bank.manage.dao;

import com.bank.manage.dto.TlogUserDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 柜员操作日志表
 *
 * @author
 * @date 2020-7-10
 */
public interface TlogUserDao extends BaseMapper<TlogUserDTO> {
    List<TlogUserDTO> selectPageList(IPage page, @Param("model") TlogUserDTO tlogUserDTO);
}
