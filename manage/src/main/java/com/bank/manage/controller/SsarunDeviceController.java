package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.BizException;
import com.bank.manage.dto.DeviceTradeDto;
import com.bank.manage.dto.KioskDto;
import com.bank.manage.service.SsarunDeviceService;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
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
        //获取终端状态
        if(deviceDetailsVo != null){

            TerminalDetailsVo terminalDetailsVo = ssarunDeviceService.getTerminalDetailsById(deviceDetailsVo.getTerminalCode());
              //1.获取读卡器
            List<ReaderStatusList> readerStatusList =ssarunDeviceService.getReaderStatusListById(deviceDetailsVo.getTerminalCode());
            if(CollectionUtils.isNotEmpty(readerStatusList)){
                terminalDetailsVo.setReaderStatusListList(readerStatusList);
                //判断设备状态是否全部ok
                boolean temp = true;
                for(ReaderStatusList list : readerStatusList){
                    if(!"ok".equals(list.getStatus())){
                        temp =false;
                    }
                }

                if(!temp){
                    terminalDetailsVo.setTotalReaderStatus("故障");
                }else{
                    terminalDetailsVo.setTotalReaderStatus("ok");
                }

            }

              //2.获取打印机
            List<PrinterListVo> printerListVoList = ssarunDeviceService.getPrinterListById(deviceDetailsVo.getTerminalCode());
            if(CollectionUtils.isNotEmpty(printerListVoList)){
                terminalDetailsVo.setPrinterListVoList(printerListVoList);
                //判断设备状态是否全部ok
                boolean temp = true;
                for(PrinterListVo list : printerListVoList){
                    if(!"ok".equals(list.getStatus())){
                        temp =false;
                    }
                }

                if(!temp){
                    terminalDetailsVo.setTotalPrinterStatus("故障");
                }else{
                    terminalDetailsVo.setTotalPrinterStatus("ok");
                }
            }


            deviceDetailsVo.setTerminalDetailsVo(terminalDetailsVo);
            //服务厂商
            List<DeviceVendorVo> deviceVendorVo = ssarunDeviceService.getDeviceVendorByCode(deviceDetailsVo.getDeviceVendor());

            if(CollectionUtils.isNotEmpty(deviceVendorVo)){
                deviceDetailsVo.setDeviceVendorVo(deviceVendorVo);
            }


        }

            Map<String,Object> map =new HashMap<String,Object>();
            map.put("deviceDetailsVo",deviceDetailsVo);
            return map;
    }

    @ApiOperation(value ="自助设备模块查询")
    @GetMapping("/getDeviceMouldByDeviceId/{deviceId}")
    public List<SsarunDeviceModelVo> getDeviceMouldByDeviceId(@PathVariable String deviceId){
        if("".equals(deviceId) || null == deviceId ){
            throw new BizException("设备ID不能为空");
        }
       List<SsarunDeviceModelVo> list =  ssarunDeviceService.getDeviceModelList(deviceId);
        return list;
    }

    /**
     * 监控设备交易趋势
     */
    @ApiOperation(value ="监控设备交易趋势")
    @PostMapping("/DeviceTradeTend")
    public List<DeviceTradeTrendVo> DeviceTradeTrend(@RequestBody DeviceTradeDto deviceTradeDto){
        List<DeviceTradeTrendVo> list = ssarunDeviceService.getDeviceTradeList(deviceTradeDto);
        return list;
    }

}
