package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.CommandlogDao;
import com.bank.manage.dos.CommandlogDO;
import com.bank.manage.service.CommandlogService;
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
 * 命令日志
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class CommandlogServiceImpl extends ServiceImpl<CommandlogDao, CommandlogDO> implements CommandlogService {

    @Autowired(required = false)
    private CommandlogDao commandlogDao;


    @Override
    public IPage<CommandlogDO> queryList(PageQueryModel pageQueryModel) {
        Page<CommandlogDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        CommandlogDO commandlogDO = null;
        if (!MapUtils.isEmpty(queryParam)) {
            commandlogDO = BeanUtil.mapToBean(queryParam, CommandlogDO.class, false);
        }
        List<CommandlogDO> list = commandlogDao.queryList(page, commandlogDO);
        page.setRecords(list);
        log.info("命令日志：{}", list);
        return page;
    }
}
