package com.bank.manage.service.impl;

import com.alibaba.excel.EasyExcel;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.StringSplitUtil;
import com.bank.manage.dao.ExcelDataDao;
import com.bank.manage.dos.ExcelDataDO;
import com.bank.manage.dto.ExcelDataDTO;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.listener.*;
import com.bank.manage.service.*;
import com.bank.manage.vo.ExamineDataVo;
import com.bank.manage.vo.ExampleBranchVo;
import com.bank.manage.vo.StarTempVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class ExcelDataServiceImpl implements ExcelDataService {

    @Resource
    private ConfigFileReader configFileReader;

    @Autowired
    private ExcelDataDao excelDataDao;

    @Autowired
    private ExamineDataAdminService examineDataAdminService;

    @Autowired
    private ExamineDataBranchService examineDataBranchService;

    @Autowired
    private ExampleBranchAdminService exampleBranchAdminService;

    @Autowired
    private ExampleBranchService exampleBranchService;

    @Autowired
    private StarTempAdminService starTempAdminService;

    @Autowired
    private StarTempBranchService starTempBranchService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importExamineTempData(ExcelDataDTO excelDataDTO,TokenUserInfo tokenUserInfo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(new Date());
        String examineDataPath = StringSplitUtil.splitMaterialPath(excelDataDTO.getExcelPath(),configFileReader.getHTTP_PATH());
        File file = new File(examineDataPath);
        if (file == null) {
            throw new BizException("请上传季度考核Excel文件进行数据导入操作！");
        }
        try {
            ExcelDataDO excelDataDO = ExcelDataDO.builder()
                    .dataType(excelDataDTO.getDataType())
                    .excelName(excelDataDTO.getExcelName())
                    .excelDate(year)
                    .excelPath(examineDataPath)
                    .excelQuarter(excelDataDTO.getExcelQuarter())
                    .excelRelease(excelDataDTO.getExcelRelease())
                    .createTime(LocalDateTime.now())
                    .createUser(tokenUserInfo.getUserId())
                    .excelSize(excelDataDTO.getExcelSize()).build();
            excelDataDao.insert(excelDataDO);

            ImportExcelResponse response = new ImportExcelResponse();
            response.setStatus(true);
            response.setErrorRows(new ArrayList<>());

            if("1".equals(excelDataDTO.getExcelRelease())){//非全行发布
                EasyExcel.read(new FileInputStream(file), ExamineDataVo.class,
                        new ExamineDataAdminListener(excelDataDO.getId(),year,excelDataDTO.getExcelQuarter(),this.examineDataAdminService,response)).sheet().headRowNumber(1).doRead();
            }
            if("0".equals(excelDataDTO.getExcelRelease())){//全行发布
                EasyExcel.read(new FileInputStream(file), ExamineDataVo.class,
                        new ExamineDataBranchListener(excelDataDO.getId(),year,excelDataDTO.getExcelQuarter(),this.examineDataBranchService,response)).sheet().headRowNumber(1).doRead();
            }
            return true;
        } catch (Exception e) {
            log.error("季度考核Excel文件数据导入失败！{}",e.getMessage());
            throw new BizException("季度考核Excel文件数据导入失败！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importExampleBranch(ExcelDataDTO excelDataDTO, TokenUserInfo tokenUserInfo) {
        String examineDataPath = StringSplitUtil.splitMaterialPath(excelDataDTO.getExcelPath(),configFileReader.getHTTP_PATH());
        File file = new File(examineDataPath);
        if (file == null) {
            throw new BizException("全国标杆网点统计数据Excel文件进行数据导入操作！");
        }
        try {
            ExcelDataDO excelDataDO = ExcelDataDO.builder()
                    .dataType(excelDataDTO.getDataType())
                    .excelName(excelDataDTO.getExcelName())
                    .excelDate(excelDataDTO.getExcelDate())
                    .excelPath(examineDataPath)
                    .excelRelease(excelDataDTO.getExcelRelease())
                    .createTime(LocalDateTime.now())
                    .createUser(tokenUserInfo.getUserId())
                    .excelSize(excelDataDTO.getExcelSize()).build();
            excelDataDao.insert(excelDataDO);

            ImportExcelResponse response = new ImportExcelResponse();
            response.setStatus(true);
            response.setErrorRows(new ArrayList<>());

            if("1".equals(excelDataDTO.getExcelRelease())){//非全行发布
                EasyExcel.read(new FileInputStream(file), ExampleBranchVo.class,
                        new ExampleBranchAdminListener(excelDataDO.getId(),excelDataDTO.getExcelDate(),response,this.exampleBranchAdminService)).sheet().headRowNumber(2).doRead();
            }

            if("0".equals(excelDataDTO.getExcelRelease())){//全行发布
                EasyExcel.read(new FileInputStream(file), ExampleBranchVo.class,
                        new ExampleBranchListener(excelDataDO.getId(),excelDataDTO.getExcelDate(),response,this.exampleBranchService)).sheet().headRowNumber(2).doRead();
            }
            return true;
        } catch (Exception e) {
            log.error("全国标杆网点统计数据Excel文件数据导入失败！{}",e.getMessage());
            throw new BizException("全国标杆网点统计数据Excel文件数据导入失败！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importStarData(ExcelDataDTO excelDataDTO,TokenUserInfo tokenUserInfo) {
        String examineDataPath = StringSplitUtil.splitMaterialPath(excelDataDTO.getExcelPath(),configFileReader.getHTTP_PATH());
        File file = new File(examineDataPath);
        if (file == null) {
            throw new BizException("请上传星级标准化网点数据Excel文件进行数据导入操作！");
        }
        try {
            ExcelDataDO excelDataDO = ExcelDataDO.builder()
                    .dataType(excelDataDTO.getDataType())
                    .excelName(excelDataDTO.getExcelName())
                    .excelDate(excelDataDTO.getExcelDate())
                    .excelPath(examineDataPath)
                    .excelRelease(excelDataDTO.getExcelRelease())
                    .createTime(LocalDateTime.now())
                    .createUser(tokenUserInfo.getUserId())
                    .excelSize(excelDataDTO.getExcelSize()).build();
            excelDataDao.insert(excelDataDO);

            ImportExcelResponse response = new ImportExcelResponse();
            response.setStatus(true);
            response.setErrorRows(new ArrayList<>());
            if("1".equals(excelDataDTO.getExcelRelease())){//非全行发布
                EasyExcel.read(new FileInputStream(file), StarTempVo.class,
                        new StarTempAdminListener(excelDataDO.getId(),excelDataDTO.getExcelDate(),response,this.starTempAdminService)).sheet().headRowNumber(2).doRead();
            }

            if("0".equals(excelDataDTO.getExcelRelease())){//全行发布
                EasyExcel.read(new FileInputStream(file), StarTempVo.class,
                        new StarTempBranchListener(excelDataDO.getId(),excelDataDTO.getExcelDate(),response,this.starTempBranchService)).sheet().headRowNumber(2).doRead();
            }
            return true;
        } catch (Exception e) {
            log.error("星级标准化网点数据保存失败！{}",e.getMessage());
            throw new BizException("星级标准化网点数据保存失败！");
        }
    }

    @Override
    public IPage<ExcelDataDTO> queryExcelData(PageQueryModel pageQueryModel) {
        Page<ExcelDataDTO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC" , pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            } else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String dataType = (String) queryParam.get("dataType");
        String excelName = (String) queryParam.get("excelName");

        IPage<ExcelDataDTO> excelDataList = excelDataDao.queryExcelData(page,dataType,excelName);
        return excelDataList;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delExcelData(String id, String dataType) {
        try {
            if("0".equals(dataType)){//考核原始数据
                examineDataAdminService.delExcelData(id,dataType);
            }
            if("1".equals(dataType)){//全国标杆网点统计数据
                exampleBranchAdminService.delExampleData(id,dataType);
            }
            if("2".equals(dataType)){//行内星级标准化网点统计数据
                starTempAdminService.delStartData(id,dataType);
            }
            excelDataDao.deleteById(id);

            return true;
        } catch (Exception e) {
            log.error("报表数据删除失败！{}",e.getMessage());
            throw new BizException("报表数据删除失败！");
        }
    }
}