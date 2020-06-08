package com.bank.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bank.manage.dos.UsherPopulationDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *
 * ClassName: UsherPopulationDao
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/22 15:35:59
 */
public interface UsherPopulationDao extends BaseMapper<UsherPopulationDO> {

    Integer insertBatch(List<UsherPopulationDO> list);

    /**
     * 查询机构人数控制
     */
    List<UsherPopulationDO> selectOrgUsherPopulation(@Param("orgName") String orgName);

    /**
     * 检查机构是否存在
     * @param orgId
     * @return
     */
    Integer checkOrgExist(@Param("orgId") String orgId);
}
