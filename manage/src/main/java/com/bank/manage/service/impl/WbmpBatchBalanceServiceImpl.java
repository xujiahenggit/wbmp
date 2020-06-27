package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.WbmpBatchBalanceDao;
import com.bank.manage.dos.WbmpBatchBalanceDO;
import com.bank.manage.service.WbmpBatchBalanceService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * 离线存款统计表 服务实现类
 *
 * @author zhaozhongyuan
 * @since 2020-06-16
 */
@Service
        public class WbmpBatchBalanceServiceImpl extends ServiceImpl<WbmpBatchBalanceDao, WbmpBatchBalanceDO>implements WbmpBatchBalanceService {

        @Resource
         WbmpBatchBalanceDao wbmpBatchBalanceDao;

        @Override
        public IPage<WbmpBatchBalanceDO>listPage(PageQueryModel pageQueryModel){
            Page<WbmpBatchBalanceDO> page = new Page<>(pageQueryModel.getPageIndex(),pageQueryModel.getPageSize());
            String order = pageQueryModel.getOrder();
            String sort = StrUtil.toUnderlineCase(pageQueryModel.getSort());
            if (!StrUtil.isBlankIfStr(order)){
            if (order.equals("asc")) {
                page.setAsc(sort);//3.1.0
            //page.setOrders(OrderItem.ascs(sort)); mybatis3.3.1版本支持
            } else if (order.equals("desc")) {
                page.setDesc(sort);
            //page.setOrders(OrderItem.descs(sort));
            }
            }
            Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
            WbmpBatchBalanceDO wbmpBatchBalanceDO=null;
            if(!MapUtils.isEmpty(queryParam)){
            wbmpBatchBalanceDO=BeanUtil.mapToBean(queryParam, WbmpBatchBalanceDO.class,false);
            }
            page.setRecords(wbmpBatchBalanceDao.listPage(page,wbmpBatchBalanceDO));
            return page;
        }
}
