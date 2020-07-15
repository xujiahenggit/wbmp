package com.bank.esb.service;

import java.util.List;
import java.util.Map;

public interface EsbService {

    List<Map<String,Object>> getEngineer(String engineerMId, String seachTxt);
}
