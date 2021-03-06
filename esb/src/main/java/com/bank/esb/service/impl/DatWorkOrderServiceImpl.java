package com.bank.esb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.esb.dao.DatWorkOrderDao;
import com.bank.esb.dos.DatWorkOrderDO;
import com.bank.esb.service.DatWorkOrderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 故障工单表 服务实现类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
@Service
public class DatWorkOrderServiceImpl extends ServiceImpl<DatWorkOrderDao, DatWorkOrderDO> implements DatWorkOrderService {

    @Resource
    DatWorkOrderDao datWorkOrderDao;

    @Override
    public IPage<DatWorkOrderDO> listPage(PageQueryModel pageQueryModel) {
        Page<DatWorkOrderDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
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
        DatWorkOrderDO datWorkOrderDO = null;
        if (!MapUtils.isEmpty(queryParam)) {
            datWorkOrderDO = BeanUtil.mapToBean(queryParam, DatWorkOrderDO.class, false);
        }
        page.setRecords(datWorkOrderDao.listPage(page, datWorkOrderDO));
        return page;
    }
}
