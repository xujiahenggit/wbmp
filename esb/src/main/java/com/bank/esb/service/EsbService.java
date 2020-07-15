package com.bank.esb.service;

import com.bank.esb.dto.EngineerDto;
import com.bank.esb.dto.ManagerDto;

import java.util.List;
import java.util.Map;

public interface EsbService {

    List<EngineerDto> getEngineer(String engineerMId, String seachTxt);

    List<ManagerDto> getManager(Object deviceId);

    Map<String, Object> getDeviceInfo(String toString);
}
