package com.bank.user.dao;

import com.bank.user.dos.OrganizationDO;
import com.bank.user.dto.OrgDemandDto;
import com.bank.user.dto.OrgListDto;
import com.bank.user.vo.OrgQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/1 16:32
 */
public interface OrganizationDao extends BaseMapper<OrganizationDO> {
    /**
     * 清空机构信息
     */
    void clearnOrgData();

    /**
     * 复制零时表中的数据到 机构表中
     */
    void copyData();

    /**
     * 获取 机构的父机构ID
     * @param orgId 机构ID
     * @return
     */
    String getParentName(@Param(value = "orgId") String orgId);

    /**
     * 机构列表 分页查询
     * @param page 分页对象
     * @param listOrgIds
     * @param orgQueryVo 查询条件
     * @return
     */
    IPage<OrgListDto> selectPageByCondition(Page<OrgListDto> page, @Param(value = "listOrgIds") List<String> listOrgIds, @Param(value = "orgQueryVo") OrgQueryVo orgQueryVo);

    /**
     * 获取机构详细信息
     * @param orgId  机构编号
     * @return
     */
    OrgListDto getOrgDetailById(@Param(value = "orgId") String orgId);

    /**
     * 查找父机机构
     * @param tempOrgId 机构编号
     * @return
     */
    OrgDemandDto getParentOrg(@Param(value = "tempOrgId") String tempOrgId);

    /**
     * 查找子机构
     * @param orgId 机构编号
     * @return
     */
    List<OrgDemandDto> getChild(@Param(value = "orgId") String orgId);
}
