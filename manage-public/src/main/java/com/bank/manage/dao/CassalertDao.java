package com.bank.manage.dao;

import com.bank.manage.dos.CassalertDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 预警表
 *
 * @author
 * @date 2020-7-7
 */
public interface CassalertDao extends BaseMapper<CassalertDO> {
    List<CassalertDO> queryList(IPage page, @Param("model") String subBranchNum);

//    List<CassalertDO> queryList(IPage page, @Param("model") CassalertDO cassalertDO);

    CassalertDO selectBank(@Param("model") String strBankNum);

    CassalertDO selectBranch(@Param("model") String strBranchNum);

    boolean updateBank(@Param("model") CassalertDO cassalertDO);

    boolean updateBranch(@Param("model") CassalertDO cassalertDO);

    boolean insertBank(@Param("model") CassalertDO cassalertDO);

    boolean insertBranch(@Param("model") CassalertDO cassalertDO);
}
