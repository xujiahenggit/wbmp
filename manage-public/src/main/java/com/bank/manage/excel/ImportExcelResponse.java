package com.bank.manage.excel;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 *
 * ClassName: ImportExcelResponse
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/24 16:18:38
 */
@Data
public class ImportExcelResponse {

    /**
     * 导入状态 成功或失败
     */
    private boolean status;

    /**
     * 错误列表
     */
    private List<Map<String, Object>> errorRows;

}
