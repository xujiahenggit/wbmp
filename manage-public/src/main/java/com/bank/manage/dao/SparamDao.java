package com.bank.manage.dao;

import com.bank.manage.dos.SparamDO;
import com.bank.manage.dto.SparamDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 参数表
 *
 * @author
 * @date 2020-7-7
 */
public interface SparamDao extends BaseMapper<SparamDO> {

    List<SparamDTO> selectPageList(IPage page, @Param("model") SparamDTO sparamDTO);

    void save(@Param("model") SparamDTO sparamDTO);

    void update(@Param("model") SparamDTO sparamDTO);

    void updateList(@Param("list") List<Integer> list, @Param("value") String value);

}
