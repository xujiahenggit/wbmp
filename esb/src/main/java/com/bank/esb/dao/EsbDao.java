package com.bank.esb.dao;

import com.bank.esb.dto.EngineerDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EsbDao {

    List<EngineerDto> getEngineer(@Param("id") String engineerMId, @Param("seachTxt") String seachTxt);
}
