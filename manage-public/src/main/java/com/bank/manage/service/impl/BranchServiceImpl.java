package com.bank.manage.service.impl;

import com.bank.core.config.DataSource;
import com.bank.core.config.DynamicDataSourceSwitcher;
import com.bank.manage.dao.BranchDao;
import com.bank.manage.dao.SubBranchDao;
import com.bank.manage.dos.BranchDO;
import com.bank.manage.dos.SubBranchDO;
import com.bank.manage.service.BranchService;
import com.bank.user.dao.OrganizationDao;
import com.bank.user.dos.OrganizationDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 原监控平台机构
 *
 * @author
 * @date 2020-8-4
 */
@Service
@Slf4j
public class BranchServiceImpl extends ServiceImpl<BranchDao, BranchDO> implements BranchService {

    @Autowired
    private BranchDao branchDao;

    @Autowired
    private SubBranchDao subBranchDao;

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public String selectByOrgcode(String orgcode) {
        //根据orgcode查询原监控的分支行编号
        QueryWrapper<BranchDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(orgcode),"ORG_CODE",orgcode);
        BranchDO branchDO = branchDao.selectOne(queryWrapper);
        if(branchDO != null && StringUtils.isNotBlank(branchDO.getStrbranchnum())){
            return branchDO.getStrbranchnum();
        }
        QueryWrapper<SubBranchDO> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq(StringUtils.isNotBlank(orgcode),"ORG_CODE",orgcode);
        SubBranchDO subBranchDO = subBranchDao.selectOne(queryWrapper2);
        if(subBranchDO != null && StringUtils.isNotBlank(subBranchDO.getStrbranchnum())){
            return subBranchDO.getStrsubbranchnum();
        }
        return null;
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public String selectBranchByOrgcode(String orgcode) {
        //根据orgcode查询原监控的分行编号
        QueryWrapper<BranchDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(orgcode),"ORG_CODE",orgcode);
        BranchDO branchDO = branchDao.selectOne(queryWrapper);
        if(branchDO != null && StringUtils.isNotBlank(branchDO.getStrbranchnum())){
            return branchDO.getStrbranchnum();
        }
        QueryWrapper<SubBranchDO> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq(StringUtils.isNotBlank(orgcode),"ORG_CODE",orgcode);
        SubBranchDO subBranchDO = subBranchDao.selectOne(queryWrapper2);
        //登录人的机构是支行时返回所属分行的机构号
        if(subBranchDO != null && StringUtils.isNotBlank(subBranchDO.getStrbranchnum())){
            return subBranchDO.getStrbranchnum();
        }
        return null;
    }
}
