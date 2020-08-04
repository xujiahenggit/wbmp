package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.TacctInfoDO;
import com.bank.manage.dto.TacctInfoDTO;
import com.bank.manage.vo.TacctInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TacctInfoService extends IService<TacctInfoDO> {

    /**
     * 插入帐号信息
     * @param tacctInfoDTO
     * @return
     */
    Boolean save(TacctInfoDTO tacctInfoDTO);

    /**
     * 删除帐号信息
     * @param list
     * @return
     */
    Boolean delete(List<String> list);

    /**
     * 查询帐号信息
     * @param pageQueryModel
     * @return
     */
    IPage<TacctInfoVO> queryList(PageQueryModel pageQueryModel);

    /**
     * 根据帐号查询帐号信息
     * @param acctNo
     * @return
     */
    String selectByAcctNo(String acctNo);

    /**
     * 启用停用指定重点账号信息
     * @param tacctInfoDTO
     * @return
     */
    Boolean updateTacctInfo(TacctInfoDTO tacctInfoDTO);
}
