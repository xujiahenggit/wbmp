package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.PersonnelDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PersonnelService extends IService<PersonnelDO> {
    IPage<PersonnelDO> queryList(PageQueryModel pageQueryModel);

    boolean save(PersonnelDO personnelDO);

    boolean delete(List<String> list);

    boolean update(PersonnelDO personnelDO);

    PersonnelDO getOne(String strOperatorNum);
}
