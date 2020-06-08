package com.bank.manage.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.enums.ConstantEnum;
import com.bank.manage.dao.DeviceDao;
import com.bank.manage.dao.DeviceGroupDao;
import com.bank.manage.dao.GroupDao;
import com.bank.manage.dao.StatusLogDao;
import com.bank.manage.dos.DeviceDO;
import com.bank.manage.dos.DeviceGroupDO;
import com.bank.manage.dos.GroupDO;
import com.bank.manage.dos.StatusLogDO;
import com.bank.manage.dto.DeviceDTO;
import com.bank.manage.service.DeviceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 设备信息
 *
 * @author
 * @date 2020-4-1
 */
@Service
@Slf4j
public class DeviceServiceImpl extends ServiceImpl<DeviceDao, DeviceDO> implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private StatusLogDao statusLogDao;

    @Autowired
    private DeviceGroupDao deviceGroupDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(DeviceDTO deviceDTO, Integer groupId, TokenUserInfo tokenUserInfo) {
        try {
            QueryWrapper<DeviceDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(StringUtils.isNotBlank(deviceDTO.getMac()), "MAC", deviceDTO.getMac().trim().toUpperCase());
            Integer i = this.deviceDao.selectCount(queryWrapper);
            if (i < 1) {
                DeviceDO deviceDO = DeviceDO.builder()
                        .terminalNum("BCS" + System.currentTimeMillis())
                        .orgId(deviceDTO.getOrgId())
                        .orgName(deviceDTO.getOrgName())
                        .deviceType(deviceDTO.getDeviceType())
                        .deviceName(deviceDTO.getDeviceName())
                        .deviceSystem(deviceDTO.getDeviceSystem())
                        .deviceBrand(deviceDTO.getDeviceBrand())
                        .deviceModel(deviceDTO.getDeviceModel())
                        .deviceSource(deviceDTO.getDeviceSource())
                        .upplierId(deviceDTO.getUpplierId())
                        .addressAbbr(deviceDTO.getAddressAbbr())
                        .installWay(deviceDTO.getInstallWay())
                        .gateWay(deviceDTO.getGateWay())
                        .ip(deviceDTO.getIp())
                        .mac(deviceDTO.getMac().trim().toUpperCase())
                        .status("01")
                        .createTime(new Date())
                        .createUser(tokenUserInfo.getUserId())
                        .remark(deviceDTO.getRemark())
                        .deviceSize(deviceDTO.getDeviceSize())
                        .build();
                log.info("插入设备信息：{}", deviceDO);
                this.deviceDao.insert(deviceDO);

                //插入设备默认分组
                DeviceGroupDO deviceGroupDO = DeviceGroupDO.builder()
                        .groupId(Integer.parseInt(ConstantEnum.GROUP_DEFAULT.getType()))
                        .deviceId(deviceDO.getDeviceId()).build();

                if (groupId != null) {
                    GroupDO groupDO = this.groupDao.selectById(groupId);
                    if (groupDO != null) {
                        deviceGroupDO.setGroupId(groupId);
                    }
                    else {
                        throw new BizException("该设备分组不存在！");
                    }
                }
                this.deviceGroupDao.insert(deviceGroupDO);
            }
            else {
                throw new BizException("该设备MAC地址已存在！");
            }
        }
        catch (Exception e) {
            log.error("该设备保存失败！{}", e.getMessage());
            throw new BizException("该设备保存失败！" + e.getMessage());
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(DeviceDTO deviceDTO, TokenUserInfo tokenUserInfo) {
        try {
            QueryWrapper<DeviceDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(StringUtils.isNotBlank(deviceDTO.getMac()), "MAC", deviceDTO.getMac().trim().toUpperCase());
            DeviceDO one = this.deviceDao.selectOne(queryWrapper);
            if (one == null || (one != null && deviceDTO.getDeviceId().equals(one.getDeviceId()))) {
                DeviceDO deviceDO = DeviceDO.builder()
                        .deviceId(deviceDTO.getDeviceId())
                        .orgId(deviceDTO.getOrgId())
                        .orgName(deviceDTO.getOrgName())
                        .deviceType(deviceDTO.getDeviceType())
                        .deviceName(deviceDTO.getDeviceName())
                        .deviceSystem(deviceDTO.getDeviceSystem())
                        .deviceBrand(deviceDTO.getDeviceBrand())
                        .deviceModel(deviceDTO.getDeviceModel())
                        .deviceSource(deviceDTO.getDeviceSource())
                        .upplierId(deviceDTO.getUpplierId())
                        .addressAbbr(deviceDTO.getAddressAbbr())
                        .installWay(deviceDTO.getInstallWay())
                        .gateWay(deviceDTO.getGateWay())
                        .ip(deviceDTO.getIp())
                        .mac(deviceDTO.getMac().trim().toUpperCase())
                        .updateTime(new Date())
                        .updateUser(tokenUserInfo.getUserId())
                        .remark(deviceDTO.getRemark())
                        .deviceSize(deviceDTO.getDeviceSize())
                        .build();
                log.info("修改设备信息：{}", deviceDO);
                this.deviceDao.updateById(deviceDO);
            }
            else {
                throw new BizException("该设备MAC地址已存在！");
            }
        }
        catch (Exception e) {
            log.error("设备更新失败：{}", e.getMessage());
            throw new BizException("设备更新失败");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Integer id) {
        log.info("删除设备信息，设备ID：{}", id);
        try {
            QueryWrapper<DeviceGroupDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("DEVICE_ID", id);
            this.deviceGroupDao.delete(queryWrapper);
            this.deviceDao.deleteById(id);
        }
        catch (Exception e) {
            log.error("设备删除失败！{}", e.getMessage());
            throw new BizException("设备删除失败！");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateStatus(DeviceDTO deviceDTO) {
        DeviceDO deviceDO = DeviceDO.builder()
                .deviceId(deviceDTO.getDeviceId())
                .updateTime(new Date())
                .status(deviceDTO.getStatus()).build();
        try {
            return this.deviceDao.updateById(deviceDO);
        }
        catch (Exception e) {
            throw new BizException("设备状态更新失败");
        }
    }

    @Override
    public IPage<DeviceDTO> queryList(PageQueryModel pageQueryModel) {
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
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String groupId = (String) queryParam.get("groupId");
        String terminalNum = (String) queryParam.get("terminalNum");
        String orgId = (String) queryParam.get("orgId");
        String status = (String) queryParam.get("status");
        String deviceType = (String) queryParam.get("deviceType");
        log.info("设备信息查询：{}", pageQueryModel);
        IPage<DeviceDTO> deviceDOIPage = this.deviceDao.selectPageListByPageQueryModel(page, groupId, terminalNum, orgId, status, deviceType);
        return deviceDOIPage;
    }

    @Override
    public Integer checkTerminalNum(String terminalNum) {
        QueryWrapper<DeviceDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(terminalNum), "TERMINAL_NUM", terminalNum);
        return this.deviceDao.selectCount(queryWrapper);
    }

    @Override
    public DeviceDTO queryDeviceByMac(String macAddress) {
        if (StringUtils.isBlank(macAddress)) {
            throw new BizException("请传入设备Mac地址进行设备信息查询");
        }
        QueryWrapper<DeviceDO> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("MAC", macAddress);
        queryWrapper.in("MAC", Arrays.asList(StringUtils.split(macAddress, ",")));
        List<DeviceDO> deviceList = this.deviceDao.selectList(queryWrapper);
        if (deviceList.size() == 0) {
            throw new BizException("根据Mac地址【" + macAddress + "】未查询到对应设备信息数据");
        }
        if (deviceList.size() > 1) {
            throw new BizException("根据Mac地址【" + macAddress + "】查询到多笔设备信息数据，请检查设备数据维护是否准确");
        }
        DeviceDTO deviceDTO = new DeviceDTO();
        //PropertyUtil.copyProperties(deviceList.get(0), deviceDTO);
        BeanUtil.copyProperties(deviceList.get(0), deviceDTO);
        //记录设备状态日志
        if (deviceList != null && deviceList.size() == 1) {
            StatusLogDO statusLogDO = StatusLogDO.builder()
                    .terminalNum(deviceList.get(0).getTerminalNum())
                    .mac(deviceList.get(0).getMac())
                    .messageLog("设备正常上线")
                    .createTime(LocalDateTime.now()).build();
            this.statusLogDao.insert(statusLogDO);
        }

        return deviceDTO;
    }
}
