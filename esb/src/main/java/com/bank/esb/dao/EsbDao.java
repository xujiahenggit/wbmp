package com.bank.esb.dao;

import com.bank.esb.dto.EngineerDto;
import com.bank.esb.dto.ManagerDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EsbDao {

    List<EngineerDto> getEngineer(@Param("id") String engineerMId, @Param("seachTxt") String seachTxt);

    String getCSName(@Param("id") String termid);

    List<ManagerDto> getCSMaster(@Param("id")String deviceid);

    Map<String, Object> getDeviceInfo(@Param("id")String deviceId);
}
