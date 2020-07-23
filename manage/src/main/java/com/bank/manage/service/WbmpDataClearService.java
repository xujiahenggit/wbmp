package com.bank.manage.service;

import com.bank.manage.dto.DeductDTO;
import com.bank.manage.vo.HappyParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 运营看板数据清理Service
 */
@Service
public interface WbmpDataClearService {

    /**
     * 步骤一：备份
     * 备份wbmp_abs_transinfo上日的数据 到 wbmp_abs_transinfo_h表中
     * @param date【上一日的日期】
     * @return
     */
    Boolean bakWbmpAbsTransinfo(@Param(value = "date") String date);

    /**
     * 步骤二：删除原表数据
     * 删除wbmp_abs_transinfo表中上一日的数据
     * @param date
     * @return
     */
    Boolean deleteWbmpAbsTransinfo(@Param(value = "date") String date);

    /**
     * 步骤三：删除历史表数据
     * 删除wbmp_abs_transinfo_h表中小于前三天的数据
     * @param date
     * @return
     */
    Boolean deleteWbmpAbsTransinfoH(@Param(value = "date") String date);

}
