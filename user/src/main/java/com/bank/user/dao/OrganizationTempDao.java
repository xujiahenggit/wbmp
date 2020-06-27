package com.bank.user.dao;

import com.bank.user.dos.OrganizationTempDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Author: Andy
 * @Date: 2020/4/8 17:13
 */
public interface OrganizationTempDao extends BaseMapper<OrganizationTempDO> {
    /**
     * 清空组织机构零时表
     */
    void clearnTempOrgData();
}
