package com.bank.manage.dao;

import com.bank.manage.dos.ActivitieSalonImageDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivitieSalonImageDao extends BaseMapper<ActivitieSalonImageDO> {
    void removeActivitieSalonImageByIds(@Param("ids") List<Integer> ids);
}
