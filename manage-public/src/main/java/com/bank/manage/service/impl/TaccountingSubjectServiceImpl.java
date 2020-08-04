package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dao.TaccountingSubjectDao;
import com.bank.manage.dos.TaccountingSubjectDO;
import com.bank.manage.dto.TaccountingSubjectDTO;
import com.bank.manage.service.TaccountingSubjectService;
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
 * 对账指令维护
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class TaccountingSubjectServiceImpl extends ServiceImpl<TaccountingSubjectDao, TaccountingSubjectDO> implements TaccountingSubjectService {

    @Autowired
    private TaccountingSubjectDao taccountingSubjectDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(TaccountingSubjectDTO taccountingSubjectDTO) {
        try {
            LocalDateTime now = LocalDateTime.now();
            TaccountingSubjectDO taccountingSubjectDO = TaccountingSubjectDO.builder()
                    .subject(taccountingSubjectDTO.getSubject())
                    .subjectname(taccountingSubjectDTO.getSubjectname())
                    .busiid(taccountingSubjectDTO.getBusiid())
                    .tellerInsert(taccountingSubjectDTO.getTellerInsert())
                    .timeInsert(now)
                    .tellerUpdate(taccountingSubjectDTO.getTellerInsert())
                    .timeUpdate(now)
                    .status("0")
                    .build();
            log.info("插入对账科目维护信息：{}", taccountingSubjectDO);
            this.taccountingSubjectDao.insert(taccountingSubjectDO);
                
        }
        catch (Exception e) {
            log.error("插入对账科目维护信息失败！{}", e.getMessage());
            throw new BizException("插入对账科目维护信息失败！" + e.getMessage());
        }
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<String> list) {
        log.info("删除对账科目维护信息，帐号ID：{}", list);
        try {
            this.taccountingSubjectDao.deleteBatchIds(list);
        }
        catch (Exception e) {
            log.error("删除对账指令科目信息失败！{}", e.getMessage());
            throw new BizException("删除对账科目维护信息失败！");
        }
        return true;
    }

    @Override
    public IPage<TaccountingSubjectDO> queryList(PageQueryModel pageQueryModel) {
        Page<TaccountingSubjectDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        //条件查询
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String subject = (String) queryParam.get("subject"); //机构号
        String subjectname = (String) queryParam.get("subjectname");
        String busiid = (String) queryParam.get("busiid");
        String status = (String) queryParam.get("status");

        QueryWrapper<TaccountingSubjectDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(subject), "SUBJECT", subject)
        .eq(StringUtils.isNotBlank(subjectname), "SUBJECTNAME", subjectname)
        .eq(StringUtils.isNotBlank(busiid), "BUSIID", busiid)
        .eq(StringUtils.isNotBlank(status), "STATUS", status);

        IPage<TaccountingSubjectDO> selectPage = this.taccountingSubjectDao.selectPage(page, queryWrapper);
        log.info("对账指令科目信息查询：{}", selectPage);
        return selectPage;
    }

    @Override
    public Boolean updateTaccountingSubject(TaccountingSubjectDTO taccountingSubjectDTO) {
        try {
            TaccountingSubjectDO taccountingSubjectDO = TaccountingSubjectDO.builder()
                    .id(taccountingSubjectDTO.getId())
                    .subject(taccountingSubjectDTO.getSubject())
                    .subjectname(taccountingSubjectDTO.getSubjectname())
                    .busiid(taccountingSubjectDTO.getBusiid())
                    .status(taccountingSubjectDTO.getStatus())
                    .tellerUpdate(taccountingSubjectDTO.getTellerUpdate())
                    .timeUpdate(LocalDateTime.now())
                    .build();
            log.info("修改对账科目维护信息：{}", taccountingSubjectDO);
            this.taccountingSubjectDao.updateById(taccountingSubjectDO);
        }
        catch (Exception e) {
            log.error("修改对账科目维护信息失败！{}", e.getMessage());
            throw new BizException("修改对账科目维护信息失败！" + e.getMessage());
        }
        return true;
    }
}
