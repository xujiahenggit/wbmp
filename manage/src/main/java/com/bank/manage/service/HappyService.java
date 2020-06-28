package com.bank.manage.service;

import com.bank.manage.dto.DeductDTO;
import com.bank.manage.vo.HappyParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface HappyService {

    boolean hasAdminPermission(String userId);

    List<Map<String, Integer>> HeadStatus(HappyParam param);

    Map<String,Object> checkStatus(HappyParam param);

    List<DeductDTO> deductStatus(HappyParam param);

    List<Map> starStatus(HappyParam param);

    List<Map> serviceLevelStatus(HappyParam param);
}
