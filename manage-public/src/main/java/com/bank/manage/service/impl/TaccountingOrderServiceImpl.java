package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.TaccountingOrderDao;
import com.bank.manage.dos.TaccountingOrderDO;
import com.bank.manage.dto.TaccountingOrderDTO;
import com.bank.manage.service.TaccountingOrderService;
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
 * 对账指令维护
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class TaccountingOrderServiceImpl extends ServiceImpl<TaccountingOrderDao, TaccountingOrderDO> implements TaccountingOrderService {

    @Autowired
    private TaccountingOrderDao taccountingOrderDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(TaccountingOrderDTO taccountingOrderDTO) {
        try {
            //先根据机构号查询 如果有则不插入
            LocalDateTime now = LocalDateTime.now();
            TaccountingOrderDO taccountingOrderDO = TaccountingOrderDO.builder()
                    .branch(taccountingOrderDTO.getBranch())
                    .cycle(taccountingOrderDTO.getCycle())
                    .numbers(taccountingOrderDTO.getNumbers())
                    .month(taccountingOrderDTO.getMonth())
                    .tellerInsert(taccountingOrderDTO.getTellerInsert())
                    .timeInsert(now)
                    .tellerUpdate(taccountingOrderDTO.getTellerInsert())
                    .timeUpdate(now)
                    .status("0")
                    .build();
            log.info("插入对账指令维护信息：{}", taccountingOrderDO);
            this.taccountingOrderDao.insert(taccountingOrderDO);
                
        }
        catch (Exception e) {
            log.error("插入对账指令维护信息失败！{}", e.getMessage());
            throw new BizException("插入对账指令维护信息失败！" + e.getMessage());
        }
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<String> list) {
        log.info("删除对账指令维护信息，帐号ID：{}", list);
        try {
            this.taccountingOrderDao.deleteBatchIds(list);
        }
        catch (Exception e) {
            log.error("删除对账指令维护信息失败！{}", e.getMessage());
            throw new BizException("删除对账指令维护信息失败！");
        }
        return true;
    }

    @Override
    public IPage<TaccountingOrderDTO> queryList(PageQueryModel pageQueryModel) {
        Page<TaccountingOrderDTO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = MapUtils.removeEmptyVal(pageQueryModel.getQueryParam());
        TaccountingOrderDTO taccountingOrderDTO=null;
        if(!MapUtils.isEmpty(queryParam)){
            taccountingOrderDTO= BeanUtil.mapToBean(queryParam, TaccountingOrderDTO.class,false);
        }
        List<TaccountingOrderDTO> taccountingOrderDTOS = taccountingOrderDao.selectPageList(page, taccountingOrderDTO);
        page.setRecords(taccountingOrderDTOS);
        log.info("对账指令维护信息查询：{}", taccountingOrderDTOS);
        return page;
    }

    @Override
    public Boolean updateTaccounTingOrder(TaccountingOrderDTO taccountingOrderDTO) {
        try {
            TaccountingOrderDO taccountingOrderDO = TaccountingOrderDO.builder()
                    .id(taccountingOrderDTO.getId())
                    .cycle(taccountingOrderDTO.getCycle())
                    .numbers(taccountingOrderDTO.getNumbers())
                    .month(taccountingOrderDTO.getMonth())
                    .status(taccountingOrderDTO.getStatus())
                    .tellerUpdate(taccountingOrderDTO.getTellerUpdate())
                    .timeUpdate(LocalDateTime.now())
                    .build();
            log.info("修改对账指令维护信息：{}", taccountingOrderDO);
            this.taccountingOrderDao.updateById(taccountingOrderDO);
        }
        catch (Exception e) {
            log.error("修改对账指令维护信息失败！{}", e.getMessage());
            throw new BizException("修改对账指令维护信息失败！" + e.getMessage());
        }
        return true;
    }
}
