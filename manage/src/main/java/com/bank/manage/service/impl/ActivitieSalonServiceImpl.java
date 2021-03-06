package com.bank.manage.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.HttpUtil;
import com.bank.core.utils.NetUtil;
import com.bank.manage.dao.ActivitieSalonDao;
import com.bank.manage.dao.ActivitieSalonImageDao;
import com.bank.manage.dos.ActivitieSalonDO;
import com.bank.manage.dos.ActivitieSalonImageDO;
import com.bank.manage.service.ActivitieSalonImageService;
import com.bank.manage.service.ActivitieSalonService;
import com.bank.manage.util.PDF2Image;
import com.bank.manage.vo.ActivitieSalonVO;
import com.bank.manage.vo.CutActivitieSalonVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 活动沙龙  服务实现类
 *
 */
@Service
@Slf4j
public class ActivitieSalonServiceImpl extends ServiceImpl<ActivitieSalonDao, ActivitieSalonDO> implements ActivitieSalonService {

    @Resource
    private ActivitieSalonDao activitieSalonDao;

    @Autowired
    private ConfigFileReader configFileReader;

    @Resource
    private ActivitieSalonImageDao activitieSalonImageDao;

    @Autowired
    private ActivitieSalonImageService activitieSalonImageService;

    @Resource
    NetUtil netUtil;

    @Override
    public IPage<ActivitieSalonVO> listPage(PageQueryModel pageQueryModel) {
        Page<ActivitieSalonVO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String activitieName = (String) queryParam.get("activitieName");
        String activitieType = (String) queryParam.get("activitieType");
        String startTime = (String) queryParam.get("startTime");
        String endTime = (String) queryParam.get("endTime");
        IPage<ActivitieSalonVO> iPage = activitieSalonDao.queryActivitiesPage(page, activitieName, activitieType, startTime, endTime);
        List<ActivitieSalonVO> records = iPage.getRecords();
        if (CollectionUtil.isNotEmpty(records)) {
            for (ActivitieSalonVO record : records) {
                record.setActivitiePath(netUtil.getUrlSuffix("") + record.getActivitiePath());
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
        String orgId = (String) queryParam.get("orgId");
        QueryWrapper<ActivitieSalonDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ORG_ID", orgId);
        queryWrapper.eq("STATUS", 0);
        IPage<ActivitieSalonDO> iPage = activitieSalonDao.selectPage(page, queryWrapper);
        List<ActivitieSalonDO> records = iPage.getRecords();
        if (CollectionUtil.isNotEmpty(records)) {
            for (ActivitieSalonDO record : records) {
                record.setActivitiePath(netUtil.getUrlSuffix("") + record.getActivitiePath());
            }
        }
        return iPage;
    }

    @Override
    public void cutActivitieSalon(CutActivitieSalonVo cutActivitieSalonVo) {
        List<Map<String, Object>> salonDO = activitieSalonDao.selectActivitieSalonById(cutActivitieSalonVo.getActivitieSalonId());
        Map<String, Object> map = new HashMap<>();
        map.put("orgId", cutActivitieSalonVo.getOrgId());
        map.put("deviceNo", cutActivitieSalonVo.getDeviceNo());
        map.put("code", "000030");
        Map<String, Object> msg = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < salonDO.size(); i++) {
            String imagePath = (String) salonDO.get(i).get("IMAGE_PATH");
            //空值直接跳出本次循环
            if (imagePath == null) {
                continue;
            }
            Integer sort = (Integer) salonDO.get(i).get("SORT");
            Map<String, Object> pathMap = new HashMap<>();
            pathMap.put("imagePath", netUtil.getUrlSuffix("") + imagePath);
            pathMap.put("sort", sort);
            list.add(pathMap);
        }
        msg.put("activitieId", salonDO.get(0).get("ID"));
        msg.put("activitieName", salonDO.get(0).get("ACTIVITIE_NAME"));
        msg.put("activitiePath", list);
        map.put("msg", msg);
        log.info("活动沙龙切换信息：{}", JSON.toJSONString(map));
        HttpUtil.sendPost(netUtil.getUrlSuffix() + "/jms/topic", map);
    }

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveActivitieSalon(ActivitieSalonDO activitieSalon, TokenUserInfo tokenUserInfo) {
        try {
            ActivitieSalonDO salonDO = new ActivitieSalonDO();
            salonDO.setActivitieName(activitieSalon.getActivitieName());
            salonDO.setActivitieType(activitieSalon.getActivitieType());
            salonDO.setActivitiePath(activitieSalon.getActivitiePath());
            salonDO.setActivitieDesc(activitieSalon.getActivitieDesc());
            salonDO.setActivitieFileName(activitieSalon.getActivitieFileName());
            salonDO.setActivitieStatus("0");
            salonDO.setOrgId(activitieSalon.getOrgId());
            salonDO.setOrgName(activitieSalon.getOrgName());
            salonDO.setCreatedTime(LocalDateTime.now());
            salonDO.setCreatedBy(tokenUserInfo.getUserId());
            salonDO.setStatus(1);
            activitieSalonDao.insert(salonDO);
            String pdfPath = activitieSalon.getActivitiePath();
            log.info("活动沙龙pdf文件服务器存储目录{}", pdfPath);
            executorService.execute(() -> convert2Image(pdfPath, salonDO.getId()));
        }
        catch (Exception e) {
            log.error("活动沙龙数据新增失败{}", e.getMessage());
            throw new BizException("活动沙龙数据新增失败");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public void convert2Image(String activitiePath, Integer id) {
        List<String> imageList = PDF2Image.pdf2Image(activitiePath);
        List<ActivitieSalonImageDO> list = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(imageList)) {
            for (String s : imageList) {
                ActivitieSalonImageDO imageDO = new ActivitieSalonImageDO();
                imageDO.setActivitieId(id);
                //String imgPath = StringUtils.replace(s, this.configFileReader.getACTIVITIE_FILE_PATH(), this.configFileReader.getACTIVITIE_ACCESS_PATH());
                imageDO.setImagePath(s);
                String[] s1 = s.split("_");
                String s2 = s1[1];
                String substring = s2.substring(0, s2.lastIndexOf("."));
                imageDO.setSort(Integer.parseInt(substring));
                list.add(imageDO);
            }
            try {
                activitieSalonImageService.saveBatch(list);

                ActivitieSalonDO salonDO = new ActivitieSalonDO();
                salonDO.setId(id);
                salonDO.setStatus(0);
                activitieSalonDao.updateById(salonDO);
            }
            catch (Exception e) {
                throw new BizException("数据解析失败！");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeActivitieSalonByIds(List<Integer> ids) {
        try {
            activitieSalonDao.deleteBatchIds(ids);
            activitieSalonImageDao.removeActivitieSalonImageByIds(ids);
            return true;
        }
        catch (Exception e) {
            log.error("活动沙龙数据删除失败,{}", e.getMessage());
            throw new BizException("活动沙龙数据删除失败");
        }
    }

}
