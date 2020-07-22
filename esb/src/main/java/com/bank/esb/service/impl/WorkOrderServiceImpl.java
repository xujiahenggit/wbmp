package com.bank.esb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.esb.dao.WorkOrderDao;
import com.bank.esb.service.WorkOrderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 工单表 服务实现类
 *
 * @author 代码自动生成
 * @since 2020-07-15
 */
@Service
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderDao, WorkOrderDO> implements WorkOrderService {

    @Resource
    WorkOrderDao workOrderDao;

    @Override
    public IPage<WorkOrderDO> listPage(PageQueryModel pageQueryModel) {
        Page<WorkOrderDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
        String order = pageQueryModel.getOrder();
        String sort = StrUtil.toUnderlineCase(pageQueryModel.getSort());
        if (!StrUtil.isBlankIfStr(order)) {
            if (order.equals("asc")) {
                page.setAsc(sort);//3.1.0
                //page.setOrders(OrderItem.ascs(sort)); mybatis3.3.1版本支持
            } else if (order.equals("desc")) {
                page.setDesc(sort);
                //page.setOrders(OrderItem.descs(sort));
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        WorkOrderDO workOrderDO = null;
        if (!MapUtils.isEmpty(queryParam)) {
            workOrderDO = BeanUtil.mapToBean(queryParam, WorkOrderDO.class, false);
        }
        page.setRecords(workOrderDao.listPage(page, workOrderDO));
        return page;
    }
}
