package com.bank.manage.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.sysConst.SysStatus;
import com.bank.core.utils.DateUtils;
import com.bank.core.utils.PropertyUtil;
import com.bank.manage.dao.UsherDao;
import com.bank.manage.dao.UsherPopulationDao;
import com.bank.manage.dao.UsherWorkDaysDao;
import com.bank.manage.dos.UsherDO;
import com.bank.manage.dos.UsherPopulationDO;
import com.bank.manage.dos.UsherWorkDaysDO;
import com.bank.manage.dto.UsherDTO;
import com.bank.manage.dto.UsherPopulationDTO;
import com.bank.manage.service.UsherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.StrUtil;

/**
 * ClassName: UsherServiceImpl
 *
 * @author Yanwen D. Ding
 * <p>
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 * <p>
 * All Rights Reserved
 * <p>
 * http://www.yusys.com.cn
 * <p>
 * Create Time: 2020/04/21 14:46:16
 */
@Service
public class UsherServiceImpl implements UsherService {

    @Autowired
    private UsherDao usherDao;

    @Autowired
    private UsherPopulationDao usherPopulationDao;

    @Autowired
    private UsherWorkDaysDao usherWorkDaysDao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer insert(UsherDTO usherDTO, String currentUser) {
        QueryWrapper<UsherPopulationDO> queryWrapperEx = new QueryWrapper<>();
        queryWrapperEx.eq("ORG_ID", usherDTO.getOrgId());
        UsherPopulationDO usherPopulationDO = this.usherPopulationDao.selectOne(queryWrapperEx);
        if (usherPopulationDO != null) {
            //查询当前机构下引导员人数
            QueryWrapper<UsherDO> queryWrapperExt = new QueryWrapper<>();
            queryWrapperExt.eq("ORG_ID", usherDTO.getOrgId()).eq("USHER_DELFLAG", "0");
            int usherCount = this.usherDao.selectCount(queryWrapperExt);

            if (usherCount >= usherPopulationDO.getPopulationLimit().intValue()) {
                throw new BizException("机构[" + usherDTO.getOrgName() + "]已存在引导员" + usherCount + "人，引导员人数不允许超过人数控制，请调整该机构人数控制");
            }
        }

        QueryWrapper<UsherDO> queryWrapper = new QueryWrapper<>();
        //手机号+未删除 唯一校验
        queryWrapper.eq("PHONE_NO", usherDTO.getPhoneNo()).eq("USHER_DELFLAG", "0");
        if (this.usherDao.selectCount(queryWrapper) > 0) {
            throw new BizException("根据手机号[" + usherDTO.getPhoneNo() + "]已查找到对应的引导员信息，不允许新增");
        }

        String identityNo = usherDTO.getIdentityNo();
        ValidIdentityNo(identityNo);
        UsherDO usherDO = new UsherDO();
        PropertyUtil.copyProperties(usherDTO, usherDO, "usherId");
        usherDO.setSex(IdcardUtil.getGenderByIdCard(identityNo));
        usherDO.setCreatedBy(currentUser);
        usherDO.setCreatedTime(LocalDateTime.now());
        usherDO.setUpdatedBy(currentUser);
        usherDO.setUpdatedTime(LocalDateTime.now());
        this.usherDao.insert(usherDO);

        //新增本月出勤天数数据
        UsherWorkDaysDO workDays = new UsherWorkDaysDO();
        workDays.setUsherId(usherDO.getUsherId());
        workDays.setWorkYearMonth(DateUtil.format(new Date(), "yyyy-MM"));
        workDays.setWorkDays(usherDTO.getWorkDays());
        return this.usherWorkDaysDao.insert(workDays);
    }

    private void ValidIdentityNo(String identityNo) {
        if (!IdcardUtil.isValidCard(identityNo)) {
            throw new BizException("无效的身份证号[" + identityNo + "]");
        }

        if (this.usherDao.selectCount(new LambdaQueryWrapper<UsherDO>().eq(UsherDO::getIdentityNo, identityNo).eq(UsherDO::getUsherDelflag, "0")) > 0) {
            throw new BizException("根据身份证号[" + identityNo + "]已查找到对应的引导员信息");
        }
    }

    @Override
    public Integer insertBatch(List<UsherDO> usherDOList) {
        return this.usherDao.insertBatch(usherDOList);
    }

