package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.MsglogDao;
import com.bank.manage.dos.MsglogDO;
import com.bank.manage.service.MsglogService;
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
 * 短信发送历史
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class MsglogServiceImpl extends ServiceImpl<MsglogDao, MsglogDO> implements MsglogService {

    @Autowired(required = false)
    private MsglogDao msglogDao;


    @Override
    public IPage<MsglogDO> queryList(PageQueryModel pageQueryModel, String powerNum) {
        Page<MsglogDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        MsglogDO msglogDO = null;
        if (!MapUtils.isEmpty(queryParam)) {
            msglogDO = BeanUtil.mapToBean(queryParam, MsglogDO.class, false);
        }
        List<MsglogDO> list = msglogDao.queryList(page, msglogDO);
        page.setRecords(list);
        log.info("短信发送历史：{}", list);
        return page;
    }
}
