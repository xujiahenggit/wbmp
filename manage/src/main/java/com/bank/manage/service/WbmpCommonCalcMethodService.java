package com.bank.manage.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.manage.dos.WbmpCommonCalcMethodDO;
import com.bank.manage.vo.WbmpCommonCalcMethodVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 网点视图计算公式参数表 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-18
 */
public interface WbmpCommonCalcMethodService extends IService<WbmpCommonCalcMethodDO> {

    /**
     * 自定义分页
     * @param pageQueryModel
     * @return
     */
    IPage<WbmpCommonCalcMethodDO> listPage(PageQueryModel pageQueryModel);

}
