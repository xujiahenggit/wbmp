package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.manage.dto.KioskDto;
import com.bank.manage.service.SsarunDeviceService;
import com.bank.manage.vo.SsaViewTermStatusVo;
import com.bank.manage.vo.SsarunDeviceVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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


}
