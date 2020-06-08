package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dao.StarTempAdminDao;
import com.bank.manage.dao.StarTempBranchDao;
import com.bank.manage.dos.StarDataAdminDO;
import com.bank.manage.dos.StarDataTempBranchDO;
import com.bank.manage.service.StarTempAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class StarTempAdminServiceImpl implements StarTempAdminService {

    @Autowired
    private StarTempAdminDao starTempAdminDao;

    @Autowired
    private StarTempBranchDao starTempBranchDao;
    @Override
    public void saveStarData(StarDataAdminDO starDataAdminDO) {
        try {
            starTempAdminDao.insert(starDataAdminDO);
        } catch (Exception e) {
            log.error("星级标准化网点数据保存失败！{}",e.getMessage());
            throw new BizException("星级标准化网点数据保存失败！");
        }
    }

    @Override
    public void delStartData(String id, String dataType) {
        try {
            QueryWrapper<StarDataAdminDO> starDataAdminWrapper = new QueryWrapper<>();
            starDataAdminWrapper.eq("EXCEL_ID",id);
            starTempAdminDao.delete(starDataAdminWrapper);

            QueryWrapper<StarDataTempBranchDO> starDataTempBranchWrapper = new QueryWrapper<>();
            starDataTempBranchWrapper.eq("EXCEL_ID",id);
            starTempBranchDao.delete(starDataTempBranchWrapper);
        } catch (Exception e) {
            log.error("行内星级标准化网点统计数据删除失败！{}",e.getMessage());
            throw new BizException("行内星级标准化网点统计数据删除失败！");
        }
    }

    @Override
    public IPage<StarDataAdminDO> queryExampleBranchByAdmin(PageQueryModel pageQueryModel) {
        Page<StarDataAdminDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        //条件查询
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String startYear = (String) queryParam.get("startYear");
        String assessStart = (String) queryParam.get("assessStart");
        String branchOrgId = (String) queryParam.get("branchOrgId");
        String outOrgId = (String) queryParam.get("outOrgId");

        return starTempAdminDao.selectPageByQueryParm(page,startYear,assessStart,branchOrgId,outOrgId);
    }
}
