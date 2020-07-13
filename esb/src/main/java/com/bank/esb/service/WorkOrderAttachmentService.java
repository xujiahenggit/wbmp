package com.bank.esb.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.esb.dos.WorkOrderAttachmentDO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 处理附件表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
public interface WorkOrderAttachmentService extends IService<WorkOrderAttachmentDO> {

    /**
     * 自定义分页
     * @param page
     * @param workOrderAttachment
     * @return
     */
    IPage<WorkOrderAttachmentDO> listPage(PageQueryModel pageQueryModel);

}
