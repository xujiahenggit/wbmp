package com.bank.manage.service;

import com.bank.manage.dao.InspectionEquipmentDto;

import com.bank.manage.dao.LargerScreenDto;
import com.bank.manage.dto.ComplaintsWorkOrderDto;
import com.bank.manage.dto.InspectionWorkOrderDto;
import com.bank.manage.dto.WorkOrderDto;
import com.bank.manage.dto.WorkOrdersDto;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface RepairService {
    int saveRepair(WorkOrderDto repairDto);

    RepairVo getRepairById(String repairCode);

    List<EquipmentVo> getEquipmentByCode(String terminalCode);

    List<InspectionEquipmentVo> getInspectionEquipmentByCode(InspectionEquipmentDto inspectionEquipmentDto);

    DevicesNumberVo getDevicesNumber();

    IPage<LargerScreenVo> getLargerScreen(LargerScreenDto largerScreenDto);

    PrinterVo getPrinterByCode(String terminalCode);

    IPage<WorkOrderVO> getWorkOrder(WorkOrdersDto workOrdersDto);

    int saveInspectionWorkOrder(InspectionWorkOrderDto inspectionWorkOrderDto);

    int saveComplaintsWorkOrder(ComplaintsWorkOrderDto complaintsWorkOrderDto);

    BreakDownWorkOrderVo getBreakWorkOrderByCode(String repairCode);
}
