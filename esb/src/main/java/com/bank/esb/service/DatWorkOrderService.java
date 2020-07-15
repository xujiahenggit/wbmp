package com.bank.esb.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.esb.dos.DatWorkOrderDO;
import com.bank.esb.vo.DatWorkOrderVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 故障工单表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatWorkOrderService extends IService<DatWorkOrderDO> {

    /**
     * 自定义分页
     * @param page
     * @param datWorkOrder
     * @return
     */
    IPage<DatWorkOrderDO> listPage(PageQueryModel pageQueryModel);

}
