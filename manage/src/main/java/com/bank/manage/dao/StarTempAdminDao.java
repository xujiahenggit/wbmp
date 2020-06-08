package com.bank.manage.dao;

import com.bank.manage.dos.StarDataAdminDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface StarTempAdminDao extends BaseMapper<StarDataAdminDO> {
    IPage<StarDataAdminDO> selectPageByQueryParm(Page<StarDataAdminDO> page, @Param("startYear") String startYear,
          @Param("assessStart") String assessStart, @Param("branchOrgId") String branchOrgId,@Param("outOrgId") String outOrgId);
}
