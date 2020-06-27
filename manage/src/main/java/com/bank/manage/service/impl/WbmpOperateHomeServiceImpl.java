package com.bank.manage.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.bank.core.entity.BizException;
import com.bank.manage.dao.WbmpOperateHomeDao;
import com.bank.manage.service.WbmpOperateHomeService;
import com.bank.user.dao.NfrtOrgDao;
import com.bank.user.dto.OrgNftDto;

/**
 *
 * ClassName: WbmpOperateHomeServiceImpl
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/15 20:32:44
 */
@Service
public class WbmpOperateHomeServiceImpl implements WbmpOperateHomeService {

    @Resource
    private WbmpOperateHomeDao wbmpOperateHomeDao;

    @Resource
    private NfrtOrgDao nfrtOrgDao;

    @Override
    public Map<String, Object> queryBranchDepositBalance(String orgId, String depositTypeCode) {
        Map<String, Object> resultData = new HashMap<>();

        String balType = "";
        switch (depositTypeCode) {
            case "00":
                balType = "private_bal";
                break;
            case "01":
                balType = "generality_bal";
                break;
            case "02":
                balType = "public_bal";
                break;
        }
        Map<String, Object> depositDay = this.wbmpOperateHomeDao.queyDepositDay(orgId);
        BigDecimal deposit = depositDay == null ? new BigDecimal("0.00") : (BigDecimal) depositDay.get(balType);
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        resultData.put("deposit", decimalFormat.format(deposit.divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP)));

        List<BigDecimal> data = new ArrayList<>();
        List<String> xAxis = new ArrayList<>();
        List<Map<String, Object>> depositDay30 = this.wbmpOperateHomeDao.queyDepositDay30(orgId);
        for (int i = 0; i < depositDay30.size(); i++) {
            Map<String, Object> depositData = depositDay30.get(i);
            BigDecimal value = (BigDecimal) depositData.get(balType);
            data.add(value.divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP));
            xAxis.add((String) depositData.get("date_str"));
        }
        resultData.put("data", data);
        resultData.put("xAxis", xAxis);
        return resultData;
    }

    @Override
    public Map<String, Object> queryOrgTierInfo(String orgId) {
        String orgType = "";
        //总行
        if (StringUtils.startsWith(orgId, "100")) {
            orgType = "1";
        }
        //分支行
        else if (StringUtils.startsWith(orgId, "102")) {
            orgType = "2";
        }
        //村镇银行
        else if (StringUtils.startsWith(orgId, "17")) {
            throw new BizException("当前用户对应村镇银行机构号【" + orgId + "】不符合网点视图看需求");
        }
        else {
            throw new BizException("当前用户对应机构号【" + orgId + "】不符合网点视图看需求");
        }
        //获取分支行机构信息
        List<OrgNftDto> orgNftList = this.nfrtOrgDao.getNftOrgList();
        Map<String, Object> result = new HashMap<>();

        if (orgType == "1") {
            //总行用户默认汇丰支行营业部
            result.put("orgType", "1");
            result.put("defaultOrgId", "10260610");
            result.put("defaultOrgName", "汇丰支行营业部");
            result.put("orgList", orgNftList);
        }
        else {
            //分支行Map
            Map<String, OrgNftDto> orgMap = new HashMap<>();
            for (int i = 0; i < orgNftList.size(); i++) {
                orgMap.put(orgNftList.get(i).getOrgId(), orgNftList.get(i));
            }
            result.put("orgType", "2");
            result.put("defaultOrgId", "10260610");
            result.put("defaultOrgName", "汇丰支行营业部");
            List<OrgNftDto> orgList = new ArrayList<>();
            result.put("orgList", orgList);
            if (orgId.length() == 6) {
                if (orgMap.containsKey(orgId)) {
                    //分支行-网点列表
                    List<OrgNftDto> websiteList = this.nfrtOrgDao.getOutletsList(orgId);
                    //默认获取该分支行第一个网点
                    result.put("defaultOrgId", websiteList.get(0).getOrgId());
                    result.put("defaultOrgName", websiteList.get(0).getOrgName());
                    orgList.add(orgMap.get(orgId));
                }
            }
            else if (orgId.length() >= 8) {
                String branchOrg = orgId.substring(0, 6);
                String websiteOrg = "";
                if (orgId.length() >= 10) {
                    websiteOrg = orgId.substring(0, 10);
                }
                else {
                    websiteOrg = orgId.substring(0, 8);
                }
                if (orgMap.containsKey(branchOrg)) {
                    List<OrgNftDto> websiteList = this.nfrtOrgDao.getOutletsList(branchOrg);
                    //分支行-网点Map
                    Map<String, OrgNftDto> websiteMap = new HashMap<>();
                    for (int i = 0; i < websiteList.size(); i++) {
                        websiteMap.put(websiteList.get(i).getOrgId(), websiteList.get(i));
                    }
                    //网点级别(社区支行)
                    if ((websiteOrg.length() == 10 || websiteOrg.length() == 8) && websiteMap.containsKey(websiteOrg)) {
                        OrgNftDto orgData = websiteMap.get(websiteOrg);
                        result.put("orgType", "3");
                        result.put("defaultOrgId", orgData.getOrgId());
                        result.put("defaultOrgName", orgData.getOrgName());
                        orgList.add(orgData);
                    }
                    //网点级别(社区支行)
                    else if (websiteOrg.length() == 10 && websiteMap.containsKey(websiteOrg.substring(0, 8))) {
                        OrgNftDto orgData = websiteMap.get(websiteOrg.substring(0, 8));
                        result.put("orgType", "3");
                        result.put("defaultOrgId", orgData.getOrgId());
                        result.put("defaultOrgName", orgData.getOrgName());
                        orgList.add(orgData);
                    }
                    //分支行级别
                    else {
                        result.put("defaultOrgId", websiteList.get(0).getOrgId());
                        result.put("defaultOrgName", websiteList.get(0).getOrgName());
                        orgList.add(orgMap.get(branchOrg));
                    }
                }
            }
            else {
                orgList.add(orgMap.get("102606"));
            }
        }
        return result;
    }

}
