package com.bank.esb.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.esb.dos.DatSubbranchDO;
import com.bank.esb.vo.DatSubbranchVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 支行信息表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatSubbranchService extends IService<DatSubbranchDO> {

    /**
     * 自定义分页
     * @param page
     * @param datSubbranch
     * @return
     */
    IPage<DatSubbranchDO> listPage(PageQueryModel pageQueryModel);

}
