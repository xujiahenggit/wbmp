package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.WbmpOperateIndexAumDao;
import com.bank.manage.dos.WbmpOperateIndexAumDO;
import com.bank.manage.service.WbmpOperateIndexAumService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * 网点AUM表 服务实现类
 *
 * @author zhaozhongyuan
 * @since 2020-06-18
 */
@Service
        public class WbmpOperateIndexAumServiceImpl extends ServiceImpl<WbmpOperateIndexAumDao, WbmpOperateIndexAumDO>implements WbmpOperateIndexAumService {

        @Resource
         WbmpOperateIndexAumDao wbmpOperateIndexAumDao;

        @Override
        public IPage<WbmpOperateIndexAumDO>listPage(PageQueryModel pageQueryModel){
            Page<WbmpOperateIndexAumDO> page = new Page<>(pageQueryModel.getPageIndex(),pageQueryModel.getPageSize());
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
            WbmpOperateIndexAumDO wbmpOperateIndexAumDO=null;
            if(!MapUtils.isEmpty(queryParam)){
            wbmpOperateIndexAumDO=BeanUtil.mapToBean(queryParam, WbmpOperateIndexAumDO.class,false);
            }
            page.setRecords(wbmpOperateIndexAumDao.listPage(page,wbmpOperateIndexAumDO));
            return page;
        }
}
