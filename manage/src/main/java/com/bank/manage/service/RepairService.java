package com.bank.manage.service;

import com.bank.manage.dao.InspectionEquipmentDto;

import com.bank.manage.dao.LargerScreenDto;
import com.bank.manage.dos.ManageWorkOrderDO;
import com.bank.manage.dto.*;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RepairService extends IService<ManageWorkOrderDO> {
    int saveRepair(WorkOrderDto repairDto);

    RepairVo getRepairById(String repairCode);

    List<EquipmentVo> getEquipmentByCode(String terminalCode);

    List<InspectionEquipmentVo> getInspectionEquipmentByCode(InspectionEquipmentDto inspectionEquipmentDto);

    DevicesNumberVo getDevicesNumber(String orgId);

    IPage<LargerScreenVo> getLargerScreen(LargerScreenDto largerScreenDto);

    PrinterVo getPrinterByCode(String terminalCode);

    IPage<WorkOrderVO> getWorkOrder(WorkOrdersDto workOrdersDto);

    int saveInspectionWorkOrder(InspectionWorkOrderDto inspectionWorkOrderDto);

    int saveComplaintsWorkOrder(ComplaintsWorkOrderDto complaintsWorkOrderDto);

    BreakDownWorkOrderVo getBreakWorkOrderByCode(String repairCode);

    List<CompletedWordOrderVo> getCompletedWordOrderByCode(CompletedWordOrderDto completedWordOrderDto);

    ServiceInformationsVo getServiceInformationByCode(String repairCode);

    IPage<KioskVo> getKioskList(KioskDto kioskDto);

    CompletedWordOrderVo getKioskById(String id);

    List<VendorVo> getVendorList();

    IPage<WorkOrderVO> getWorkOrderBySystem(WorkOrdersDto workOrdersDto);

    String getBuffetLine(String orgId);

    List<BuffetLineVo> getBuffetLineList(BuffetLineDto buffetLineDto);
}
