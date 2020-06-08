package com.bank.manage.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: Andy
 * @Date: 2020/4/9 9:47
 */
public interface HomeDao {

    Map<String, Object> getHomePanleData(@Param("day") int day);

    List<Map<String, Object>> getNewMaterial(Integer day, String orgId);
}
