package com.bank.manage.service;

import java.util.Map;

/**
 *
 * ClassName: WbmpOperateHomeService
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/15 20:27:14
 */
public interface WbmpOperateHomeService {

    /**
     * 查询网点存款时点余额数据
     * @param orgId 机构号
     * @param depositTypeCode 存款类型
     * @return mapKey deposit data xAxis
     */
    Map<String, Object> queryBranchDepositBalance(String orgId, String depositTypeCode);

    /**
     * 查询机构层级结构信息（总行，分支行，网点）
     * @param orgId 用户所在机构号
     * @return
     */
    Map<String, Object> queryOrgTierInfo(String orgId);
}
