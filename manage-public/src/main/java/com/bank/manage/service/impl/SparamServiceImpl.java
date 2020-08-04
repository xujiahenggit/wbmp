package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.SparamDao;
import com.bank.manage.dos.SparamDO;
import com.bank.manage.dto.SparamDTO;
import com.bank.manage.service.SparamService;
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
 * 对账指令维护
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class SparamServiceImpl extends ServiceImpl<SparamDao, SparamDO> implements SparamService {

    @Autowired(required = false)
    private SparamDao sparamDao;

    @Override
    public Boolean save(SparamDTO sparamDTO, TokenUserInfo tokenUserInfo) {

        try {
            sparamDao.save(sparamDTO);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public IPage<SparamDTO> queryList(PageQueryModel pageQueryModel) {
        Page<SparamDTO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        SparamDTO sparamDTO = null;
        if (!MapUtils.isEmpty(queryParam)) {
            sparamDTO = BeanUtil.mapToBean(queryParam, SparamDTO.class, false);
        }
        List<SparamDTO> sparamDTOS = sparamDao.selectPageList(page, sparamDTO);
        page.setRecords(sparamDTOS);
        log.info("回单参数管理查询：{}", sparamDTOS);
        return page;
    }

    @Override
    public Boolean update(SparamDTO sparamDTO, TokenUserInfo tokenUserInfo) {
        try {
            sparamDao.update(sparamDTO);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateList(List<Integer> list, String value) {
        try {
            sparamDao.updateList(list, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
