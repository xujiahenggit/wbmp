package com.bank.esb.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.esb.dos.DatBranchDO;
import com.bank.esb.vo.DatBranchVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 分行信息表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatBranchService extends IService<DatBranchDO> {

    /**
     * 自定义分页
     * @param page
     * @param datBranch
     * @return
     */
    IPage<DatBranchDO> listPage(PageQueryModel pageQueryModel);

}
