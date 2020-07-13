package com.bank.esb.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.esb.dos.DatSelfsvcbankDO;
import com.bank.esb.vo.DatSelfsvcbankVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 自助银行信息表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatSelfsvcbankService extends IService<DatSelfsvcbankDO> {

    /**
     * 自定义分页
     * @param page
     * @param datSelfsvcbank
     * @return
     */
    IPage<DatSelfsvcbankDO> listPage(PageQueryModel pageQueryModel);

}
