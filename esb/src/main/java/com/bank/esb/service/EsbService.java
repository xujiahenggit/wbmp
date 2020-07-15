package com.bank.esb.service;

import com.bank.esb.dto.EngineerDto;

import java.util.List;

public interface EsbService {

    List<EngineerDto> getEngineer(String engineerMId, String seachTxt);
}
