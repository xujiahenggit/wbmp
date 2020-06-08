package com.bank.manage.dao;

import com.bank.manage.dos.DictionaryItemDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/9 17:59
 */
public interface DictionaryItemDao extends BaseMapper<DictionaryItemDO> {
    /**
     * 批量删除 字典项目
     * @param ids 字典编号
     */
    void DeleteItemByDictIds(@Param("listDictIds") List<Integer> ids);

    /**
     * 删除
     * @param dictId 字典编号
     */
    void deleteByDictId(@Param(value = "dictId") Integer dictId);
}
