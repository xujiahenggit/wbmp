package com.bank.user.service.impl;

import com.bank.user.dao.OrganizationTempDao;
import com.bank.user.dos.OrganizationTempDO;
import com.bank.user.service.OrganizationTempService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Andy
 * @Date: 2020/4/8 17:14
 */
@Service
public class OrganizationTempServiceImpl extends ServiceImpl<OrganizationTempDao, OrganizationTempDO> implements OrganizationTempService {

    @Autowired
    private OrganizationTempDao organizationTempDao;
    /**
     * 清空零时表中的数据
     */
    @Override
    public void clearnTempOrgData() {
        organizationTempDao.clearnTempOrgData();
    }
}
