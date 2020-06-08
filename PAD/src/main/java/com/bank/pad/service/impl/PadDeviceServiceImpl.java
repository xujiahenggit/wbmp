package com.bank.pad.service.impl;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.DeviceDO;
import com.bank.manage.dto.DeviceDTO;
import com.bank.pad.dao.PadDeviceDao;
import com.bank.pad.service.PadDeviceService;
import com.bank.user.dao.UserDao;
import com.bank.user.dos.UserDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PadDeviceServiceImpl implements PadDeviceService {

    @Autowired
    private PadDeviceDao padDeviceDao;

    @Autowired
    private UserDao userDao;

    @Override
    public IPage<DeviceDTO> queryList(PageQueryModel pageQueryModel) {
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String userId = (String) queryParam.get("userId");
        UserDO userDO = userDao.selectById(userId);
        String orgId = userDO.getOrgId();

        Page<DeviceDTO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());
        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        //条件查询
        String terminalNum = (String) queryParam.get("terminalNum");
        String deviceName = (String) queryParam.get("deviceName");
        String deviceType = (String) queryParam.get("deviceType");
        IPage<DeviceDTO> deviceList = padDeviceDao.queryDeviceByOrgId(page,orgId,terminalNum,deviceName,deviceType);
        return deviceList;
    }
}
