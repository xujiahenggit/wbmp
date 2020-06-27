package com.bank.manage.service;

import com.bank.manage.dos.DictionaryItemDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/9 22:45
 */
public interface DictionaryItemService extends IService<DictionaryItemDO> {
    /**
     * 批量删除 字典项目
     * @param ids 字典ID 列表
     */
    void DeleteItemByDictIds(List<Integer> ids);

    /**
     * 单条删除 字典项目
     * @param dictId 字典ID
     */
    void deleteByDictId(Integer dictId);


}