    @Override
    public Integer deleteById(Integer id, String currentUser) {
        UsherDO usherDO = new UsherDO();
        usherDO.setUsherId(id);
        usherDO.setUpdatedBy(currentUser);
        usherDO.setUpdatedTime(LocalDateTime.now());
        usherDO.setUsherDelflag("1");
        //逻辑删除
        return this.usherDao.updateById(usherDO);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer updateById(UsherDTO usherDTO, String currentUser) {
        QueryWrapper<UsherDO> queryWrapper = new QueryWrapper<>();
        //手机号+未删除 唯一校验
        queryWrapper.eq("PHONE_NO", usherDTO.getPhoneNo()).eq("USHER_DELFLAG", "0");

        UsherDO usherDOE = this.usherDao.selectOne(queryWrapper);
        if (usherDOE != null && !usherDOE.getUsherId().equals(usherDTO.getUsherId())) {
            throw new BizException("更改手机号[" + usherDTO.getPhoneNo() + "]与其他引导员信息冲突，不允许更新");
        }

        UsherDO usherDO = new UsherDO();
        PropertyUtil.copyProperties(usherDTO, usherDO);
        String identityNo = usherDTO.getIdentityNo();
        ValidIdentityNo(identityNo);//校验身份证号码有效性
        UsherDO usher = this.usherDao.selectOne(new LambdaQueryWrapper<UsherDO>().eq(UsherDO::getIdentityNo, identityNo).eq(UsherDO::getUsherDelflag, "0"));
        if (usher != null && !usher.getIdentityNo().equals(identityNo)) {
            throw new BizException("更改身份证号[" + identityNo + "]与其他引导员信息冲突，不允许更新");
        }

        if (!StrUtil.isBlankIfStr(identityNo)) {
            usherDO.setIdentityNo(identityNo);
            usherDO.setSex(IdcardUtil.getGenderByIdCard(identityNo));
        }
        usherDO.setUpdatedBy(currentUser);
        usherDO.setUpdatedTime(LocalDateTime.now());
        int updateRow = this.usherDao.updateById(usherDO);

        //主表未更新到数据，从表不做操作
        if (updateRow > 0) {
            UsherWorkDaysDO workDays = new UsherWorkDaysDO();
            workDays.setUsherId(usherDTO.getUsherId());
            workDays.setWorkYearMonth(DateUtil.format(new Date(), "yyyy-MM"));
            workDays.setWorkDays(usherDTO.getWorkDays());

            UpdateWrapper<UsherWorkDaysDO> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("USHER_ID", usherDTO.getUsherId()).eq("WORK_YEAR_MONTH", DateUtil.format(new Date(), "yyyy-MM"));

            updateRow = this.usherWorkDaysDao.update(workDays, updateWrapper);
            //未更新到数据就新增
            if (updateRow == 0) {
                updateRow = this.usherWorkDaysDao.insert(workDays);
            }
        }
        return updateRow;
    }

    @Override
    public IPage<UsherDTO> selectPageExt(PageQueryModel pageQueryModel) {
        Page<UsherDTO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }

        pageQueryModel.getQueryParam().put("workYearMonth", DateUtil.format(new Date(), "yyyy-MM"));
        IPage<UsherDTO> usherDTOIPage = this.usherDao.selectPageExt(page, pageQueryModel.getQueryParam());
        int workDay = DateUtils.getCuruentMonthWorkDays();
        List<UsherDTO> list = usherDTOIPage.getRecords().stream().map(usherDTO -> {
            Integer workDays = usherDTO.getWorkDays();
            if (workDays == null) {
                usherDTO.setWorkDays(workDay);
            }
            String identityNo = usherDTO.getIdentityNo();
            if (!StrUtil.isBlankIfStr(identityNo)) {
                usherDTO.setAge(IdcardUtil.getAgeByIdCard(identityNo));
            }
            return usherDTO;
        }).collect(Collectors.toList());

        usherDTOIPage.setRecords(list);

        return usherDTOIPage;
    }

    @Override
    public Integer insertBatchLimit(List<UsherPopulationDO> usherPopulationDOList) {
        return this.usherPopulationDao.insertBatch(usherPopulationDOList);
    }

