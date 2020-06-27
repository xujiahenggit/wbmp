package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.utils.MapUtils;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.util.Map;
import com.bank.manage.dos.WbmpOrgBalanceDO;
import com.bank.manage.vo.WbmpOrgBalanceVO;
import com.bank.manage.dao.WbmpOrgBalanceDao;
import com.bank.manage.service.WbmpOrgBalanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * 网点机构存款表 服务实现类
 *
 * @author zhaozhongyuan
 * @since 2020-06-22
 */
@Service
        public class WbmpOrgBalanceServiceImpl extends ServiceImpl<WbmpOrgBalanceDao, WbmpOrgBalanceDO>implements WbmpOrgBalanceService {

        @Resource
         WbmpOrgBalanceDao wbmpOrgBalanceDao;

        @Override
        public IPage<WbmpOrgBalanceDO>listPage(PageQueryModel pageQueryModel){
            Page<WbmpOrgBalanceDO> page = new Page<>(pageQueryModel.getPageIndex(),pageQueryModel.getPageSize());
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
            WbmpOrgBalanceDO wbmpOrgBalanceDO=null;
            if(!MapUtils.isEmpty(queryParam)){
            wbmpOrgBalanceDO=BeanUtil.mapToBean(queryParam, WbmpOrgBalanceDO.class,false);
            }
            page.setRecords(wbmpOrgBalanceDao.listPage(page,wbmpOrgBalanceDO));
            return page;
        }
}
