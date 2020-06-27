package com.bank.manage.service.impl;

import com.bank.manage.dao.DictionaryItemDao;
import com.bank.manage.dos.DictionaryItemDO;
import com.bank.manage.service.DictionaryItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/9 22:46
 */
@Service
public class DictionaryItemServiceImpl extends ServiceImpl<DictionaryItemDao, DictionaryItemDO> implements DictionaryItemService {

    @Resource
    private DictionaryItemDao dictionaryItemDao;
    /**
     * 批量删除 字典项目
     * @param ids 字典ID 列表
     */
    @Override
    public void DeleteItemByDictIds(List<Integer> ids) {
        dictionaryItemDao.DeleteItemByDictIds(ids);
    }

    /**
     * 单条删除 字典项目
     *
     * @param dictId 字典ID
     */
    @Override
    public void deleteByDictId(Integer dictId) {
        dictionaryItemDao.deleteByDictId(dictId);
    }
}
