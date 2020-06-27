package com.bank.user.dao;

import com.bank.user.dos.OrgDetailinfoDO;
import com.bank.user.dto.OrgDetailDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Andy
 * @Date: 2020/5/7 16:03
 */
public interface OrgDetailinfoDao extends BaseMapper<OrgDetailinfoDO> {
    /**
     * 获取机构详细信息
     * @param orgId 机构编号
     * @return
     */
    OrgDetailDto getOrgInfoByOrgId(@Param(value = "orgId") String orgId);
}
