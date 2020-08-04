package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.TmapActverifyDO;
import com.bank.manage.dto.TmapActverifyDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TmapActverifyService extends IService<TmapActverifyDO> {

    /**
     * 插入帐号指定信息
     * @param tmapActverifyDTO
     * @return
     */
    Boolean save(TmapActverifyDTO tmapActverifyDTO);

    /**
     * 删除帐号指定信息
     * @param list
     * @return
     */
    Boolean delete(List<String> list);

    /**
     * 查询帐号指定信息
     * @param pageQueryModel
     * @return
     */
    IPage<TmapActverifyDO> queryList(PageQueryModel pageQueryModel);

    /**
     * 根据帐号查询帐号指定信息
     * @param acctno
     * @return
     */
    String selectByAcctno(String acctno);
}
