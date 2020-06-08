package com.bank.user.service;

import com.bank.core.entity.TokenUserInfo;
import com.bank.user.dos.OrganizationDO;
import com.bank.user.dto.OrgDemandDto;
import com.bank.user.dto.OrgListDto;
import com.bank.user.vo.OrgQueryVo;
import com.bank.user.vo.OrgVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/1 16:33
 */
public interface OrganizationService extends IService<OrganizationDO> {
    /**
     * 清空机构表 中的数据
     */
    void clearnOrgData();

    /**
     * 复制零时表中的数据到 机构表中
     */
    void copyData();

    /**
     * 添加机构
     * @param orgVo 机构信息
     * @param tokenUserInfo
     * @return
     */
    Boolean addOrg(OrgVo orgVo, TokenUserInfo tokenUserInfo);

    /**
     * 更新机构
     * @param orgVo 机构信息
     * @param tokenUserInfo
     * @return
     */
    Boolean updateOrg(OrgVo orgVo, TokenUserInfo tokenUserInfo);

    /**
     * 删除 组织机构
     * @param orgId 组织机构ID
     * @return
     */
    Boolean deleteOrg(String orgId);

    /**
     * 获取机构列表
     * @param orgQueryVo 查询参数
     * @param currentOrgId 当前用户的机构编号
     * @return
     */
    IPage<OrgListDto> selectOrgPage(OrgQueryVo orgQueryVo, String currentOrgId);

    /**
     * 获取机构详细 信息
     * @param orgId 机构编号
     * @return
     */
    OrgListDto getOrgDetailById(String orgId);

    /**
     * 获取 对外开放的机构
     * @return
     */
    List<OrganizationDO> getOpenOrgList();

    /**
     * 查询是不是总行
     * @param orgId
     * @return
     */
    boolean isHeadOffice(String orgId);

    /**
     * 查找父机
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    OrgDemandDto getParentOrg(TokenUserInfo tokenUserInfo);

    /**
     * 查找子机构
     * @param orgId 父机构ID
     * @return
     */
    List<OrgDemandDto> getChild(String orgId);
}
