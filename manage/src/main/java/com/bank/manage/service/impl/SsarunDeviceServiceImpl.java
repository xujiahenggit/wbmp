package com.bank.manage.service.impl;

import com.bank.core.config.DataSource;
import com.bank.core.config.DynamicDataSourceSwitcher;
import com.bank.core.sysConst.WbmpConstFile;
import com.bank.manage.dao.SsarunDeviceDao;
import com.bank.manage.dto.DeviceTradeDto;
import com.bank.manage.dto.KioskDto;
import com.bank.manage.service.SsarunDeviceService;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SsarunDeviceServiceImpl implements SsarunDeviceService {

    @Resource
    SsarunDeviceDao ssarunDeviceDao;

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public IPage<SsarunDeviceVo> ssarunDeviceList(KioskDto kioskDto) {
        Page<SsarunDeviceVo> page = new Page<>(kioskDto.getPageIndex(), kioskDto.getPageSize());
        return ssarunDeviceDao.ssarunDeviceList(page,kioskDto);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<SsaViewTermStatusVo> termStatusList(List<String> list) {
        return ssarunDeviceDao.termStatusList(list);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public DeviceDetailsVo getDeviceDetailsById(String id) {
        return ssarunDeviceDao.getDeviceDetailsById(id);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public TerminalDetailsVo getTerminalDetailsById(String terminalCode) {
        return ssarunDeviceDao.getTerminalDetailsById(terminalCode);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public DeviceVendorVo getDeviceVendorByCode(String deviceVendor) {
        return ssarunDeviceDao.getDeviceVendorByCode(deviceVendor);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<SsarunDeviceModelVo> getDeviceModelList(String deviceId) {
        return ssarunDeviceDao.getDeviceModelList(deviceId);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<ReaderStatusList> getReaderStatusListById(String terminalCode) {
        return ssarunDeviceDao.getReaderStatusListById(terminalCode);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<PrinterListVo> getPrinterListById(String terminalCode) {
        return ssarunDeviceDao.getPrinterListById(terminalCode);
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<DeviceTradeTrendVo> getDeviceTradeList(DeviceTradeDto deviceTradeDto) {
        if(WbmpConstFile.DATE_TYPE_MONTH.equals(deviceTradeDto.getQueryType())){
            //按月查询
            return ssarunDeviceDao.getDeviceTradeMouthList(deviceTradeDto);

        }else if(WbmpConstFile.DATE_TYPE_YEAR.equals(deviceTradeDto.getQueryType())){
            //按年查询
            return ssarunDeviceDao.getDeviceTradeYearsList(deviceTradeDto);
        }else{
            return  null;
        }

    }

}
