package com.bank.manage.service.impl;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dao.DeviceErrorLogDao;
import com.bank.manage.dos.DeviceErrorLogDO;
import com.bank.manage.dto.DeviceErrorLogDTO;
import com.bank.manage.service.DeviceErrorLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@Slf4j
public class DeviceErrorLogServiceImpl implements DeviceErrorLogService {

    @Autowired
    private DeviceErrorLogDao deviceErrorLogDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveErrorLog(DeviceErrorLogDTO deviceErrorLogDTO) {
        Boolean flag = null;
        try {
            DeviceErrorLogDO deviceErrorLogDO = DeviceErrorLogDO.builder()
                    .terminalNum(deviceErrorLogDTO.getTerminalNum())
                    .mac(deviceErrorLogDTO.getMac())
                    .messageLog(deviceErrorLogDTO.getMessageLog())
                    .createTime(LocalDateTime.now()).build();
            deviceErrorLogDao.insert(deviceErrorLogDO);
            flag = true;
        } catch (Exception e) {
            flag = false;
            log.error("设备错误信息保存失败："+e.getMessage());
        }
        return flag;
    }

    @Override
    public IPage<DeviceErrorLogDO> queryDeviceErrorLog(PageQueryModel pageQueryModel) {
        Page<DeviceErrorLogDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        //条件查询
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String terminalNum = (String) queryParam.get("terminalNum");
        String startTime = (String) queryParam.get("startTime");
        String endTime = (String) queryParam.get("endTime");

        QueryWrapper<DeviceErrorLogDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(terminalNum), "TERMINAL_NUM", terminalNum);
        if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
            queryWrapper.between("CREATE_TIME",startTime,endTime);
        }

        return deviceErrorLogDao.selectPage(page,queryWrapper);
    }
}
