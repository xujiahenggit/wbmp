package com.bank.manage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bank.manage.dos.UsherDO;
import com.bank.manage.dto.UsherDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 *
 * ClassName: UsherDao
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/22 15:36:06
 */
public interface UsherDao extends BaseMapper<UsherDO> {

    Integer insertBatch(List<UsherDO> list);

    IPage<UsherDTO> selectPageExt(Page<UsherDTO> page, @Param("queryParam") Map<String, Object> queryParam);
}
