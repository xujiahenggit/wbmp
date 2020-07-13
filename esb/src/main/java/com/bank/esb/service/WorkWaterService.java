package com.bank.esb.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.esb.dos.WorkWaterDO;
import com.bank.esb.vo.WorkWaterVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 工单流水表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
public interface WorkWaterService extends IService<WorkWaterDO> {

    /**
     * 自定义分页
     * @param page
     * @param workWater
     * @return
     */
    IPage<WorkWaterDO> listPage(PageQueryModel pageQueryModel);

}
