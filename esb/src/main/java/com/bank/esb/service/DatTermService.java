package com.bank.esb.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.esb.dos.DatTermDO;
import com.bank.esb.vo.DatTermVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 终端信息表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatTermService extends IService<DatTermDO> {

    /**
     * 自定义分页
     * @param page
     * @param datTerm
     * @return
     */
    IPage<DatTermDO> listPage(PageQueryModel pageQueryModel);

}
