package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.VersionsDao;
import com.bank.manage.dos.VersionsDO;
import com.bank.manage.service.VersionsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 应用版本维护 服务实现类
 *
 * @author zhaozhongyuan
 * @since 2020-06-01
 */
@Service
public class VersionsServiceImpl extends ServiceImpl<VersionsDao, VersionsDO> implements VersionsService {

    @Resource
    VersionsDao versionsDao;

    @Override
    public IPage<VersionsDO> listPage(PageQueryModel pageQueryModel) {
        Page<VersionsDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
        String order = pageQueryModel.getOrder();
        String sort = StrUtil.toUnderlineCase(pageQueryModel.getSort());
        if (!StrUtil.isBlankIfStr(order)) {
            if (order.equals("asc")) {
                page.setAsc(sort);
//                page.setOrders(OrderItem.ascs(sort));
            } else if (order.equals("desc")) {
                page.setDesc(sort);
//                page.setOrders(OrderItem.descs(sort));
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        VersionsDO versionsDO = null;
        if (!MapUtils.isEmpty(queryParam)) {
            versionsDO = BeanUtil.mapToBean(queryParam, VersionsDO.class, false);
        }
        page.setRecords(versionsDao.listPage(page, versionsDO));
        return page;
    }
}
