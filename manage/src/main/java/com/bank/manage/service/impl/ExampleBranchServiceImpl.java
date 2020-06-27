package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.PropertyUtil;
import com.bank.manage.dao.ExampleBranchAdminDao;
import com.bank.manage.dao.ExampleBranchDao;
import com.bank.manage.dos.ExampleAdminDO;
import com.bank.manage.dos.ExampleBranchDO;
import com.bank.manage.service.ExampleBranchService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ExampleBranchServiceImpl implements ExampleBranchService {

    @Autowired
    private ExampleBranchDao exampleBranchDao;

    @Autowired
    private ExampleBranchAdminDao exampleBranchAdminDao;

    @Override
    public void saveExampleBranch(ExampleAdminDO exampleAdminDO, ExampleBranchDO exampleBranchDO) {
        try {
            exampleBranchDao.insert(exampleBranchDO);
            exampleBranchAdminDao.insert(exampleAdminDO);
        } catch (Exception e) {
            log.error("全国标杆网点统计数据！{}",e.getMessage());
            throw new BizException("全国标杆网点统计数据保存失败！");
        }
    }

    @Override
    public IPage<ExampleAdminDO> queryExampleBranch(PageQueryModel pageQueryModel) {
        Page<ExampleBranchDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        QueryWrapper<ExampleBranchDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("START_YEAR", LocalDateTime.now().getYear());
        IPage<ExampleBranchDO> exampleBranchDOIPage = exampleBranchDao.selectPage(page, queryWrapper);
        IPage<ExampleAdminDO> page1 = new Page<>();

        PropertyUtil.copyProperties(exampleBranchDOIPage,page1);
        return page1;
    }
}
