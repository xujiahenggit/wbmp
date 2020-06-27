package com.bank.manage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 *
 * ClassName: WbmpOperateHomeDao
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/15 20:36:20
 */
public interface WbmpOperateHomeDao {

    /**
     * 查询当前日存款数
     * @param orgId
     * @return
     */
    Map<String, Object> queyDepositDay(@Param("orgId") String orgId);

    /**
     * 查询当前30日存款数
     * @param orgId
     * @return
     */
    List<Map<String, Object>> queyDepositDay30(@Param("orgId") String orgId);
}
