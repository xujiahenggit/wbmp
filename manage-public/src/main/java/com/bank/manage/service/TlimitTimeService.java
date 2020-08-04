package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.TlimitTimeDO;
import com.bank.manage.dto.TlimitTimeDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TlimitTimeService extends IService<TlimitTimeDO> {

    /**
     * 逾期催收时限信息查询
     * @param pageQueryModel
     * @return
     */
    IPage<TlimitTimeDO> queryList(PageQueryModel pageQueryModel);

    /**
     * 修改逾期催收时限信息
     * @param tlimitTimeDTO
     * @return
     */
    Boolean updateTlimitTime(TlimitTimeDTO tlimitTimeDTO);

    /**
     * 根据机构号查询
     * @param branch
     * @return
     */
    Boolean selectByBranch(String branch);

    /**
     * 插入逾期催收时限信息
     * @param tlimitTimeDTO
     * @return
     */
    Boolean save(TlimitTimeDTO tlimitTimeDTO);

    /**
     * 删除逾期催收时限信息
     * @param list
     * @return
     */
    Boolean delete(List<String> list);
}
