package com.bank.manage.service;


import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.VersionsDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 应用版本维护 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-01
 */
public interface VersionsService extends IService<VersionsDO> {

    /**
     * 自定义分页
     * @param page
     * @param versions
     * @return
     */
    IPage<VersionsDO> listPage(PageQueryModel pageQueryModel);

}
