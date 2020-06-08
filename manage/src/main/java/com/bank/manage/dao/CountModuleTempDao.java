package com.bank.manage.dao;

import com.bank.manage.dos.CountModuleTempDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 统计模块从表  Mapper 接口
 *
 * @author zhaozhongyuan
 * @since 2020-05-22
 */
public interface CountModuleTempDao extends BaseMapper<CountModuleTempDO> {

    /**
     * 删除从表数据
     * @param ids
     */
    void delCountModuleTempByIds(@Param("ids") List<Integer> ids);
}
