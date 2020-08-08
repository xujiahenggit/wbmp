package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.DcStatuslogDao;
import com.bank.manage.dos.DcStatuslogDO;
import com.bank.manage.service.DcStatuslogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 硬件故障历史
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class DcStatuslogServiceImpl extends ServiceImpl<DcStatuslogDao, DcStatuslogDO> implements DcStatuslogService {

    @Autowired(required = false)
    private DcStatuslogDao dcStatuslogDao;

    @Override
    public IPage<DcStatuslogDO> queryList(PageQueryModel pageQueryModel) {
        Page<DcStatuslogDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        DcStatuslogDO dcStatuslogDO = null;
        if (!MapUtils.isEmpty(queryParam)) {
            dcStatuslogDO = BeanUtil.mapToBean(queryParam, DcStatuslogDO.class, false);
        }

        QueryWrapper<DcStatuslogDO> queryWrapper = new QueryWrapper<>();
        if (dcStatuslogDO.getStrtermnum() != null) {
            queryWrapper.eq("STRTERMNUM", dcStatuslogDO.getStrtermnum());
        }
        if (dcStatuslogDO.getBeginTime() != null && dcStatuslogDO.getEndTime() != null) {
            queryWrapper.between("DTBEGIN", dcStatuslogDO.getBeginTime(), dcStatuslogDO.getEndTime());
        }
        IPage<DcStatuslogDO> list = dcStatuslogDao.selectPage(page, queryWrapper);
        for (DcStatuslogDO statuslogDO : list.getRecords()) {
            statuslogDO.setDttime(statuslogDO.getDttime());
        }
        log.info("硬件故障历史列表查询：{}", list);
        return list;
    }


    @Override
    public Boolean log(DcStatuslogDO dcStatuslogDO) {
        QueryWrapper<DcStatuslogDO> wrapper = new QueryWrapper<DcStatuslogDO>()
                .ge("STRTERMNUM", dcStatuslogDO.getStrtermnum())
                .ge("STRVMNAME", dcStatuslogDO.getStrvmname())
                .ge("STRHDWSTATUS", dcStatuslogDO.getStrhdwstatus())
                .ge("DTBEGIN", dcStatuslogDO.getDtbegin());
        Integer count = dcStatuslogDao.selectCount(wrapper);
        if (count == 0) {
            DcStatuslogDO last = dcStatuslogDao.queryLast(dcStatuslogDO);
            if (last != null) {
                last.setDtend(dcStatuslogDO.getDtbegin());
                dcStatuslogDao.updateById(last);
            }
            dcStatuslogDO.setDtend(LocalDateTime.now());
            dcStatuslogDao.insert(dcStatuslogDO);
        } else {
            DcStatuslogDO dcStatuslogDO1 = dcStatuslogDao.selectOne(wrapper);
            dcStatuslogDO1.setDtend(LocalDateTime.now());
            dcStatuslogDao.insert(dcStatuslogDO1);
        }
        return null;
    }

    @Override
    public boolean save(DcStatuslogDO dcStatuslogDO) {
        dcStatuslogDao.insert(dcStatuslogDO);
        log.info("硬件故障历史插入：{}", dcStatuslogDO);
        return true;
    }
}
