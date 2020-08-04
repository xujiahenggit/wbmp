package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.TaccountingOrderDO;
import com.bank.manage.dto.TaccountingOrderDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TaccountingOrderService extends IService<TaccountingOrderDO> {

    /**
     * 插入对账指令维护信息
     * @param taccountingOrderDTO
     * @return
     */
    Boolean save(TaccountingOrderDTO taccountingOrderDTO);

    /**
     * 删除对账指令维护信息
     * @param list
     * @return
     */
    Boolean delete(List<String> list);

    /**
     * 查询对账指令维护信息
     * @param pageQueryModel
     * @return
     */
    IPage<TaccountingOrderDTO> queryList(PageQueryModel pageQueryModel);

    /**
     * 修改对账指令维护信息
     * @param taccountingOrderDTO
     * @return
     */
    Boolean updateTaccounTingOrder(TaccountingOrderDTO taccountingOrderDTO);
}
