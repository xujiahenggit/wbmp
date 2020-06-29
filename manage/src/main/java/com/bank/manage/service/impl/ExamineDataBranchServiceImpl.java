package com.bank.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.PropertyUtil;
import com.bank.manage.dao.ExamineDataAdminDao;
import com.bank.manage.dao.ExamineDataBranchDao;
import com.bank.manage.dao.ExamineDataTempAdminDao;
import com.bank.manage.dao.ExamineDataTempBranchDao;
import com.bank.manage.dos.ExamineDataAdminDO;
import com.bank.manage.dos.ExamineDataBranchDO;
import com.bank.manage.dos.ExamineDataTempAdminDO;
import com.bank.manage.dos.ExamineDataTempBranchDO;
import com.bank.manage.service.ExamineDataBranchService;
import com.bank.manage.vo.ExamineAnalyzeParmVo;
import com.bank.manage.vo.ExamineAnalyzeVo;
import com.bank.manage.vo.ExamineDataRankVo;
import com.bank.manage.vo.ExamineDeduVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ExamineDataBranchServiceImpl implements ExamineDataBranchService {

    @Autowired
    private ExamineDataBranchDao examineDataBranchDao;

    @Autowired
    private ExamineDataAdminDao examineDataAdminDao;

    @Autowired
    private ExamineDataTempBranchDao examineDataTempBranchDao;

    @Autowired
    private ExamineDataTempAdminDao examineDataTempAdminDao;

    @Override
    public void saveBranchData(Map<String, Object> mapData) {
        try {
            for (Map.Entry<String, Object> entry : mapData.entrySet()) {
                //log.info("分行：key= " + entry.getKey() + " and value= " + entry.getValue());
                Map<String, Object> branchValue = (Map<String, Object>) entry.getValue();
                ExamineDataBranchDO examineDataBranchFH = (ExamineDataBranchDO) branchValue.get("FH_DATA");
                ExamineDataAdminDO examineDataAdminFH = new ExamineDataAdminDO();
                PropertyUtil.copyProperties(examineDataBranchFH, examineDataAdminFH);
                examineDataBranchDao.insert(examineDataBranchFH);
                examineDataAdminDao.insert(examineDataAdminFH);
                for (Map.Entry<String, Object> entry1 : branchValue.entrySet()) {
                    if("FH_DATA".equals(entry1.getKey())){
                        continue;
                    }
                    //log.info("网点：key= " + entry1.getKey() + " and value= " + entry1.getValue());
                    Map<String, Object> outOrgValue = (Map<String, Object>) entry1.getValue();

                    ExamineDataBranchDO examineDataBranchWD = (ExamineDataBranchDO) outOrgValue.get("WD_DATA");
                    examineDataBranchWD.setExamineId(examineDataBranchFH.getId());
                    examineDataBranchDao.insert(examineDataBranchWD);

                    ExamineDataAdminDO examineDataAdminWD = new ExamineDataAdminDO();
                    PropertyUtil.copyProperties(examineDataBranchWD, examineDataAdminWD);
                    examineDataAdminWD.setExamineId(examineDataAdminFH.getId());
                    examineDataAdminDao.insert(examineDataAdminWD);

                    List<ExamineDataTempBranchDO> ExamineDataTempBranchWDList = (List<ExamineDataTempBranchDO>) outOrgValue.get("WDTEMP_DATA");
                    for (ExamineDataTempBranchDO e:ExamineDataTempBranchWDList) {
                        e.setExamineId(examineDataBranchWD.getId());
                        examineDataTempBranchDao.insert(e);

                        ExamineDataTempAdminDO examineDataTempAdminDO = new ExamineDataTempAdminDO();
                        PropertyUtil.copyProperties(e, examineDataTempAdminDO);
                        examineDataTempAdminDO.setExamineId(examineDataAdminWD.getId());
                        examineDataTempAdminDao.insert(examineDataTempAdminDO);
                    }
                }
            }
        } catch (Exception e) {
            log.error("季度考核数据保存失败！{}",e.getMessage());
            throw new BizException("季度考核数据保存失败！");
        }
    }

    @Override
    public IPage<ExamineDataRankVo> queryExamineDataRank(PageQueryModel pageQueryModel) {
        Page<ExamineDataRankVo> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC" , pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String startYear = (String) queryParam.get("startYear");//统计开始年份
        String startQuarter = (String) queryParam.get("startQuarter");//统计开始季度
        String endYear = (String) queryParam.get("endYear");//统计结束年份
        String endQuarter = (String) queryParam.get("endQuarter");//统计结束季度
        String excelType = (String) queryParam.get("excelType");//报表类型 0 分支行 1 网点
        IPage<ExamineDataRankVo> examineDataRankVoPage = null;
        Integer pageIndex = pageQueryModel.getPageIndex();
        Integer pageSize = pageQueryModel.getPageSize();
        if("0".equals(excelType)){
            examineDataRankVoPage = examineDataBranchDao.queryExamineDataRankFH(page,startYear,startQuarter,endYear,endQuarter);
            if(CollectionUtil.isNotEmpty(examineDataRankVoPage.getRecords())){
                for (int i = 0; i < examineDataRankVoPage.getRecords().size(); i++) {
                    if(pageIndex==1){
                        examineDataRankVoPage.getRecords().get(i).setSort(i+1);
                    }else{
                        examineDataRankVoPage.getRecords().get(i).setSort(((pageIndex-1)*pageSize)+i+1);
                    }
                }
            }
        }
        if("1".equals(excelType)){
            examineDataRankVoPage = examineDataBranchDao.queryExamineDataRankWD(page,startYear,startQuarter,endYear,endQuarter);
            if(CollectionUtil.isNotEmpty(examineDataRankVoPage.getRecords())){
                for (int i = 0; i < examineDataRankVoPage.getRecords().size(); i++) {
                    if(pageIndex==1){
                        examineDataRankVoPage.getRecords().get(i).setSort(i+1);
                    }else{
                        examineDataRankVoPage.getRecords().get(i).setSort(((pageIndex-1)*pageSize)+i+1);
                    }

                }
            }
        }
        return examineDataRankVoPage;
    }

    @Override
    public IPage<ExamineDeduVo> queryExamineDataDeduByBranch(PageQueryModel pageQueryModel) {
        Page<ExamineDeduVo> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC" , pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String startYear = (String) queryParam.get("startYear");//统计开始年份
        String startQuarter = (String) queryParam.get("startQuarter");//统计开始季度
        String endYear = (String) queryParam.get("endYear");//统计结束年份
        String endQuarter = (String) queryParam.get("endQuarter");//统计结束季度
        String branchOrgId = (String) queryParam.get("branchOrgId");//分支行机构号
        String outOrgId = (String) queryParam.get("outOrgId");//网点机构号
        return examineDataBranchDao.queryExamineDataDeduByBranch(page,startYear,startQuarter,endYear,endQuarter,branchOrgId,outOrgId);
    }

    @Override
    public List<ExamineAnalyzeVo> queryExamineDataAnalyzeByBranch(ExamineAnalyzeParmVo vo) {

        if(StringUtils.isBlank(vo.getStartYear()) || StringUtils.isBlank(vo.getEndYear())){
            throw new BizException("请选择开始年份和结束年份！");
        }
        if(StringUtils.isBlank(vo.getStartQuarter()) || StringUtils.isBlank(vo.getEndQuarter())){
            throw new BizException("请选择开始季度和结束季度！");
        }
        return examineDataBranchDao.queryExamineDataAnalyzeByBranch(vo);

    }
}
