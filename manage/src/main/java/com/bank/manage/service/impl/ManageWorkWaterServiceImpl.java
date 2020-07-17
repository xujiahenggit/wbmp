package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.ManageWorkWaterDao;
import com.bank.manage.dos.WorkWaterDO;
import com.bank.manage.service.ManageWorkWaterService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 工单流水表 服务实现类
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
@Service
public class ManageWorkWaterServiceImpl extends ServiceImpl<ManageWorkWaterDao, WorkWaterDO> implements ManageWorkWaterService {

    @Resource
    ManageWorkWaterDao manageworkWaterDao;

    @Override
    public IPage<WorkWaterDO> listPage(PageQueryModel pageQueryModel) {
        Page<WorkWaterDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
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
        WorkWaterDO workWaterDO = null;
        if (!MapUtils.isEmpty(queryParam)) {
            workWaterDO = BeanUtil.mapToBean(queryParam, WorkWaterDO.class, false);
        }
        page.setRecords(manageworkWaterDao.listPage(page, workWaterDO));
        return page;
    }

    @Override
    public List<Map<String, String>> getwater(String orderId) {
        return manageworkWaterDao.getwater(orderId);
    }
}
