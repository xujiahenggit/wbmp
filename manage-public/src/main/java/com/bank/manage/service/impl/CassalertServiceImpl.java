package com.bank.manage.service.impl;

import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.CassalertDao;
import com.bank.manage.dos.CassalertDO;
import com.bank.manage.service.CassalertService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 预警信息表
 *
 * @author
 * @date 2020-7-7
 */

@Service
@Slf4j
public class CassalertServiceImpl extends ServiceImpl<CassalertDao, CassalertDO> implements CassalertService {
    @Autowired(required = false)
    private CassalertDao cassalertDao;

    @Override
    public IPage<CassalertDO> queryList(PageQueryModel pageQueryModel) {
        Page<CassalertDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        String subBranchNum = "";
        if (!MapUtils.isEmpty(queryParam)) {
            subBranchNum = (String) queryParam.get("strSubBranchNum");
        }
        List<CassalertDO> list = cassalertDao.queryList(page, subBranchNum);
        page.setRecords(list);
        log.info("预警值分页查询：{}", list);
        return page;
    }

    @Override
    public CassalertDO queryBank(String strBankNum) {
        CassalertDO cassalertDO = cassalertDao.selectBank(strBankNum);
        log.info("银行预警值查询：{}", cassalertDO);
        return cassalertDO;
    }

    @Override
    public CassalertDO queryBranch(String strBranchNum) {
        CassalertDO cassalertDO = cassalertDao.selectBranch(strBranchNum);
        log.info("分行预警值查询：{}", cassalertDO);
        return cassalertDO;
    }

    @Override
    public boolean save(CassalertDO cassalertDO) {
        try {
            if (cassalertDO.getId() != null) {
                cassalertDao.updateById(cassalertDO);
                log.info("预警值修改：{}", cassalertDO);
            } else {
                cassalertDao.insert(cassalertDO);
                log.info("预警值新增：{}", cassalertDO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        log.info("银行预警值新增：{}", cassalertDO);
        return true;
    }

    @Override
    public boolean saveBank(CassalertDO cassalertDO) {
        try {
            if (cassalertDO.getId() != null) {
                cassalertDao.updateBank(cassalertDO);
                log.info("银行预警值修改：{}", cassalertDO);
            } else {
                cassalertDao.insertBank(cassalertDO);
                log.info("银行预警值新增：{}", cassalertDO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean saveBranch(CassalertDO cassalertDO) {
        try {
            if (cassalertDO.getId() != null) {
                cassalertDao.updateBranch(cassalertDO);
                log.info("分行预警值修改：{}", cassalertDO);
            } else {
                cassalertDao.insertBranch(cassalertDO);
                log.info("分行预警值新增：{}", cassalertDO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
