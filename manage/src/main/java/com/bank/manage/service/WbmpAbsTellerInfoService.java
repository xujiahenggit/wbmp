package com.bank.manage.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.manage.dos.WbmpAbsTellerInfoDO;
import com.bank.manage.vo.WbmpAbsTellerInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 柜员信息表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-03
 */
public interface WbmpAbsTellerInfoService extends IService<WbmpAbsTellerInfoDO> {

    /**
     * 自定义分页
     * @param page
     * @param wbmpAbsTellerInfo
     * @return
     */
    IPage<WbmpAbsTellerInfoDO> listPage(PageQueryModel pageQueryModel);

}
