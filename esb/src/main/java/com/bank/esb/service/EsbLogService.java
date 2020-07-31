package com.bank.esb.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.esb.dos.EsbLogDO;
import com.bank.esb.vo.EsbLogVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 *  服务类
 *
 * @author 代码自动生成
 * @since 2020-07-22
 */
public interface EsbLogService extends IService<EsbLogDO> {

    /**
     * 自定义分页
     * @param page
     * @param esbLog
     * @return
     */
    IPage<EsbLogDO> listPage(PageQueryModel pageQueryModel);

}
