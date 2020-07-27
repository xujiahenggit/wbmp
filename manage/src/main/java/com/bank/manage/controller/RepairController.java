package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dao.InspectionEquipmentDto;
import com.bank.manage.dao.LargerScreenDto;
import com.bank.manage.dos.ManageWorkOrderDO;
import com.bank.manage.dos.WorkWaterDO;
import com.bank.manage.dto.*;
import com.bank.manage.service.ManageWorkOrderAttachmentService;
import com.bank.manage.service.ManageWorkWaterService;
import com.bank.manage.service.RepairService;
import com.bank.manage.util.Tools;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;


/***
 * 陈强
 *
 */
@Api(tags = "监控平台-工单信息接口")
@RestController
@RequestMapping("/repair")
public class RepairController extends BaseController {
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

    @ApiOperation(value ="（故障）工单详情查询")
    @PostMapping("/getRepairById")
    public RepairVo getRepairById(@RequestBody ConditionsDto conditionsDto, HttpServletRequest request){
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);

          if("1".equals(conditionsDto.getFlog())){
              //系统自建
              RepairVo repairVo =new  RepairVo();
              repairVo= repairService.getWOrkSystemByCode(conditionsDto.getRepairCode());
              repairVo.setCreateName("admin");
              return repairVo;
          }else{
              //人工创建
              RepairVo repairVo =new  RepairVo();
              repairVo =  repairService.getRepairById(conditionsDto.getRepairCode());
              int i=repairService.getUserRoleById(tokenUserInfo.getUserId(),"19");
              if(i==0){
                  //判断是否是分行管理员
                  int b = repairService.getUserRoleById(tokenUserInfo.getUserId(),"18");
                  if(b==0){
                      repairVo.setUserType(0+"");
                  }else{
                      repairVo.setUserType(2+"");
                  }

              }else{
                  repairVo.setUserType(1+"");
              }

              //判断是否为创建人
              String isCreateUser = repairService.getUserByCode(tokenUserInfo.getUserId(),conditionsDto.getRepairCode());
              if(isCreateUser !=null ||!"".equals(isCreateUser)){
                  repairVo.setIsCreateUser("0");
              }else{
                  repairVo.setIsCreateUser("1");
              }

              return repairVo;
          }
    }

    @ApiOperation(value ="（投诉）工单详情查询")
    @GetMapping("/getComplaintsRepairById/{repairCode}")
    @ApiImplicitParam(name = "repairCode",value = "工单编号",required = true,paramType = "path")
    public RepairVo getComplaintsRepairById(@PathVariable String repairCode, HttpServletRequest request){
        if("".equals(repairCode)){
            throw new BizException("工单编号不能为空");
        }
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);

        RepairVo repairVo= repairService.getComplaintsRepairById(repairCode);
        if(repairVo == null){
            repairVo =new RepairVo();
        }
