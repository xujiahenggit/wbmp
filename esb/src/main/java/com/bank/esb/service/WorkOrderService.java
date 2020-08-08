package com.bank.esb.service;


import com.bank.core.entity.PageQueryModel;
import com.bank.esb.dos.WorkOrderDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 工单表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-15
 */
public interface WorkOrderService extends IService<WorkOrderDO> {

    /**
     * 自定义分页
     * @param page
     * @param workOrder
     * @return
     */
    IPage<WorkOrderDO> listPage(PageQueryModel pageQueryModel);

}
