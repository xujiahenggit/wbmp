package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.BizException;
import com.bank.manage.dto.KioskDto;
import com.bank.manage.service.SsarunDeviceService;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备信息
 * @author
 * @date 2020-4-1
 */
@Api(tags = "监控平台-设备信息接口")
@RestController
@RequestMapping("/ssarun_device")
public class SsarunDeviceController extends BaseController {


    @Autowired
    private SsarunDeviceService ssarunDeviceService;


    @ApiOperation(value ="自助设备列表查询")
    @PostMapping("/getDeviceList")
    public IPage<SsarunDeviceVo> getDeviceList(@RequestBody KioskDto kioskDto){
        IPage page =  ssarunDeviceService.ssarunDeviceList(kioskDto);
       List<SsarunDeviceVo> serviceList = page.getRecords();
       if(serviceList.size() >0 ){
           List<String> termNoList = new ArrayList<String>();
            for (SsarunDeviceVo item:serviceList){
                termNoList.add(item.getTermNo());
            }
            //查询终端服务服务状态
           List<SsaViewTermStatusVo> termStatusList = ssarunDeviceService.termStatusList(termNoList);
            if(termStatusList.size()>0){
                for(int j =0;j<serviceList.size(); j++){
                    for (int i =0;i<termStatusList.size(); i++ ){
                            if(serviceList.get(j).getTermNo().equals(termStatusList.get(i).getStrtermnum())){
                                serviceList.get(j).setDeviceRunStatus(termStatusList.get(i).getSvcstatus());
                            }
                    }
                }
            }
           page.setRecords(serviceList);
       }
        return page;
    }

    @ApiOperation(value ="自助设备详情查询")
    @GetMapping("/getKioskById/{code}")
    public Map getKioskById(@PathVariable String code){
        if("".equals(code) || null == code ){
            throw new BizException("终端编号不能为空");
        }
        //获取设备信息
        DeviceDetailsVo deviceDetailsVo = ssarunDeviceService.getDeviceDetailsById(code);
        TerminalDetailsVo terminalDetailsVo = null;
        DeviceVendorVo deviceVendorVo = null;
        //获取终端状态
        if(deviceDetailsVo != null){
             terminalDetailsVo = ssarunDeviceService.getTerminalDetailsById(deviceDetailsVo.getTerminalCode());
            //服务厂商
            deviceVendorVo = ssarunDeviceService.getDeviceVendorByCode(deviceDetailsVo.getDeviceVendor());
        }
            Map map =new HashMap();
            map.put("deviceDetailsVo",deviceDetailsVo);
            map.put("terminalDetailsVo",terminalDetailsVo);
            map.put("deviceVendorVo",deviceVendorVo);
            return map;
    }

}
