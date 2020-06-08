package com.bank.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.HttpUtil;
import com.bank.manage.dao.ActivitieSalonDao;
import com.bank.manage.dos.ActivitieSalonDO;
import com.bank.manage.service.ActivitieSalonService;
import com.bank.manage.util.PDF2Image;
import com.bank.manage.vo.CutActivitieSalonVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 活动沙龙  服务实现类
 *
 */
@Service
@Slf4j
public class ActivitieSalonServiceImpl extends ServiceImpl<ActivitieSalonDao, ActivitieSalonDO>implements ActivitieSalonService {

    @Resource
    private ActivitieSalonDao activitieSalonDao;

    @Autowired
    private ConfigFileReader configFileReader;

    @Override
    public IPage<ActivitieSalonDO>listPage(PageQueryModel pageQueryModel){
            Page<ActivitieSalonDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

            if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
                if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                    page.setDesc(pageQueryModel.getSort());
                }
                else {
                    page.setAsc(pageQueryModel.getSort());
                }
            }
            Map<String, Object> queryParam = pageQueryModel.getQueryParam();
            String activitieName = (String)queryParam.get("activitieName");
            String activitieType = (String)queryParam.get("activitieType");
            String startTime = (String)queryParam.get("startTime");
            String endTime = (String)queryParam.get("endTime");
            IPage<ActivitieSalonDO> iPage=  activitieSalonDao.queryActivitiesPage(page,activitieName,activitieType,startTime,endTime);
            List<ActivitieSalonDO> records = iPage.getRecords();
            if(CollectionUtil.isNotEmpty(records)){
                for (ActivitieSalonDO record : records) {
                    record.setActivitiePath(configFileReader.getHTTP_PATH()+ record.getActivitiePath());
                }
            }
            return iPage;
        }

    @Override
    public IPage<ActivitieSalonDO> queryActivitieSalon(PageQueryModel pageQueryModel) {
        Page<ActivitieSalonDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String orgId = (String)queryParam.get("orgId");
        QueryWrapper<ActivitieSalonDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ORG_ID",orgId);
        IPage<ActivitieSalonDO> iPage = activitieSalonDao.selectPage(page, queryWrapper);
        List<ActivitieSalonDO> records = iPage.getRecords();
        if(CollectionUtil.isNotEmpty(records)){
            for (ActivitieSalonDO record : records) {
                record.setActivitiePath(configFileReader.getHTTP_PATH()+ record.getActivitiePath());
            }
        }
        return iPage;
    }

    @Override
    public void cutActivitieSalon(CutActivitieSalonVo cutActivitieSalonVo) {
        ActivitieSalonDO salonDO = activitieSalonDao.selectById(cutActivitieSalonVo.getActivitieSalonId());
        Map<String,Object> map = new HashMap<>();
        map.put("orgId",cutActivitieSalonVo.getOrgId());
        map.put("deviceNo",cutActivitieSalonVo.getDeviceNo());
        map.put("code","000030");
        Map<String,Object> msg = new HashMap<>();
        msg.put("ACTIVITIE_ID",salonDO.getId());
        msg.put("ACTIVITIE_NAME",salonDO.getActivitieName());
        msg.put("ACTIVITIE_PATH",configFileReader.getHTTP_PATH()+ salonDO.getActivitiePath());
        map.put("msg",msg);
        log.info("活动沙龙发送数据，{}",map);
        HttpUtil.sendPost(configFileReader.getMESSAGE_PATH(),map);
    }

    @Override
    public Boolean saveActivitieSalon(ActivitieSalonDO activitieSalon, TokenUserInfo tokenUserInfo) {
        ActivitieSalonDO salonDO = new ActivitieSalonDO();
        salonDO.setActivitieName(activitieSalon.getActivitieName());
        salonDO.setActivitieType(activitieSalon.getActivitieType());
        salonDO.setActivitiePath(activitieSalon.getActivitiePath());
        salonDO.setActivitieDesc(activitieSalon.getActivitieDesc());
        salonDO.setActivitieStatus("0");
        salonDO.setOrgId(activitieSalon.getOrgId());
        salonDO.setOrgName(activitieSalon.getOrgName());
        salonDO.setCreatedTime(LocalDateTime.now());
        salonDO.setCreatedBy(tokenUserInfo.getUserId());
        activitieSalonDao.insert(salonDO);

        //PDF2Image.pdf2Image()

        return null;
    }
}
