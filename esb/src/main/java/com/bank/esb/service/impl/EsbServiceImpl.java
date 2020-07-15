package com.bank.esb.service.impl;

import com.bank.core.config.DataSource;
import com.bank.core.config.DynamicDataSourceSwitcher;
import com.bank.esb.dao.EsbDao;
import com.bank.esb.service.EsbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EsbServiceImpl implements EsbService {


    @Resource
    EsbDao esbDao;

    @Override
    @DataSource(DynamicDataSourceSwitcher.Slave)
    public List<Map<String, Object>> getEngineer(String engineerMId, String seachTxt) {
        return esbDao.getEngineer(engineerMId,seachTxt);
    }
}
