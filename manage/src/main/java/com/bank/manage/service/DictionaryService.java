package com.bank.manage.service;

import com.bank.manage.dos.DictionaryDO;
import com.bank.manage.dos.DictionaryItemDO;
import com.bank.manage.dto.DictionaryDto;
import com.bank.manage.vo.DictQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/9 17:30
 */
public interface DictionaryService extends IService<DictionaryDO> {

    /**
     * 获取字典列表
     * @param dictQueryVo 查询参数
     * @return
     */
    IPage<DictionaryDO> getPage(DictQueryVo dictQueryVo);

    /**
     * 批量删除数据字典
     * @param ids 字典ID 列表
     * @return
     */
    boolean deleteDict(List<Integer> ids);

    /**
     * 获取字典详细信息
     * @param dictId 字典ID
     * @return
     */
    DictionaryDto getDictinfo(Integer dictId);

    /**
     * 修改字典信息
     * @param dictionaryDto 字典信息内容
     * @return
     */
    boolean updateDictInfo(DictionaryDto dictionaryDto);

    /**
     * 新增 字典信息
     * @param dictionaryDto 字典信息
     * @return
     */
    boolean saveDictInfo(DictionaryDto dictionaryDto);

    /**
     * 通过名称 获取字典列表项
     * @param name
     * @return
     */
    List<DictionaryItemDO> getDictinfoByName(String name);
}
