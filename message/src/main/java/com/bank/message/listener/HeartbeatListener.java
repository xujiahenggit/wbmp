package com.bank.message.listener;

import com.bank.core.entity.BizException;
import com.bank.manage.dos.DeviceDO;
import com.bank.manage.service.DeviceService;
import com.bank.message.dos.DeviceOnline;
import com.bank.message.service.DeviceOnlineService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author ZHAO
 */
@Component
@Slf4j
public class HeartbeatListener {

    @Resource
    DeviceOnlineService deviceOnlineService;


    @Resource
    DeviceService deviceService;

    public void handle(Map<String, Object> map) {
        Object obj = map.get("MAC");
        if (obj == null) {
            throw new BizException("心跳检测===设备mac地址为必填字段");
        }
        String mac = obj.toString();

        DeviceOnline deviceOnline = DeviceOnline.builder()
                .mac(mac)
                .terminalNum(map.get("TERMINAL_NUM").toString())
                .updateTime(new Date())
                .build();
        deviceOnlineService.saveOrUpdate(deviceOnline);
        DeviceDO deviceDO = deviceService.getOne(new LambdaQueryWrapper<DeviceDO>().eq(DeviceDO::getMac,mac));
        if (deviceDO == null) {
            throw new BizException("设备表中未查到mac地址为：" + mac + " 的记录。");
        }

        String online = deviceDO.getOnline();
        if (online==null||online.equals("01")){
            deviceDO.setUpdateTime(new Date());
            deviceDO.setOnline("00");
            deviceService.updateById(deviceDO);
        }
        log.info("Mac地址为：{}的设备，心跳处理成功。", mac);

    }

}
