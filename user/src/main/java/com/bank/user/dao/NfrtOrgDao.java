package com.bank.user.dao;

import com.bank.user.dos.NfrtOrgDO;
import com.bank.user.dto.OrgNftDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/3 15:11
 */
public interface NfrtOrgDao extends BaseMapper<NfrtOrgDO> {
    /**
     * 清空核心机构表
     */
    void clearnNfrt();

    /**
     * 复制中间表数据 到核心机构表中
     */
    void copyTempData();

    /**
     * 获取分支行 列表
     * @return
     */
    List<OrgNftDto> getNftOrgList();

    /**
     * 获取网点列表
     * @param orgId 分支行核心机构号
     * @return
     */
    List<OrgNftDto> getOutletsList(@Param(value = "orgId") String orgId);

    /**
     * 获取村镇支行
     * @return
     */
    List<OrgNftDto> getContryOrgList();

    /**
     * 获取村镇 支行的网点列表
     * @param orgId 机构号
     * @return
     */
    List<OrgNftDto> getContryOutsitList(@Param(value = "orgId") String orgId);

    /**
     * 获取所有的网点列表
     * @return
     */
    List<OrgNftDto> getAllOutletList();
}
