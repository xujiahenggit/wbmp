package com.bank.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dao.ExamineDataAdminDao;
import com.bank.manage.dao.ExamineDataBranchDao;
import com.bank.manage.dao.ExamineDataTempAdminDao;
import com.bank.manage.dao.ExamineDataTempBranchDao;
import com.bank.manage.dos.ExamineDataAdminDO;
import com.bank.manage.dos.ExamineDataBranchDO;
import com.bank.manage.dos.ExamineDataTempAdminDO;
import com.bank.manage.service.ExamineDataAdminService;
import com.bank.manage.vo.ExamineAnalyzeParmVo;
import com.bank.manage.vo.ExamineAnalyzeVo;
import com.bank.manage.vo.ExamineDataRankVo;
import com.bank.manage.vo.ExamineDeduVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ExamineDataAdminServiceImpl implements ExamineDataAdminService {

    @Autowired
    private ExamineDataAdminDao examineDataAdminDao;

    @Autowired
    private ExamineDataTempAdminDao examineDataTempAdminDao;

    @Autowired
    private ExamineDataBranchDao examineDataBranchDao;

    @Autowired
    private ExamineDataTempBranchDao examineDataTempBranchDao;

    @Override
    public void saveAdminData(Map<String, Object> mapData) {
        try {
            for (Map.Entry<String, Object> entry : mapData.entrySet()) {
                //log.info("分行：key= " + entry.getKey() + " and value= " + entry.getValue());
                Map<String, Object> branchValue = (Map<String, Object>) entry.getValue();
                ExamineDataAdminDO examineDataAdminFH =  (ExamineDataAdminDO) branchValue.get("FH_DATA");
                examineDataAdminDao.insert(examineDataAdminFH);
                for (Map.Entry<String, Object> entry1 : branchValue.entrySet()) {
                    if("FH_DATA".equals(entry1.getKey())){
                        continue;
                    }
                    //log.info("网点：key= " + entry1.getKey() + " and value= " + entry1.getValue());
                    Map<String, Object> outOrgValue = (Map<String, Object>) entry1.getValue();
                    ExamineDataAdminDO examineDataAdminWD = (ExamineDataAdminDO) outOrgValue.get("WD_DATA");
                    examineDataAdminWD.setExamineId(examineDataAdminFH.getId());
                    examineDataAdminDao.insert(examineDataAdminWD);

                    List<ExamineDataTempAdminDO> examineDataTempAdminWDList = (List<ExamineDataTempAdminDO>) outOrgValue.get("WDTEMP_DATA");
                    for (ExamineDataTempAdminDO e:examineDataTempAdminWDList) {
                        e.setExamineId(examineDataAdminWD.getId());
                        examineDataTempAdminDao.insert(e);
                    }
                }
            }
        } catch (Exception e) {
            log.error("季度考核数据保存失败！{}",e.getMessage());
            throw new BizException("季度考核数据保存失败！");
        }
    }

    @Override
    public void delExcelData(String id, String dataType) {
        try {
            //查询季度考核数据（管理员表）
            QueryWrapper<ExamineDataAdminDO> queryExamineDataAdmin = new QueryWrapper<>();
            queryExamineDataAdmin.eq("EXCEL_ID",id);
            List<ExamineDataAdminDO> examineDataAdminDOS = examineDataAdminDao.selectList(queryExamineDataAdmin);
            List<Integer> examineDataAdminList = new ArrayList<>();
            if(CollectionUtil.isNotEmpty(examineDataAdminDOS)){
                for (ExamineDataAdminDO examineDataAdminDO:examineDataAdminDOS) {
                    if(StringUtils.isNotBlank(String.valueOf(examineDataAdminDO.getExamineId()))){
                        examineDataAdminList.add(examineDataAdminDO.getId());
                    }
                }
                examineDataAdminDao.delExamineDataAdminByExcelId(id);
                examineDataTempAdminDao.delExamineDataTempAdminByList(examineDataAdminList);//刪除从表
            }

            //查询季度考核数据（全行）
            QueryWrapper<ExamineDataBranchDO> queryExamineDataBranch = new QueryWrapper<>();
            queryExamineDataAdmin.eq("EXCEL_ID",id);
            List<ExamineDataBranchDO> examineDataBranchDOS = examineDataBranchDao.selectList(queryExamineDataBranch);
            List<Integer> examineDataBranchList = new ArrayList<>();
            if(CollectionUtil.isNotEmpty(examineDataBranchDOS)){
                for (ExamineDataBranchDO examineDataBranchDO:examineDataBranchDOS) {
                    if(StringUtils.isNotBlank(String.valueOf(examineDataBranchDO.getExamineId()))){
                        examineDataBranchList.add(examineDataBranchDO.getId());
                    }
                }
                examineDataBranchDao.delExamineDataBranchByExcelId(id);
                examineDataTempBranchDao.delExamineDataBranchByList(examineDataBranchList);
            }
        } catch (Exception e) {
            log.error("考核数据删除失败！{}",e.getMessage());
            throw new BizException("考核数据删除失败！");
        }
    }

    @Override
    public IPage<ExamineDataRankVo> queryExamineDataRankByAdmin(PageQueryModel pageQueryModel) {
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
        if("0".equals(excelType)){//分行
            examineDataRankVoPage = examineDataAdminDao.queryExamineDataRankByFH(page,startYear,startQuarter,endYear,endQuarter);
            if(CollectionUtil.isNotEmpty(examineDataRankVoPage.getRecords())){
                for (int i = 0; i < examineDataRankVoPage.getRecords().size(); i++) {
                    examineDataRankVoPage.getRecords().get(i).setSort(i+1);
                }
            }
        }
        if("1".equals(excelType)){
            examineDataRankVoPage = examineDataAdminDao.queryExamineDataRankByWD(page,startYear,startQuarter,endYear,endQuarter);
            if(CollectionUtil.isNotEmpty(examineDataRankVoPage.getRecords())){
                for (int i = 0; i < examineDataRankVoPage.getRecords().size(); i++) {
                    examineDataRankVoPage.getRecords().get(i).setSort(i+1);
                }
            }
        }
        return examineDataRankVoPage;
    }

    @Override
    public IPage<ExamineDeduVo> queryExamineDataDeduByAdmin(PageQueryModel pageQueryModel) {
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
        return examineDataAdminDao.queryExamineDataDeduByAdmin(page,startYear,startQuarter,endYear,endQuarter,branchOrgId,outOrgId);
    }

    @Override
    public List<ExamineAnalyzeVo> queryExamineDataAnalyzeByAdmin(ExamineAnalyzeParmVo vo) {
        if(StringUtils.isBlank(vo.getStartYear()) || StringUtils.isBlank(vo.getEndYear())){
            throw new BizException("请选择开始年份和结束年份！");
        }
        if(StringUtils.isBlank(vo.getStartQuarter()) || StringUtils.isBlank(vo.getEndQuarter())){
            throw new BizException("请选择开始季度和结束季度！");
        }
        return examineDataAdminDao.queryExamineDataAnalyzeByAdmin(vo);
    }
}
