package com.bank.manage.service;

import com.bank.manage.dao.InspectionEquipmentDto;

import com.bank.manage.dao.LargerScreenDto;
import com.bank.manage.dos.RepairDo;
import com.bank.manage.dto.WorkOrderDto;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface RepairService {
    int saveRepair(WorkOrderDto repairDto);

    RepairVo getRepairById(String repairCode);

    EquipmentVo getEquipmentByCode(String terminalCode);

    List<InspectionEquipmentVo> getInspectionEquipmentByCode(InspectionEquipmentDto inspectionEquipmentDto);

    DevicesNumberVo getDevicesNumber();

    IPage<LargerScreenVo> getLargerScreen(LargerScreenDto largerScreenDto);

    PrinterVo getPrinterByCode(String terminalCode);
}
