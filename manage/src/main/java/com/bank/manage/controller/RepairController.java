package com.bank.manage.controller;

import com.bank.core.entity.BizException;
import com.bank.manage.dao.InspectionEquipmentDto;
import com.bank.manage.dao.LargerScreenDto;
import com.bank.manage.dto.*;
import com.bank.manage.service.RepairService;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/***
 * 陈强
 *
 */
@Api(tags = "设备管理--工单")
@RestController
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private RepairService repairService;

    @ApiOperation(value ="故障工单新增")
    @PostMapping("/saveRepair")
    public int saveRepair(@RequestBody WorkOrderDto workOrderDto){
       return repairService.saveRepair(workOrderDto);
    }

    @ApiOperation(value ="故障工单新增--设备信息查询")
    @GetMapping("/getequipmentByCode/{terminalCode}")
    @ApiImplicitParam(name = "terminalCode",value = "终端编号",required = true,paramType = "path")
    public List<EquipmentVo> getEquipmentByCode(@PathVariable String terminalCode){
        if("".equals(terminalCode)){
            throw new BizException("终端编号不能为空");
        }
       return  repairService.getEquipmentByCode(terminalCode);

    }


    @ApiOperation(value ="工单详情查询")
    @GetMapping("/getRepairById/{repairCode}")
    @ApiImplicitParam(name = "repairCode",value = "工单编号",required = true,paramType = "path")
    public RepairVo getRepairById(@PathVariable String repairCode){
        if("".equals(repairCode)){
            throw new BizException("工单编号不能为空");
        }
        return repairService.getRepairById(repairCode);
    }


    @ApiOperation(value ="巡检工单新增--设备信息查询")
    @PostMapping("/getInspectionEquipmentByCode")
    public List<InspectionEquipmentVo> getInspectionEquipmentByCode(@RequestBody InspectionEquipmentDto inspectionEquipmentDto ){
        return  repairService.getInspectionEquipmentByCode(inspectionEquipmentDto);

    }

    @ApiOperation(value ="巡检工单新增")
    @PostMapping("/saveInspectionWorkOrder")
    public int saveInspectionWorkOrder(@RequestBody InspectionWorkOrderDto inspectionWorkOrderDto){
        return repairService.saveInspectionWorkOrder(inspectionWorkOrderDto);
    }

    @ApiOperation(value ="投诉工单新增")
    @PostMapping("/saveComplaintsWorkOrder")
    public int saveComplaintsWorkOrder(@RequestBody ComplaintsWorkOrderDto complaintsWorkOrderDto){
        return repairService.saveComplaintsWorkOrder(complaintsWorkOrderDto);
    }

    @GetMapping("/getDevicesNumber")
    @ApiOperation(value ="设备管理首页-设备状态")
    public DevicesNumberVo getDevicesNumber(){
        return  repairService. getDevicesNumber();
    }

    @PostMapping("/getLargerScreen")
    @ApiOperation(value ="大屏设备列表查询")
   public IPage<LargerScreenVo> getLargerScreen(@RequestBody LargerScreenDto largerScreenDto){
       return  repairService.getLargerScreen(largerScreenDto);
   }

    @GetMapping("/getPrinterByCode/{terminalCode}")
    @ApiOperation(value ="打印机状态查询")
   public PrinterVo getPrinterByCode(@PathVariable String terminalCode){
        if("".equals(terminalCode)){
            throw new BizException("终端编号不能为空");
        }
        return repairService.getPrinterByCode(terminalCode);
  }

      @PostMapping("/getWorkOrder")
      @ApiOperation(value ="工单列表查询")
      public IPage<WorkOrderVO> getWorkOrder(@RequestBody WorkOrdersDto workOrdersDto){
        return repairService.getWorkOrder(workOrdersDto);
      }

    @ApiOperation(value ="（主管，工程师收到故障单）人工创建工单信息查询")
    @GetMapping("/getBreakWorkOrderByCode/{repairCode}")
    @ApiImplicitParam(name = "repairCode",value = "工单编号",required = true,paramType = "path")
    public BreakDownWorkOrderVo getBreakWorkOrderByCode(@PathVariable String repairCode){
        if("".equals(repairCode)){
            throw new BizException("工单编号不能为空");
        }
        return repairService.getBreakWorkOrderByCode(repairCode);
    }

    @ApiOperation(value ="已完成工单详情查询")
    @PostMapping("/getCompletedWordOrderByCode")
    public List<CompletedWordOrderVo> getCompletedWordOrderByCode(@RequestBody CompletedWordOrderDto completedWordOrderDto){

        return repairService.getCompletedWordOrderByCode(completedWordOrderDto);
    }

    @ApiOperation(value ="服务信息查询")
    @PostMapping("/getServiceInformationByCode/{repairCode}")
    @ApiImplicitParam(name = "repairCode",value = "工单编号",required = true,paramType = "path")
    public ServiceInformationsVo getServiceInformationByCode(String repairCode){
        if("".equals(repairCode)){
            throw new BizException("工单编号不能为空");
        }
        return repairService.getServiceInformationByCode(repairCode);
    }

}
