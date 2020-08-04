package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dao.TlimitTimeDao;
import com.bank.manage.dos.TlimitTimeDO;
import com.bank.manage.dto.TlimitTimeDTO;
import com.bank.manage.service.TlimitTimeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 逾期催收时限
 *
 * @author
 * @date 2020-7-7
 */
@Service
@Slf4j
public class TlimitTimeServiceImpl extends ServiceImpl<TlimitTimeDao, TlimitTimeDO> implements TlimitTimeService {

    @Autowired
    private TlimitTimeDao tlimitTimeDao;

    @Override
    public IPage<TlimitTimeDO> queryList(PageQueryModel pageQueryModel) {
        Page<TlimitTimeDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        //查询用户的帐号 如果是总行就查询所有 否则只能查询本机构下的信息（未实现）
        //条件查询
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        //如果是总行 可以使用机构号搜索 前端控制
        String branch = (String) queryParam.get("branch");

        QueryWrapper<TlimitTimeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(branch), "BRANCH", branch)   ;

        IPage<TlimitTimeDO> selectPage = this.tlimitTimeDao.selectPage(page, queryWrapper);
        log.info("催收时限信息查询：{}", selectPage);
        return selectPage;
    }

    @Override
    public Boolean updateTlimitTime(TlimitTimeDTO tlimitTimeDTO) {
        try {
            TlimitTimeDO tlimitTimeDO = TlimitTimeDO.builder()
                    .id(tlimitTimeDTO.getId())
                    .timeLimit(tlimitTimeDTO.getTimeLimit())
                    .frequency(tlimitTimeDTO.getFrequency())
                    .tellerUpdate(tlimitTimeDTO.getTellerUpdate())
                    .timeUpdate(LocalDateTime.now())
                    .build();
            log.info("设置催收时限：{}", tlimitTimeDO);
            this.tlimitTimeDao.updateById(tlimitTimeDO);
        }
        catch (Exception e) {
            log.error("修改催收时限失败！{}", e.getMessage());
            throw new BizException("修改催收时限失败！" + e.getMessage());
        }
        return true;
    }

    @Override
    public Boolean selectByBranch(String branch) {
        QueryWrapper<TlimitTimeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(branch), "BRANCH", branch);
        TlimitTimeDO tlimitTimeDO = this.tlimitTimeDao.selectOne(queryWrapper);
        if(null == tlimitTimeDO) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<String> list) {
        log.info("删除逾期催收时限信息，ID：{}", list);
        try {
            this.tlimitTimeDao.deleteBatchIds(list);
        }
        catch (Exception e) {
            log.error("删除逾期催收时限信息失败！{}", e.getMessage());
            throw new BizException("删除逾期催收时限信息失败！");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(TlimitTimeDTO tlimitTimeDTO) {
        try {
            LocalDateTime now = LocalDateTime.now();
            TlimitTimeDO tlimitTimeDO = TlimitTimeDO.builder()
                    .branch(tlimitTimeDTO.getBranch())
                    .timeLimit(StringUtils.isNotBlank(tlimitTimeDTO.getTimeLimit()) ? tlimitTimeDTO.getTimeLimit() : "10")
                    .frequency(StringUtils.isNotBlank(tlimitTimeDTO.getFrequency()) ? tlimitTimeDTO.getFrequency() : "3")
                    .tellerInsert(tlimitTimeDTO.getTellerInsert())
                    .timeInsert(now)
                    .tellerUpdate(tlimitTimeDTO.getTellerInsert())
                    .timeUpdate(now)
                    .status("1")
                    .build();
            log.info("插入逾期催收时限信息：{}", tlimitTimeDO);
            this.tlimitTimeDao.insert(tlimitTimeDO);

        }
        catch (Exception e) {
            log.error("插入逾期催收时限信息失败！{}", e.getMessage());
            throw new BizException("插入逾期催收时限信息失败！" + e.getMessage());
        }
        return true;
    }
}
