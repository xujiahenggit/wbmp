package com.bank.manage.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.manage.dos.WbmpOrgBalanceDO;
import com.bank.manage.vo.WbmpOrgBalanceVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 网点机构存款表 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-22
 */
public interface WbmpOrgBalanceService extends IService<WbmpOrgBalanceDO> {

    /**
     * 自定义分页
     * @param page
     * @param wbmpOrgBalance
     * @return
     */
    IPage<WbmpOrgBalanceDO> listPage(PageQueryModel pageQueryModel);

}
