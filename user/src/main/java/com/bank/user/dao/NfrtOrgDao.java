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

    /**
     * 获取分行下面的所有的机构列表
     * @param branchNo 分行编号
     * @return
     */
    List<String> getSubOutSiteList(@Param(value = "branchNo") String branchNo);

    /**
     * 根绝  用户所在机构号 查询分行列表
     * @param orgDepartId 用户所在的分行号
     * @return
     */
    List<OrgNftDto> getOrgListByUser(@Param(value = "orgDepartId") String orgDepartId);

    /**
     * 根据 用户所在的机构号 查询 网点列表
     * @param departId 分行机构号
     * @param orgId 用户所在的机构号
     * @return
     */
    List<OrgNftDto> getOutlegetOutSitListByUsertsList(@Param(value = "departId") String departId, @Param(value = "orgId") String orgId);

    /**
     * 获取所有的 核心机构号列表 计算综合分数用
     * @return
     */
    List<OrgNftDto> getAllOrgUseOperate();
}