    @Override
    public List<UsherPopulationDO> selectUsherPopulation(String orgName) {
        return this.usherPopulationDao.selectOrgUsherPopulation(orgName);
    }

    /**
     * 用手机号码查询 引导员信息
     * @param userPhone 手机号码
     * @return
     */
    @Override
    public UsherDO selectUsherByPhone(String userPhone) {
        QueryWrapper<UsherDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("PHONE_NO", userPhone);
        queryWrapper.eq("USHER_DELFLAG", SysStatus.FLAG_NO_DELETE);
        UsherDO usherDO = this.usherDao.selectOne(queryWrapper);
        return usherDO;
    }

    /**
     * 查询所有可用引导员
     * @return
     */
    @Override
    public List<UsherDO> SelectUseFullUsher() {
        QueryWrapper<UsherDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USHER_DELFLAG", 0);
        List<UsherDO> list = this.usherDao.selectList(queryWrapper);
        return list;
    }

    @Override
    public List<UsherDO> queryUsherByPhone(String phone) {
        QueryWrapper<UsherDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USHER_DELFLAG", 0);
        queryWrapper.eq("PHONE_NO", phone);
        return this.usherDao.selectList(queryWrapper);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer insertLimit(UsherPopulationDO usherPopulationDO) {
        String orgId = usherPopulationDO.getOrgId();
        QueryWrapper<UsherPopulationDO> queryWrapper = new QueryWrapper<>();
        //机构号 唯一校验
        queryWrapper.eq("ORG_ID", orgId);
        if (this.usherPopulationDao.selectCount(queryWrapper) > 0) {
            throw new BizException("根据机构号[" + orgId + "]已查找到对应的引导员人员控制，不允许新增");
        }

        if (this.usherPopulationDao.checkOrgExist(orgId) == 0) {
            throw new BizException("机构号[" + orgId + "]在系统中不存在");
        }

        return this.usherPopulationDao.insert(usherPopulationDO);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer saveLimit(List<UsherPopulationDTO> usherPopulationDTOList, String currentUser) {
        for (int i = 0; i < usherPopulationDTOList.size(); i++) {
            UsherPopulationDTO usherPopulationDTO = usherPopulationDTOList.get(i);
            String orgId = usherPopulationDTO.getOrgId();

            //查询当前机构下引导员人数
            QueryWrapper<UsherDO> queryWrapperEx = new QueryWrapper<>();
            queryWrapperEx.eq("ORG_ID", orgId).eq("USHER_DELFLAG", "0");
            int usherCount = this.usherDao.selectCount(queryWrapperEx);

            if (usherCount > usherPopulationDTO.getPopulationLimit().intValue()) {
                throw new BizException("机构[" + usherPopulationDTO.getOrgName() + "]已存在引导员" + usherCount + "人，人数控制不允许小于它");
            }

            QueryWrapper<UsherPopulationDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("ORG_ID", orgId);
            UsherPopulationDO usherPopulationDO = this.usherPopulationDao.selectOne(queryWrapper);

            if (usherPopulationDO == null) {
                usherPopulationDO = new UsherPopulationDO();
                PropertyUtil.copyProperties(usherPopulationDTO, usherPopulationDO, "usherPopulationId");
                usherPopulationDO.setCreatedBy(currentUser);
                usherPopulationDO.setCreatedTime(new Date());
                usherPopulationDO.setUpdatedBy(currentUser);
                usherPopulationDO.setUpdatedTime(new Date());
                this.usherPopulationDao.insert(usherPopulationDO);
            }
            else {
                PropertyUtil.copyProperties(usherPopulationDTO, usherPopulationDO, "usherPopulationId");
                usherPopulationDO.setUpdatedBy(currentUser);
                usherPopulationDO.setUpdatedTime(new Date());
                this.usherPopulationDao.updateById(usherPopulationDO);
            }
        }
        return usherPopulationDTOList.size();
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer batchSetLimit(Integer limit, String currentUser) {
        UsherPopulationDO usherPopulationDO = new UsherPopulationDO();
        usherPopulationDO.setPopulationLimit(limit);
        usherPopulationDO.setUpdatedBy(currentUser);
        usherPopulationDO.setUpdatedTime(new Date());
        //全更新
        return this.usherPopulationDao.update(usherPopulationDO, null);

    }

}
