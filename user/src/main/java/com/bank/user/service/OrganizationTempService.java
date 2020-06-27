package com.bank.user.service;

import com.bank.user.dos.OrganizationTempDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: Andy
 * @Date: 2020/4/8 17:13
 */
public interface OrganizationTempService extends IService<OrganizationTempDO> {
    /**
     * 清空零时表中的数据
     */
    void clearnTempOrgData();
}
