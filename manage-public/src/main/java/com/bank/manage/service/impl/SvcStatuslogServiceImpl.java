package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.SvcStatuslogDao;
import com.bank.manage.dos.DcStatuslogDO;
import com.bank.manage.dos.SvcStatuslogDO;
import com.bank.manage.service.SvcStatuslogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 服务故障历史
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class SvcStatuslogServiceImpl extends ServiceImpl<SvcStatuslogDao, SvcStatuslogDO> implements SvcStatuslogService {

    @Autowired
    private SvcStatuslogDao svcStatuslogDao;


    @Override
    public IPage<SvcStatuslogDO> queryList(PageQueryModel pageQueryModel) {
        Page<SvcStatuslogDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        SvcStatuslogDO svcStatuslogDO = null;
        if (!MapUtils.isEmpty(queryParam)) {
            svcStatuslogDO = BeanUtil.mapToBean(queryParam, SvcStatuslogDO.class, false);
        }
        QueryWrapper<SvcStatuslogDO> queryWrapper = new QueryWrapper<>();
        if (svcStatuslogDO.getStrtermnum() != null) {
            queryWrapper.eq("STRTERMNUM", svcStatuslogDO.getStrtermnum());
        }
        if (svcStatuslogDO.getBeginTime() != null && svcStatuslogDO.getEndTime() != null) {
            queryWrapper.between("DTBEGIN", svcStatuslogDO.getBeginTime(), svcStatuslogDO.getEndTime());
        }
        IPage<SvcStatuslogDO> list = svcStatuslogDao.selectPage(page, queryWrapper);
        for (SvcStatuslogDO statuslogDO : list.getRecords()) {
            statuslogDO.setDttime(statuslogDO.getDtTime());
        }
        log.info("服务故障历史查询：{}", list);
        return list;
    }

    @Override
    public boolean save(SvcStatuslogDO svcStatuslogDO) {
        svcStatuslogDao.insert(svcStatuslogDO);
        log.info("服务故障历史插入：{}", svcStatuslogDO);
        return true;
    }

    @Override
    public void setSvc(SvcStatuslogDO statuslogDO) {
        QueryWrapper<SvcStatuslogDO> queryWrapper1 = new QueryWrapper<SvcStatuslogDO>()
                .eq("STRTERMNUM", statuslogDO.getStrtermnum());
        Integer count1 = svcStatuslogDao.selectCount(queryWrapper1);
        QueryWrapper<SvcStatuslogDO> queryWrapper = new QueryWrapper<SvcStatuslogDO>()
                .eq("STRTERMNUM", statuslogDO.getStrtermnum())
                .eq("SVCSTATUS", statuslogDO.getSvcstatus())
                .eq("DTBEGIN", statuslogDO.getDtbegin());
        Integer count = svcStatuslogDao.selectCount(queryWrapper);
        SvcStatuslogDO last = svcStatuslogDao.queryLast(statuslogDO.getStrtermnum());

        if (count1 == 0) {
            statuslogDO.setDtend(statuslogDO.getDtbegin());
            svcStatuslogDao.insert(statuslogDO);
        } else if (count == 0) {
            last.setDtend(statuslogDO.getDtbegin());
            svcStatuslogDao.updateById(last);
            statuslogDO.setDtend(statuslogDO.getDtbegin());
            svcStatuslogDao.insert(statuslogDO);
        } else {
            last.setDtend(LocalDateTime.now());
            svcStatuslogDao.updateById(last);
        }
    }
}
