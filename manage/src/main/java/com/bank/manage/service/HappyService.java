package com.bank.manage.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bank.manage.dto.DeductDTO;
import com.bank.manage.vo.HappyParam;

@Service
public interface HappyService {

    boolean hasAdminPermission(String userId);

    List<Map<String, Object>> HeadStatus(HappyParam param);

    Map<String, Object> checkStatus(HappyParam param);

    List<DeductDTO> deductStatus(HappyParam param);

    List<Map> starStatus(HappyParam param);

    List<Map> serviceLevelStatus(HappyParam param);
}
