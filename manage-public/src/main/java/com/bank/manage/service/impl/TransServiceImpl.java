package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.TransDao;
import com.bank.manage.dos.TransDO;
import com.bank.manage.dto.TransInfoDTO;
import com.bank.manage.service.TransService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易历史表
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class TransServiceImpl extends ServiceImpl<TransDao, TransDO> implements TransService {

    @Autowired(required = false)
    private TransDao transDao;


    @Override
    public IPage<TransDO> queryList(PageQueryModel pageQueryModel) {
        Page<TransDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        TransDO transDO = null;
        if (!MapUtils.isEmpty(queryParam)) {
            transDO = BeanUtil.mapToBean(queryParam, TransDO.class, false);
        }
        List<TransDO> list = transDao.queryList(page, transDO);
        page.setRecords(list);
        log.info("交易历史查询：{}", list);
        return page;
    }

    @Override
    public void export(PageQueryModel pageQueryModel, HttpServletResponse response) {

    }

    @Override
    public Map<String, Object> queryCount(String termNum) {
        Map<String, Object> map = new HashMap<>();
        List<TransInfoDTO> list = transDao.transCount(termNum);
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            count += list.get(i).getTransHnum();
        }
        map.put("totalCount", count);
        map.put("transList: ", list);
        log.info("交易时间统计：{}", map);
        return map;
    }

    @Override
    public boolean save(TransDO entity) {
        return transDao.insert(entity) > 0;
    }
}
