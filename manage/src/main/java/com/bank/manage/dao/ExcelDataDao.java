package com.bank.manage.dao;

import com.bank.manage.dos.ExcelDataDO;
import com.bank.manage.dto.ExcelDataDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface ExcelDataDao extends BaseMapper<ExcelDataDO> {
    IPage<ExcelDataDTO> queryExcelData(Page<ExcelDataDTO> page, @Param("dataType") String dataType, @Param("excelName") String excelName);
}
