package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.TmapKeyacctverifyDO;
import com.bank.manage.dto.TmapKeyacctverifyDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TmapKeyacctverifyService extends IService<TmapKeyacctverifyDO> {

    /**
     * 插入指定重点账号信息
     * @param tmapKeyacctverifyDTO
     * @return
     */
    Boolean save(TmapKeyacctverifyDTO tmapKeyacctverifyDTO);

    /**
     * 删除指定重点账号信息
     * @param list
     * @return
     */
    Boolean delete(List<String> list);

    /**
     * 查询指定重点账号信息
     * @param pageQueryModel
     * @return
     */
    IPage<TmapKeyacctverifyDO> queryList(PageQueryModel pageQueryModel);

    /**
     * 根据帐号查询指定重点账号信息
     * @param acctno
     * @return
     */
    String selectByAcctno(String acctno);

    /**
     * 启用停用指定重点账号信息
     * @param tmapKeyacctverifyDTO
     * @return
     */
    Boolean switchByAcctno(TmapKeyacctverifyDTO tmapKeyacctverifyDTO);
}
