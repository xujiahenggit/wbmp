package com.bank.manage.controller;

import com.bank.core.entity.BizException;
import com.bank.manage.dao.InspectionEquipmentDto;
import com.bank.manage.dao.LargerScreenDto;
import com.bank.manage.dos.ManageWorkOrderAttachmentDO;
import com.bank.manage.dos.ManageWorkOrderDO;
import com.bank.manage.dos.WorkWaterDO;
import com.bank.manage.dto.*;
import com.bank.manage.service.ManageWorkOrderAttachmentService;
import com.bank.manage.service.ManageWorkWaterService;
import com.bank.manage.service.RepairService;
import com.bank.manage.util.Tools;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/***
 * 陈强
 *
 */
@Api(tags = "监控平台-工单信息接口")
@RestController
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private RepairService repairService;
    @Autowired
    private ManageWorkWaterService manageWorkWaterService;
    @Autowired
    private ManageWorkOrderAttachmentService manageWorkOrderAttachmentService;



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


    @ApiOperation(value ="查询所有厂商")
    @GetMapping("/getVendorList")
    public List<VendorVo> getVendorList(){
        return repairService.getVendorList();
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

    @GetMapping("/getDevicesNumber/{orgId}")
    @ApiOperation(value ="设备管理首页-设备状态")
    public DevicesNumberVo getDevicesNumber(@PathVariable String orgId){

        return  repairService. getDevicesNumber(orgId);
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
        if("4".equals(workOrdersDto.getSourceType())){
            IPage<WorkOrderVO> workOrderList = repairService.getWorkOrderBySystem(workOrdersDto);
            List<WorkOrderVO> list = workOrderList.getRecords();
            //获取机构名称
            for(int i=0;i<list.size();i++){
                String name=   repairService.getBuffetLine(list.get(i).getOrgId());
                if(!"".equals(name) || null != name){
                    list.get(i).setOrgName(name);
                }

            }
           return workOrderList;
        }
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


    @ApiOperation(value ="工单退回")
    @PostMapping("/repairReback")
    public Boolean repairReback(@RequestBody RepairRebackVo repairRebackVo){
        Boolean flag = false;
        if(null == repairRebackVo.getWorkOrderCode()  || "".equals(repairRebackVo.getWorkOrderCode())){
            throw new BizException("工单编号不能为空");
        }
        String workOrderCode = repairRebackVo.getWorkOrderCode() ;
        RepairVo repairVo = repairService.getRepairById(workOrderCode);
        if(repairVo != null){
            //更新工单状态为退回
            LambdaUpdateWrapper<ManageWorkOrderDO> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            //更新工单状态为退回和更新退回原因字段
            lambdaUpdateWrapper.eq(ManageWorkOrderDO::getWorkOrderCode,workOrderCode).set(ManageWorkOrderDO::getWorkOrderStatus,"8")
            .set(ManageWorkOrderDO::getReturnOpinion,repairRebackVo.getDealWithNote());
            flag = repairService.update(lambdaUpdateWrapper);
            //插入工单流水
            WorkWaterDO  workWater = new WorkWaterDO();
            workWater.setSerialNumber(Tools.getFreeOrderNo());
            workWater.setWordOrderId(workOrderCode);
            workWater.setDealWithType("1");
            workWater.setDealWithTime(LocalDateTime.now());
            workWater.setDealWithPeopleId(repairRebackVo.getDealWithPeopleId());
            workWater.setDealWithPeopleName(repairRebackVo.getDealWithPeopleName());
            workWater.setOrgId(repairRebackVo.getOrgId());
            workWater.setCreateTime(LocalDateTime.now());
            workWater.setDealWithNote(repairRebackVo.getDealWithNote());
            flag =  manageWorkWaterService.save(workWater);
        }

        return flag;
    }


    @ApiOperation(value ="工单评论")
    @PostMapping("/repairComment")
    public Boolean repairComment(@RequestBody RepairCommentVo commentVo){
        Boolean flag = false;
        if(null == commentVo.getWorkOrderCode()  || "".equals(commentVo.getWorkOrderCode())){
            throw new BizException("工单编号不能为空");
        }
        String workOrderCode = commentVo.getWorkOrderCode() ;
        RepairVo repairVo = repairService.getRepairById(workOrderCode);
        if(repairVo != null) {
            //更新工单状态为已评论
            LambdaUpdateWrapper<ManageWorkOrderDO> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(ManageWorkOrderDO::getWorkOrderCode,workOrderCode).set(ManageWorkOrderDO::getWorkOrderStatus,"10")
            .set(ManageWorkOrderDO::getRating,commentVo.getRating())
            .set(ManageWorkOrderDO::getRatingNote,commentVo.getRatingNote());
            flag = repairService.update(lambdaUpdateWrapper);
        }
        return flag ;
    }

    @ApiOperation(value ="工单流转历史")
    @GetMapping("/repairHistory/{repairCode}")
    @ApiImplicitParam(name = "repairCode",value = "工单编号",required = true,paramType = "path")
    public Object repairHistory(@PathVariable String repairCode){
        Map<String,Object> map = new HashMap<>();
        if("".equals(repairCode) || null == repairCode ){
            throw new BizException("工单编号不能为空");
        }
        RepairVo repairVo = repairService.getRepairById(repairCode);
        if(repairVo != null) {
            ManageWorkOrderDO workOrderDO = repairService.getById(repairVo.getId());
            //截图URL的list
            QueryWrapper<ManageWorkOrderAttachmentDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("repair_id",repairCode);
            List<ManageWorkOrderAttachmentDO>  list = manageWorkOrderAttachmentService.list(queryWrapper);
            map.put("repairHistory",workOrderDO);
            map.put("imageList",list);
        }
        return  map;
    }

    @ApiOperation(value ="自助设备列表查询")
    @PostMapping("/getKioskList")
    public IPage<KioskVo> getKioskList(@RequestBody KioskDto kioskDto){
        return repairService.getKioskList(kioskDto);
    }

    @ApiOperation(value ="自助设备详情查询")
    @GetMapping("/getKioskById/{id}")
    public CompletedWordOrderVo getKioskById(@PathVariable String id){
        if("".equals(id) || null == id ){
            throw new BizException("id不能为空");
        }
        return repairService.getKioskById(id);

    }
}
