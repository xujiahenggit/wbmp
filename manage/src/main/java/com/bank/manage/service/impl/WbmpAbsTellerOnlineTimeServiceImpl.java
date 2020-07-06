package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.WbmpAbsTellerOnlineTimeDao;
import com.bank.manage.dos.WbmpAbsOnlineTimeDO;
import com.bank.manage.dos.WbmpAbsTellerInfoDO;
import com.bank.manage.dos.WbmpAbsTellerOnlineTimeDO;
import com.bank.manage.service.WbmpAbsOnlineTimeService;
import com.bank.manage.service.WbmpAbsTellerInfoService;
import com.bank.manage.service.WbmpAbsTellerOnlineTimeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 柜员在线时长信息表 服务实现类
 *
 * @author 代码自动生成
 * @since 2020-07-03
 */
@Service
public class WbmpAbsTellerOnlineTimeServiceImpl extends ServiceImpl<WbmpAbsTellerOnlineTimeDao, WbmpAbsTellerOnlineTimeDO> implements WbmpAbsTellerOnlineTimeService {

    @Resource
    WbmpAbsTellerOnlineTimeDao wbmpAbsTellerOnlineTimeDao;

    @Override
    public IPage<WbmpAbsTellerOnlineTimeDO> listPage(PageQueryModel pageQueryModel) {
        Page<WbmpAbsTellerOnlineTimeDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
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
        WbmpAbsTellerOnlineTimeDO wbmpAbsTellerOnlineTimeDO = null;
        if (!MapUtils.isEmpty(queryParam)) {
            wbmpAbsTellerOnlineTimeDO = BeanUtil.mapToBean(queryParam, WbmpAbsTellerOnlineTimeDO.class, false);
        }
        page.setRecords(wbmpAbsTellerOnlineTimeDao.listPage(page, wbmpAbsTellerOnlineTimeDO));
        return page;
    }

    @Resource
    WbmpAbsOnlineTimeService wbmpAbsOnlineTimeService;

    @Resource
    WbmpAbsTellerInfoService wbmpAbsTellerInfoService;

    @Override
    public void fillDataBeat() {
        List<WbmpAbsTellerInfoDO> list = wbmpAbsTellerInfoService.list();
        for (WbmpAbsTellerInfoDO wbmpAbsTellerInfoDO : list) {
            String tellerId = wbmpAbsTellerInfoDO.getTellerId();
            WbmpAbsTellerOnlineTimeDO wbmpAbsTellerOnlineTimeDO = getById(tellerId);
            WbmpAbsTellerOnlineTimeDO onlineTimeDO = new WbmpAbsTellerOnlineTimeDO(wbmpAbsTellerInfoDO.getOrgId(),
                    tellerId,
                    wbmpAbsTellerInfoDO.getTellerName(),
                    null,
                    0
            );
            if (wbmpAbsTellerOnlineTimeDO == null) {
                saveOrUpdate(onlineTimeDO);
            }else {
                String date = DateUtil.format(wbmpAbsTellerOnlineTimeDO.getUpdateTime(),"yyyy-MM-dd");
                //清洗前一天数据
                if (!date.equals(DateUtil.today())){
                    wbmpAbsTellerOnlineTimeDao.clearAll();
                }
            }
            String tellerInd = wbmpAbsTellerInfoDO.getTellerInd();
            boolean online = isOnLine(tellerInd);
            long onLineTime = getCurrentDayOnLineTime(tellerId, online);
            onlineTimeDO.setOnlineTime(onLineTime);
            saveOrUpdate(onlineTimeDO);
        }


    }

    private long getCurrentDayOnLineTime(String tellerId, boolean online) {
        LambdaQueryWrapper<WbmpAbsOnlineTimeDO> queryWrapper = new LambdaQueryWrapper<WbmpAbsOnlineTimeDO>()
                .eq(WbmpAbsOnlineTimeDO::getTellerId, tellerId)
                .eq(WbmpAbsOnlineTimeDO::getRtnmsg, "交易成功")
                .eq(WbmpAbsOnlineTimeDO::getDataDt, DateUtil.today());
        List<WbmpAbsOnlineTimeDO> list = wbmpAbsOnlineTimeService.list(queryWrapper);
        long sum = 0;
        if (list.size() > 0) {
            List<WbmpAbsOnlineTimeDO> collect = list.stream().sorted((a, b) -> a.getRecordtime().compareTo(b.getRecordtime())).collect(Collectors.toList());
            int size = collect.size();
            String recordtime = "";
            for (int i = 0; i < size; i += 2) {
                WbmpAbsOnlineTimeDO wbmpAbsOnlineTimeDO = collect.get(i);
                if (i + 1 < size) {
                    WbmpAbsOnlineTimeDO next = collect.get(i + 1);
                    //如果取到连续登入或者登出，忽略数据
                    if (wbmpAbsOnlineTimeDO.getInterfacecode().equals(next.getInterfacecode())) {
                        continue;
                    }
                    if (next.getInterfacecode().equals("NLTTSM_5201")) {
                        continue;
                    }
                    if (wbmpAbsOnlineTimeDO.getInterfacecode().equals("NLTTSM_5202")) {
                        continue;
                    }
                    recordtime = next.getRecordtime();
                } else {
                    //如果是登入
                    if (wbmpAbsOnlineTimeDO.getRtncode().equals("NLTTSM_5201")&&online) {
                        recordtime = DateUtil.now();
                    } else {
                        recordtime = "";
                    }

                }
                sum += getTime(recordtime, wbmpAbsOnlineTimeDO.getRecordtime());
            }


        }
        return sum;

    }

    private long getTime(String recordtime, String recordtime1) {
        if (StrUtil.isBlank(recordtime) | StrUtil.isBlank(recordtime1)) {
            return 0;
        }

        long between = DateUtil.between(DateUtil.parseDateTime(recordtime),
                DateUtil.parseDateTime(recordtime1),
                DateUnit.SECOND, true);
        return between;

    }

    private boolean isOnLine(String tellerInd) {
        return tellerInd.equals("1") ? true : false;
    }


}
