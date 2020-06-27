package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.utils.MapUtils;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.util.Map;
import com.bank.manage.dos.WbmpCommonCalcMethodDO;
import com.bank.manage.vo.WbmpCommonCalcMethodVO;
import com.bank.manage.dao.WbmpCommonCalcMethodDao;
import com.bank.manage.service.WbmpCommonCalcMethodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * 网点视图计算公式参数表 服务实现类
 *
 * @author zhaozhongyuan
 * @since 2020-06-18
 */
@Service
        public class WbmpCommonCalcMethodServiceImpl extends ServiceImpl<WbmpCommonCalcMethodDao, WbmpCommonCalcMethodDO>implements WbmpCommonCalcMethodService {

        @Resource
         WbmpCommonCalcMethodDao wbmpCommonCalcMethodDao;

        @Override
        public IPage<WbmpCommonCalcMethodDO>listPage(PageQueryModel pageQueryModel){
            Page<WbmpCommonCalcMethodDO> page = new Page<>(pageQueryModel.getPageIndex(),pageQueryModel.getPageSize());
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
            WbmpCommonCalcMethodDO wbmpCommonCalcMethodDO=null;
            if(!MapUtils.isEmpty(queryParam)){
            wbmpCommonCalcMethodDO=BeanUtil.mapToBean(queryParam, WbmpCommonCalcMethodDO.class,false);
            }
            page.setRecords(wbmpCommonCalcMethodDao.listPage(page,wbmpCommonCalcMethodDO));
            return page;
        }
}
