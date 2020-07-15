package com.bank.esb.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EsbDao {

    @Select("select * from vendorpersonnel")
    List<Map<String, Object>> getEngineer(String engineerMId, String seachTxt);
}
