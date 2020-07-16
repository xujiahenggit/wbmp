package com.bank.esb.service.impl;

import com.bank.core.config.DataSource;
import com.bank.core.config.DynamicDataSourceSwitcher;
import com.bank.esb.dao.EsbDao;
import com.bank.esb.dto.CSInfoDto;
import com.bank.esb.dto.EngineerDto;
import com.bank.esb.dto.ManagerDto;
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
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<EngineerDto> getEngineer(String engineerMId, String seachTxt) {
        return esbDao.getEngineer(engineerMId,seachTxt);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<ManagerDto> getManager(Object deviceId) {
        return esbDao.getCSMaster(deviceId.toString());
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public Map<String, Object> getDeviceInfo(String deviceId) {
        return esbDao.getDeviceInfo(deviceId);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<CSInfoDto> getCSInfo() {
        return esbDao.getCSInfo();
    }

}
