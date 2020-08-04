package com.bank.manage.dao;

import com.bank.manage.dos.TlogTellerDO;
import com.bank.manage.dto.TlogTellerDTO;
import com.bank.manage.vo.TlogTellerVO;
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
public interface TlogTellerDao extends BaseMapper<TlogTellerDO> {

    List<TlogTellerVO> selectPageList(IPage page, @Param("model") TlogTellerDTO tlogTellerDTO);


}
