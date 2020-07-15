package com.bank.esb.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.esb.dos.DatTermstatusDO;
import com.bank.esb.vo.DatTermstatusVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 终端状态表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatTermstatusService extends IService<DatTermstatusDO> {

    /**
     * 自定义分页
     * @param page
     * @param datTermstatus
     * @return
     */
    IPage<DatTermstatusDO> listPage(PageQueryModel pageQueryModel);

}
