package com.bank.user.service;

import com.bank.core.entity.TokenUserInfo;
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

    /**
     * 获取分行下面的所有 网点机构号
     * @param branchNo 分行编号
     * @return
     */
    List<String> getSubOutSiteList(String branchNo);

    /**
     * 查询用户所在的机构号，如果是总行 则显示所有
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    List<OrgNftDto> getOrgListByUser(TokenUserInfo tokenUserInfo);

    /**
     * 查询用户所在的机构号
     * @param orgId 分行编号
     * @param type 类型
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    List<OrgNftDto> getOutlegetOutSitListByUsertsList(String orgId, String type, TokenUserInfo tokenUserInfo);
}
