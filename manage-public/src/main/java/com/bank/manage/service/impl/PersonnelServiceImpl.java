package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.PersonnelDao;
import com.bank.manage.dos.PersonnelDO;
import com.bank.manage.service.PersonnelService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 短信预警人员
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class PersonnelServiceImpl extends ServiceImpl<PersonnelDao, PersonnelDO> implements PersonnelService {

    @Autowired(required = false)
    private PersonnelDao personnelDao;

    @Override
    public IPage<PersonnelDO> queryList(PageQueryModel pageQueryModel, String powerNum) {
        Page<PersonnelDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        PersonnelDO personnelDO = null;
        if (!MapUtils.isEmpty(queryParam)) {
            personnelDO = BeanUtil.mapToBean(queryParam, PersonnelDO.class, false);
            personnelDO.setPowerNum(powerNum);
        }
        List<PersonnelDO> list = personnelDao.queryList(page, personnelDO);
        page.setRecords(list);
        log.info("短信预警人员查询：{}", list);
        return page;
    }

    @Override
    public boolean save(PersonnelDO personnelDO) {
        QueryWrapper<PersonnelDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("STROPERATORNUM", personnelDO.getStrOperatorNum());
        Integer integer = personnelDao.selectCount(queryWrapper);
        if (integer > 0) {
            return false;
        }
        try {
            personnelDO.setStrAlertType("0");
            personnelDO.setStrValidTimeStart("08:30:00");
            personnelDO.setStrValidTimeEnd("18:00:00");
            personnelDO.setCreateDate(LocalDateTime.now());
            personnelDao.insert(personnelDO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        log.info("短信预警人员新增：{}", personnelDO);
        return true;
    }

    @Override
    public boolean update(PersonnelDO personnelDO) {
        PersonnelDO build = PersonnelDO.builder()
                .strAlertType(personnelDO.getStrAlertType())
                .id(personnelDO.getId()).build();
        try {
            personnelDao.updateById(build);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        log.info("短信预警人员修改：{}", build);
        return true;
    }

    @Override
    public boolean delete(List<String> list) {
        try {
            personnelDao.deleteBatchIds(list);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        log.info("短信预警人员批量删除：{}", list);
        return true;
    }

    @Override
    public PersonnelDO getOne(String strOperatorNum) {
        PersonnelDO personnelDO = personnelDao.getOne(strOperatorNum);
        log.info("短信预警人员查询工号：{}", personnelDO);
        return personnelDO;
    }
}
