package com.bank.manage.service;

import com.bank.manage.dos.BranchDO;
import com.bank.manage.dto.SsbRankingDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface BranchService extends IService<BranchDO> {

    /**
     * 根据用户登录机构号 查询原监控 分支行机构号
     * @param orgcode
     * @return
     */
    String selectByOrgcode(String orgcode);

    /**
     * 根据用户登录机构号 查询原监控 分行机构号
     * @param orgcode
     * @return
     */
    String selectBranchByOrgcode(String orgcode);
}
