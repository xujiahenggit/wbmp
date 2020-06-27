package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dao.ExampleBranchAdminDao;
import com.bank.manage.dao.ExampleBranchDao;
import com.bank.manage.dos.ExampleAdminDO;
import com.bank.manage.dos.ExampleBranchDO;
import com.bank.manage.service.ExampleBranchAdminService;
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
public class ExampleBranchAdminServiceImpl implements ExampleBranchAdminService {

    @Autowired
    private ExampleBranchAdminDao exampleBranchAdminDao;

    @Autowired
    private ExampleBranchDao exampleBranchDao;

    @Override
    public void saveExampleAdmin(ExampleAdminDO exampleAdminDO) {
        exampleBranchAdminDao.insert(exampleAdminDO);
    }

    @Override
    public void delExampleData(String id, String dataType) {
        try {
            QueryWrapper<ExampleAdminDO> exampleAdminWrapper = new QueryWrapper<>();
            exampleAdminWrapper.eq("EXCEL_ID",id);
            exampleBranchAdminDao.delete(exampleAdminWrapper);

            QueryWrapper<ExampleBranchDO> exampleBranchWrapper = new QueryWrapper<>();
            exampleAdminWrapper.eq("EXCEL_ID",id);
            exampleBranchDao.delete(exampleBranchWrapper);
        } catch (Exception e) {
            log.error("全国标杆网点统计数据删除失败！{}",e.getMessage());
            throw new BizException("全国标杆网点统计数据删除失败！");
        }
    }

    @Override
    public IPage<ExampleAdminDO> queryExampleBranchByAdmin(PageQueryModel pageQueryModel) {

        Page<ExampleAdminDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        QueryWrapper<ExampleAdminDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("START_YEAR", LocalDateTime.now().getYear());

        return exampleBranchAdminDao.selectPage(page,queryWrapper);
    }
}
