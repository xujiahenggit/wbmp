package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dao.ActivitieSalonDao;
import com.bank.manage.dao.ActivitieSalonLogDao;
import com.bank.manage.dos.ActivitieSalonDO;
import com.bank.manage.dos.ActivitieSalonLogDO;
import com.bank.manage.service.ActivitieSalonLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * 活动沙龙流水  服务实现类
 *
 */
@Service
@Slf4j
 public class ActivitieSalonLogServiceImpl extends ServiceImpl<ActivitieSalonLogDao, ActivitieSalonLogDO>implements ActivitieSalonLogService {

        @Resource
        private ActivitieSalonLogDao activitieSalonLogDao;

        @Autowired
        private ActivitieSalonDao activitieSalonDao;

        @Override
        public IPage<ActivitieSalonLogDO>listPage(PageQueryModel pageQueryModel){
            Page<ActivitieSalonLogDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

            if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
                if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                    page.setDesc(pageQueryModel.getSort());
                }
                else {
                    page.setAsc(pageQueryModel.getSort());
                }
            }
            QueryWrapper<ActivitieSalonLogDO> queryWrapper = new QueryWrapper();
            IPage<ActivitieSalonLogDO> page1=  activitieSalonLogDao.selectPage(page,queryWrapper);
            return page1;
        }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveActivitieSalonLog(ActivitieSalonLogDO activitieSalonLog) {
        try {
            activitieSalonLog.setStartTime(LocalDateTime.now());
            activitieSalonLogDao.insert(activitieSalonLog);

            Integer activitieId = activitieSalonLog.getActivitieId();
            BigDecimal playTime = activitieSalonLog.getPlayTime();

            ActivitieSalonDO salonDO = activitieSalonDao.selectById(activitieId);
            if(salonDO != null){
                BigDecimal activitieTime = salonDO.getActivitieTime();
                Integer activitieTotal = salonDO.getActivitieTotal();
                BigDecimal total = new BigDecimal("0.00");
                if(activitieTime != null){
                    total = playTime.add(activitieTime);
                }else{
                    total = total.add(playTime);
                }
                salonDO.setActivitieTime(total);
                salonDO.setActivitieTotal(activitieTotal+1);
                activitieSalonDao.updateById(salonDO);
            }
            return true;
        } catch (Exception e) {
            log.error("活动沙龙日志记录失败,{}",e.getMessage());
            throw new BizException("活动沙龙日志记录失败");
        }
    }
}
