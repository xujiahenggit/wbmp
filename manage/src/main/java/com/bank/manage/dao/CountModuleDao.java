package com.bank.manage.dao;

import com.bank.manage.dos.CountModuleDO;
import com.bank.manage.dto.CountModuleDTO;
import com.bank.manage.vo.CountModuleDetailVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *  统计模块主表 Mapper 接口
 */
public interface CountModuleDao extends BaseMapper<CountModuleDO> {


    IPage<CountModuleDTO> queryCountModulePage(Page<CountModuleDTO> page);

    List<CountModuleDetailVo> getCountModule(@Param("moduleYear") String moduleYear);

    List<Map<String, Object>> queryCountModule(@Param("year") String year);

    Map<String, Object> queryCountModuleByCount();
}
