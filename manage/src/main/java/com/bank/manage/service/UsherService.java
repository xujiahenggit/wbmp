package com.bank.manage.service;

import java.util.List;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.UsherDO;
import com.bank.manage.dos.UsherPopulationDO;
import com.bank.manage.dto.UsherDTO;
import com.bank.manage.dto.UsherPopulationDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *
 * ClassName: UsherService
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/21 14:46:24
 */
public interface UsherService {

    /**
     * 新增
     * @param usherDTO
     * @return
     */
    Integer insert(UsherDTO usherDTO, String currentUser);

    /**
     * 批量新增
     * @param sherDOList
     * @return
     */
    Integer insertBatch(List<UsherDO> usherDOList);

    /**
     * 新增引导员人员人数控制
     * @param usherPopulationDO
     * @return
     */
    Integer insertLimit(UsherPopulationDO usherPopulationDO);

    /**
     * 保存引导员人数控制
     * @param usherPopulationDOList
     * @return
     */
    Integer saveLimit(List<UsherPopulationDTO> usherPopulationDTOList, String currentUser);

    /**
     * 批量设置引导员人数控制
     * @param limit 人数控制
     * @param currentUser 当前登录用户
     */
    Integer batchSetLimit(Integer limit, String currentUser);

    /**
     * 批量新增人数控制
     * @param usherPopulationDOList
     * @return
     */
    Integer insertBatchLimit(List<UsherPopulationDO> usherPopulationDOList);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer deleteById(Integer id, String currentUser);

    /**
     * 更新
     * @param usherDTO
     * @return
     */
    Integer updateById(UsherDTO usherDTO, String currentUser);

    /**
     * 分页查询
     * @param pageQueryModel
     * @return
     */
    IPage<UsherDTO> selectPageExt(PageQueryModel pageQueryModel, String orgId);

    /**
     *  查询引导员人数控制
     * @return
     */
    List<UsherPopulationDO> selectUsherPopulation(String orgName);

    /**
     * 用手机号码 查询 引导员信息
     * @param userPhone 手机号码
     * @return
     */
    UsherDO selectUsherByPhone(String userPhone);

    /**
     * 查询所有可用的 引导员
     * @return
     */
    List<UsherDO> SelectUseFullUsher();

    List<UsherDO> queryUsherByPhone(String phone);
}
