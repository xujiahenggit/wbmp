package com.bank.icop.service;


import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.icop.dos.SoapLogDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * SOAP调用第三方接口日志 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-09
 */
public interface SoapLogService extends IService<SoapLogDO> {

    /**
     * 自定义分页
     * @param page
     * @param soapLog
     * @return
     */
    IPage<SoapLogDO> listPage(PageQueryModel pageQueryModel);

    TokenUserInfo getUserInfo(String token);
}
