package com.bank.manage.service;


import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.ActivitieSalonDO;
import com.bank.manage.vo.CutActivitieSalonVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 活动沙龙  服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-05
 */
public interface ActivitieSalonService extends IService<ActivitieSalonDO> {

    /**
     * 自定义分页
     * @return
     */
    IPage<ActivitieSalonDO> listPage(PageQueryModel pageQueryModel);

    IPage<ActivitieSalonDO> queryActivitieSalon(PageQueryModel pageQueryModel);

    void cutActivitieSalon(CutActivitieSalonVo cutActivitieSalonVo);

    Boolean saveActivitieSalon(ActivitieSalonDO activitieSalon, TokenUserInfo tokenUserInfo);
}