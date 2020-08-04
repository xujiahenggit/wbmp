package com.bank.manage.dao;

import com.bank.manage.dos.TaccountingOrderDO;
import com.bank.manage.dto.TaccountingOrderDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 对账指令维护
 *
 * @author
 * @date 2020-7-4
 */
public interface TaccountingOrderDao extends BaseMapper<TaccountingOrderDO> {

    List<TaccountingOrderDTO> selectPageList(IPage page, @Param("model") TaccountingOrderDTO taccountingOrderDTO);

}
