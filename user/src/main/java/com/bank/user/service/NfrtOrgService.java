package com.bank.user.service;

import com.bank.user.dos.NfrtOrgDO;
import com.bank.user.dto.OrgNftDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/3 15:12
 */
public interface NfrtOrgService extends IService<NfrtOrgDO> {
    /**
     * 清空核心机构表
     */
    void clearnNfrt();

    /**
     * 复制中间表的数据到 核心机构表中
     */
    void copyTempData();

    /**
     * 获取分支行列表
     *
     * @return
     */
    List<OrgNftDto> getNftOrgList();

    /**
     * 获取网点类别
     *
     * @param orgId 分支行核心机构号
     * @param type  类型
     * @return
     */
    List<OrgNftDto> getOutletsList(String orgId, String type);

    /**
     * 获取所有的网点列表
     * @return
     */
    List<OrgNftDto> getAllOutletsList();
}
