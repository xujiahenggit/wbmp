package com.bank.esb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.utils.MapUtils;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import java.util.Map;
import com.bank.esb.dos.DatSubbranchDO;
import com.bank.esb.vo.DatSubbranchVO;
import com.bank.esb.dao.DatSubbranchDao;
import com.bank.esb.service.DatSubbranchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * 支行信息表 服务实现类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
@Service
        public class DatSubbranchServiceImpl extends ServiceImpl<DatSubbranchDao, DatSubbranchDO>implements DatSubbranchService {

        @Resource
         DatSubbranchDao datSubbranchDao;

        @Override
        public IPage<DatSubbranchDO>listPage(PageQueryModel pageQueryModel){
            Page<DatSubbranchDO> page = new Page<>(pageQueryModel.getPageIndex(),pageQueryModel.getPageSize());
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
            DatSubbranchDO datSubbranchDO=null;
            if(!MapUtils.isEmpty(queryParam)){
            datSubbranchDO=BeanUtil.mapToBean(queryParam, DatSubbranchDO.class,false);
            }
            page.setRecords(datSubbranchDao.listPage(page,datSubbranchDO));
            return page;
        }
}
