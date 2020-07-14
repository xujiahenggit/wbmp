package com.bank.esb.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.esb.dos.DatBankDO;
import com.bank.esb.vo.DatBankVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 银行信息表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatBankService extends IService<DatBankDO> {

    /**
     * 自定义分页
     * @param page
     * @param datBank
     * @return
     */
    IPage<DatBankDO> listPage(PageQueryModel pageQueryModel);

}
