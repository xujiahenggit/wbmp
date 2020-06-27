package com.bank.manage.service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 经营视图Service
 *
 * @Author: Zhangfuqiang
 * @Date: 2020/6/16 10:54
 */
public interface OperateCurveService {
    //计算经营的分数
    float calcOrgMonthScore(String orgId, String date);
}
