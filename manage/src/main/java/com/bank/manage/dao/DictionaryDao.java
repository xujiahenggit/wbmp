package com.bank.manage.dao;

import com.bank.manage.dos.DictionaryDO;
import com.bank.manage.dto.DictionaryDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Andy
 * @Date: 2020/5/9 17:31
 */
public interface DictionaryDao extends BaseMapper<DictionaryDO> {
    /**
     * 获取字典详细信息
     * @param dictId 字典编号
     * @return
     */
    DictionaryDto getDictinfo(@Param(value = "dictId") Integer dictId);
}
