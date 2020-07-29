package com.bank.esb.service.impl;

import com.bank.core.config.DataSource;
import com.bank.core.config.DynamicDataSourceSwitcher;
import com.bank.esb.dao.EsbDao;
import com.bank.esb.dos.DatBranchDO;
import com.bank.esb.dos.DatSelfsvcbankDO;
import com.bank.esb.dos.DatSubbranchDO;
import com.bank.esb.dto.CSInfoDto;
import com.bank.esb.dto.EngineerDto;
import com.bank.esb.dto.ManagerDto;
import com.bank.esb.dto.OrderDto;
import com.bank.esb.service.EsbService;
import com.bank.esb.vo.EngineerVo;
import com.bank.esb.vo.InspectionSheetVo;
import com.bank.esb.vo.OrderNumVo;
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
    public List<EngineerDto> getEngineer(EngineerVo engineerVo) {
        return esbDao.getEngineer(engineerVo);
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

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<OrderDto> getEsbErrOrder(OrderNumVo orderNumVo) {
        return esbDao.getEsbErrOrder(orderNumVo);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public Map<String, String> getEngineerInfo(String engineerId) {
        return esbDao.getEngineerInfo(engineerId);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<DatBranchDO> getBranch(String orgId) {
        return esbDao.getBranch(orgId);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<DatSubbranchDO> getSubBranch(String orgId) {
        return esbDao.getSubBranch(orgId);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<DatSubbranchDO> getSubBranch() {
        return esbDao.getAllSubBranch();
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<DatSelfsvcbankDO> getSelfBranch(String orgId) {
        return esbDao.getSelfBranch(orgId);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public Map<String, Object> getXjdInfo(String deviceNo) {
        return esbDao.getXjdInfo(deviceNo);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<Map<String, String>> getXjd(InspectionSheetVo inspectionSheetVo) {
        return esbDao.getXjd(inspectionSheetVo);
    }

}