//        String orgType = "";
//        //总行
//        if (StringUtils.startsWith(tokenUserInfo.getOrgId(), "100")) {
//            orgType = "1";
//        }
//        //分支行
//        else if (StringUtils.startsWith(tokenUserInfo.getOrgId(), "102")) {
//            orgType = "2";
//        }
//        if(!"".equals(orgType)){
//            repairVo.setUserType(orgType);
//        }
        // 2:分行管理员；1:总行管理员
        int i=repairService.getUserRoleById(tokenUserInfo.getUserId(),"19");
        if(i==0){
            //判断是否是分行管理员
            int b = repairService.getUserRoleById(tokenUserInfo.getUserId(),"18");
            if(b==0){
                repairVo.setUserType("0");
            }else{
                repairVo.setUserType("2");
            }

        }else{
            repairVo.setUserType("1");
        }

        //判断是否为创建人
        String isCreateUser = repairService.getUserByCode(tokenUserInfo.getUserId(),repairCode);
        if(isCreateUser !=null ||!"".equals(isCreateUser)){
            repairVo.setIsCreateUser("0");
        }else{
            repairVo.setIsCreateUser("1");
        }
        return repairVo;
    }


    @ApiOperation(value ="（巡检）工单详情查询")
    @GetMapping("/getInspevtionRepairById/{repairCode}")
    @ApiImplicitParam(name = "repairCode",value = "工单编号",required = true,paramType = "path")
    public InspectionRepairVo getInspectionRepairById(@PathVariable String repairCode , HttpServletRequest request){
        if("".equals(repairCode)){
            throw new BizException("工单编号不能为空");
        }
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        InspectionRepairVo inspectionRepairVo =new InspectionRepairVo();
        inspectionRepairVo= repairService.getInspectionRepairById(repairCode);
        int i=repairService.getUserRoleById(tokenUserInfo.getUserId(),"19");
        if(i==0){
            //判断是否是分行管理员
            int b = repairService.getUserRoleById(tokenUserInfo.getUserId(),"18");
            if(b==0){
                inspectionRepairVo.setUserType(0+"");
            }else{
                inspectionRepairVo.setUserType(2+"");
            }

        }else{
            inspectionRepairVo.setUserType(1+"");
        }

        //判断是否为创建人
        String isCreateUser = repairService.getUserByCode(tokenUserInfo.getUserId(),repairCode);
        if(isCreateUser !=null ||!"".equals(isCreateUser)){
            inspectionRepairVo.setIsCreateUser("0");
        }else{
            inspectionRepairVo.setIsCreateUser("1");
        }
        return inspectionRepairVo;
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
      public Map<String, Object> getWorkOrder(@RequestBody WorkOrdersDto workOrdersDto){
          Map<String, Object> resultMap = new HashMap<>();
        if("4".equals(workOrdersDto.getSourceType())){
            //系统自建
            IPage<WorkOrderVO> workOrderList = repairService.getWorkOrderBySystem(workOrdersDto);

            resultMap.put("records", workOrderList.getRecords());
            resultMap.put("current", workOrderList.getCurrent());
            resultMap.put("size", workOrderList.getSize());
            resultMap.put("total", workOrderList.getTotal());

            return resultMap;
        }else if("5".equals(workOrdersDto.getSourceType())){
            List<WorkOrderVO> list = repairService.getWorkOrderList(workOrdersDto);
            //加上系统自建的
            List<WorkOrderVO> workOrderList = repairService.getWorkOrderBySystemList(workOrdersDto);
            if(!CollectionUtils.isNotEmpty(workOrderList)){
                workOrderList =new ArrayList<>();
            }
            if(CollectionUtils.isNotEmpty(list)){
                list.addAll(workOrderList);
                //分页
                resultMap.put("total", list.size());
                resultMap.put("current", workOrdersDto.getPageIndex());
                resultMap.put("size", workOrdersDto.getPageSize());

                if(CollectionUtils.isNotEmpty(list)){
                    int totalCount = list.size();
                    int page =workOrdersDto.getPageSize();
                    int pageIndex =workOrdersDto.getPageIndex();
                    int tem =page * pageIndex;
                    if(tem>=totalCount){
                       tem =totalCount;
                    }
                    int toIndex =(pageIndex-1)*workOrdersDto.getPageSize();
                    if(toIndex > totalCount){
                        toIndex =totalCount;
                    }

                    resultMap.put("records", list.subList(toIndex,tem));
                }
                return resultMap;
            }

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
            workWater.setDealWithTime(new Date());
            workWater.setOrgId(repairRebackVo.getOrgId());
            workWater.setDealWithPeopleId(repairRebackVo.getDealWithPeopleId());
            workWater.setDealWithPeopleName(repairRebackVo.getDealWithPeopleName());
            workWater.setDealWithPeopleRole(Integer.parseInt(repairRebackVo.getDealWithPeopleRole()));
            workWater.setDealWithNote(repairRebackVo.getDealWithNote());
            workWater.setCreateTime(LocalDateTime.now());
            workWater.setPhone(repairRebackVo.getPhone());
            workWater.setOperationType("8");
            flag =  manageWorkWaterService.save(workWater);
        }

        return flag;
    }


    @ApiOperation(value ="工单(操作)")
    @PostMapping("/repairComment")
    public Boolean repairComment(@RequestBody RepairCommentVo commentVo, HttpServletRequest request){
        if(null == commentVo.getWorkOrderCode()  || "".equals(commentVo.getWorkOrderCode())){
            throw new BizException("工单编号不能为空");
        }
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        Boolean flag =  repairService.wordOperation(tokenUserInfo,commentVo);

//        String workOrderCode = commentVo.getWorkOrderCode() ;
//        RepairVo repairVo = repairService.getRepairById(workOrderCode);
//        if(repairVo != null) {
//            //更新工单状态为已评论
//            LambdaUpdateWrapper<ManageWorkOrderDO> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
//            lambdaUpdateWrapper.eq(ManageWorkOrderDO::getWorkOrderCode,workOrderCode).set(ManageWorkOrderDO::getWorkOrderStatus,"10")
//            .set(ManageWorkOrderDO::getRating,commentVo.getRating())
//            .set(ManageWorkOrderDO::getRatingNote,commentVo.getRatingNote());
//            flag = repairService.update(lambdaUpdateWrapper);
//        }
//        //插入工单流水
//        WorkWaterDO  workWater = new WorkWaterDO();
//        workWater.setSerialNumber(Tools.getFreeOrderNo());
//        workWater.setWordOrderId(workOrderCode);
//        workWater.setDealWithType("1");
//        workWater.setDealWithTime(LocalDateTime.now());
//        workWater.setOrgId(commentVo.getOrgId());
//        workWater.setDealWithPeopleId(commentVo.getDealWithPeopleId());
//        workWater.setDealWithPeopleName(commentVo.getDealWithPeopleName());
//        workWater.setDealWithPeopleRole(Integer.parseInt(commentVo.getDealWithPeopleRole()));
//        workWater.setDealWithNote(commentVo.getRatingNote());
//        workWater.setCreateTime(LocalDateTime.now());
//        workWater.setPhone(commentVo.getPhone());
//        workWater.setOperationType("1");
//        flag =  manageWorkWaterService.save(workWater);
        return flag ;
    }

    @ApiOperation(value ="工单流转历史")
    @GetMapping("/repairHistory/{repairCode}")
    @ApiImplicitParam(name = "repairCode",value = "工单编号",required = true,paramType = "path")
    public List<RepairHistoryListVo> repairHistory(@PathVariable String repairCode){
        List<RepairHistoryListVo> list = new ArrayList<>();
        if("".equals(repairCode) || null == repairCode ){
            throw new BizException("工单编号不能为空");
        }
        list = repairService.getRepairHistoryList(repairCode);
        return  list;
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

    @ApiOperation(value ="查询所有的自助行")
    @PostMapping("/getBuffetLineList")
    public List<BuffetLineVo> getBuffetLineList(@RequestBody BuffetLineDto buffetLineDto){
        return repairService.getBuffetLineList(buffetLineDto);

    }

    @ApiOperation(value ="查询所有的分行")
    @GetMapping("/getBranchVoList")
    public List<BuffetLineVo> getBranchVoList(){
        return repairService.getBranchVoList();

    }

    @ApiOperation(value ="查询所有的支行")
    @GetMapping("/getSubbranchList/{code}")
    public List<BuffetLineVo> getSubbranchList(@PathVariable String code){
        return repairService.getSubbranchList(code);

    }
}
