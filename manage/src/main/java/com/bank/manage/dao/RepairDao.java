package com.bank.manage.dao;

import com.bank.manage.dos.ManageWorkOrderDO;
import com.bank.manage.dto.*;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepairDao extends BaseMapper<ManageWorkOrderDO> {


    int saveRepair(WorkOrderDto workOrderDto);

    RepairVo getRepairById(@Param(value = "repairCode")String repairCode);

    List<EquipmentVo> getEquipmentByCode(@Param(value = "terminalCode") String terminalCode);

    int saveWorkOrder(WorkOrderDto workOrderDto);

    List<InspectionEquipmentVo> getInspectionEquipmentByCode(InspectionEquipmentDto inspectionEquipmentDto);

    List<InspectionEquipmentVo> getInspectionEquipment(InspectionEquipmentDto inspectionEquipmentDto);

    DevicesNumberVo getDevicesNumber( @Param("orgId") String orgId);

    IPage<LargerScreenVo> getLargerScreen(Page<LargerScreenVo> page, @Param("branchCode")String branchCode,
                                          @Param("terminalCode") String terminalCode);

    PrinterVo getPrinterByCode(String terminalCode);

    IPage<WorkOrderVO> getWorkOrder(Page<WorkOrderVO> page,@Param("model") WorkOrdersDto workOrdersDto);

    IPage<WorkOrderVO> getWorkOrderByMe(Page<WorkOrderVO> page,@Param("model") WorkOrdersDto workOrdersDto);

    IPage<WorkOrderVO> getWorkOrderBySystem(Page<WorkOrderVO> page,@Param("model") WorkOrdersDto workOrdersDto);

    IPage<WorkOrderVO> getWorkOrderByOther(Page<WorkOrderVO> page,@Param("model") WorkOrdersDto workOrdersDto);

    int saveInspectionWorkOrder(InspectionWorkOrderDto inspectionWorkOrderDto);

    int saveComplaintsWorkOrder(ComplaintsWorkOrderDto complaintsWorkOrderDto);

    BreakDownWorkOrderVo getBreakWorkOrderByCode(@Param("repairCode") String repairCode);

    List<CompletedWordOrderVo> getCompletedWordOrderByCode(CompletedWordOrderDto completedWordOrderDto);

    ServiceInformationsVo getServiceInformationByCode( @Param("repairCode") String repairCode);

    IPage<KioskVo> kioskDto(Page<KioskVo> page,@Param("model") KioskDto kioskDto);

    CompletedWordOrderVo getKioskById(@Param("id") String id);

    List<VendorVo> getVendorList();

    String getBuffetLine(@Param("orgId")  String orgId);

    IPage<WorkOrderVO> getWorkOrderByMeApp(Page<WorkOrderVO> page, @Param("model")WorkOrdersDto workOrdersDto);

    List<ServiceInfoVo> getServiceInfoList(@Param("repairCode") String repairCode);
}
