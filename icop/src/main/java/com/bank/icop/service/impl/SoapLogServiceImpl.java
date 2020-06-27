package com.bank.icop.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.JwtUtil;
import com.bank.icop.dao.SoapLogDao;
import com.bank.icop.dos.SoapLogDO;
import com.bank.icop.service.SoapLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * SOAP调用第三方接口日志 服务实现类
 *
 * @author zhaozhongyuan
 * @since 2020-06-09
 */
@Service
public class SoapLogServiceImpl extends ServiceImpl<SoapLogDao, SoapLogDO> implements SoapLogService {

    @Resource
    SoapLogDao soapLogDao;




    @Override
    public IPage<SoapLogDO> listPage(PageQueryModel pageQueryModel) {
        Page<SoapLogDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
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
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        SoapLogDO soapLogDO = null;
        if (!MapUtil.isEmpty(queryParam)) {
            soapLogDO = BeanUtil.mapToBean(queryParam, SoapLogDO.class, false);
        }
        page.setRecords(soapLogDao.listPage(page, soapLogDO));
        return page;
    }

    @Override
    @Cacheable(cacheNames = "SoapLogServiceImpl.getUserInfo",key = "#token")
    public TokenUserInfo getUserInfo(String token) {
        return JwtUtil.getUserInfo(token);
    }
}
