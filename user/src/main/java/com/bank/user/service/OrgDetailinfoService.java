package com.bank.user.service;

import com.bank.user.dos.OrgDetailinfoDO;
import com.bank.user.dto.OrgDetailDto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: Andy
 * @Date: 2020/5/7 16:04
 */
public interface OrgDetailinfoService extends IService<OrgDetailinfoDO> {
    /**
     * 更新机构信息
     * @param orgDetailinfoDO 更新内容
     * @return
     */
    boolean updateOrgDetail(OrgDetailinfoDO orgDetailinfoDO);

    /**
     * 获取机构详细信息
     * @param orgId 机构编号
     * @return
     */
    OrgDetailDto getInfoByOrgId(String orgId);
}
