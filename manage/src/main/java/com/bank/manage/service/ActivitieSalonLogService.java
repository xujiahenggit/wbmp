package com.bank.manage.service;


import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.ActivitieSalonLogDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 活动沙龙流水  服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-05
 */
public interface ActivitieSalonLogService extends IService<ActivitieSalonLogDO> {

    /**
     * 自定义分页
     * @return
     */
    IPage<ActivitieSalonLogDO> listPage(PageQueryModel pageQueryModel);

    Boolean saveActivitieSalonLog(ActivitieSalonLogDO activitieSalonLog);
}
