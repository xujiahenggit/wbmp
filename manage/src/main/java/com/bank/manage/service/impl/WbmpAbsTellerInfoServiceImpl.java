package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.utils.MapUtils;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.util.Map;
import com.bank.manage.dos.WbmpAbsTellerInfoDO;
import com.bank.manage.vo.WbmpAbsTellerInfoVO;
import com.bank.manage.dao.WbmpAbsTellerInfoDao;
import com.bank.manage.service.WbmpAbsTellerInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * 柜员信息表 服务实现类
 *
 * @author 代码自动生成
 * @since 2020-07-03
 */
@Service
        public class WbmpAbsTellerInfoServiceImpl extends ServiceImpl<WbmpAbsTellerInfoDao, WbmpAbsTellerInfoDO>implements WbmpAbsTellerInfoService {

        @Resource
         WbmpAbsTellerInfoDao wbmpAbsTellerInfoDao;

        @Override
        public IPage<WbmpAbsTellerInfoDO>listPage(PageQueryModel pageQueryModel){
            Page<WbmpAbsTellerInfoDO> page = new Page<>(pageQueryModel.getPageIndex(),pageQueryModel.getPageSize());
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
            WbmpAbsTellerInfoDO wbmpAbsTellerInfoDO=null;
            if(!MapUtils.isEmpty(queryParam)){
            wbmpAbsTellerInfoDO=BeanUtil.mapToBean(queryParam, WbmpAbsTellerInfoDO.class,false);
            }
            page.setRecords(wbmpAbsTellerInfoDao.listPage(page,wbmpAbsTellerInfoDO));
            return page;
        }
}
