package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.TaccountingSubjectDO;
import com.bank.manage.dto.TaccountingSubjectDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TaccountingSubjectService extends IService<TaccountingSubjectDO> {

    /**
     * 插入对账科目维护信息
     * @param taccountingSubjectDTO
     * @return
     */
    Boolean save(TaccountingSubjectDTO taccountingSubjectDTO);

    /**
     * 删除对账科目维护信息
     * @param list
     * @return
     */
    Boolean delete(List<String> list);

    /**
     * 查询对账科目维护信息
     * @param pageQueryModel
     * @return
     */
    IPage<TaccountingSubjectDO> queryList(PageQueryModel pageQueryModel);

    /**
     * 修改对账科目维护信息
     * @param taccountingSubjectDTO
     * @return
     */
    Boolean updateTaccountingSubject(TaccountingSubjectDTO taccountingSubjectDTO);
}
