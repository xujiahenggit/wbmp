package com.bank.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bank.manage.dos.MaterialDO;
import com.bank.manage.dto.MaterialDTO;
import com.bank.manage.vo.MaterialVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface MaterialDao extends BaseMapper<MaterialDO> {

    IPage<MaterialVo> selectPageListByCatalogType(Page<MaterialVo> page, @Param("catalogId") String catalogId, @Param("createdUser") String createdUser, @Param("orgId") String orgId, @Param("deviceType") String deviceType, @Param("forcePlay") String forcePlay, @Param("materialName") String materialName);

    IPage<MaterialDTO> queryListForPlay(Page<MaterialDO> page, @Param("materialName") String materialName, @Param("orgId") String orgId, @Param("deviceType") String deviceType, @Param("catalogId") String catalogId);

    List<MaterialVo> queryListByCatalogId(@Param("catalogId") String catalogId);
}
