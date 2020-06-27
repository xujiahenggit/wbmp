package com.bank.manage.service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Andy
 * @Date: 2020/4/9 9:47
 */
public interface HomeService {

    Map<String, Object> getHomePanleData(int day);

    List<Map<String, Object>> getNewMaterial(Integer day, String orgid);
}
