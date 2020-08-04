package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.TlogTellerDao;
import com.bank.manage.dos.TlogTellerDO;
import com.bank.manage.dto.TlogTellerDTO;
import com.bank.manage.service.TlogTellerService;
import com.bank.manage.vo.TlogTellerVO;
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
 * 柜员操作日志
 *
 * @author
 * @date 2020-7-10
 */
@Service
@Slf4j
public class TlogTellerServiceImpl extends ServiceImpl<TlogTellerDao, TlogTellerDO> implements TlogTellerService {

    @Autowired
    private TlogTellerDao tlogTellerDao;

    @Override
    public IPage<TlogTellerVO> queryList(PageQueryModel pageQueryModel) {
        Page<TlogTellerVO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        TlogTellerDTO tlogTellerDTO=null;
        if(!MapUtils.isEmpty(queryParam)){
            tlogTellerDTO= BeanUtil.mapToBean(queryParam, TlogTellerDTO.class,false);
        }
        List<TlogTellerVO> tlogTellerDOS = tlogTellerDao.selectPageList(page, tlogTellerDTO);
        page.setRecords(tlogTellerDOS);
        log.info("柜员操作日志信息查询：{}", tlogTellerDOS);
        return page;
    }
}
