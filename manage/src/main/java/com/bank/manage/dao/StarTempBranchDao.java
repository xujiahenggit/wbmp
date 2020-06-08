package com.bank.manage.dao;

import com.bank.manage.dos.StarDataTempBranchDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface StarTempBranchDao extends BaseMapper<StarDataTempBranchDO> {
    IPage<StarDataTempBranchDO> queryExampleBranch(Page<StarDataTempBranchDO> page,@Param("startYear") String startYear,
          @Param("assessStart") String assessStart, @Param("branchOrgId") String branchOrgId, @Param("outOrgId") String outOrgId);
}
