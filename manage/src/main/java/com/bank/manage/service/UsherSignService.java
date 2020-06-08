package com.bank.manage.service;

import com.bank.manage.dto.UsherSignDTO;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Map;

public interface UsherSignService {

    /**
     * 上班签
     * @param usherSignDTO
     */
    Boolean onDutySign(UsherSignDTO usherSignDTO);

    /**
     * 下班签
     * @param usherSignDTO
     */
    Boolean offDutySign(UsherSignDTO usherSignDTO);

    /**
     * 查询考勤详细信息
     * @param usherId
     * @param month
     * @return
     */
    Map<String, Object> queryInformation(String usherId, String month);
}
