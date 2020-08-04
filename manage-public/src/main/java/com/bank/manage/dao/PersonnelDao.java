package com.bank.manage.dao;

import com.bank.manage.dos.PersonnelDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 短信人员
 *
 * @author
 * @date 2020-7-7
 */
public interface PersonnelDao extends BaseMapper<PersonnelDO> {
    List<PersonnelDO> queryList(IPage page, @Param("model") PersonnelDO personnelDO);

    PersonnelDO getOne(@Param("model") String strOperatorNum);
}
