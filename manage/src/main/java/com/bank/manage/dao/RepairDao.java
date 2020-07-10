package com.bank.manage.dao;

import com.bank.manage.dos.RepairDo;
import com.bank.manage.dto.WorkOrderDto;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepairDao extends BaseMapper<RepairDao> {


    int saveRepair(WorkOrderDto workOrderDto);

    RepairVo getRepairById(@Param(value = "repairCode")String repairCode);

    EquipmentVo getEquipmentByCode(@Param(value = "terminalCode") String terminalCode);

    int saveWorkOrder(WorkOrderDto workOrderDto);

    List<InspectionEquipmentVo> getInspectionEquipmentByCode(InspectionEquipmentDto inspectionEquipmentDto);

    List<InspectionEquipmentVo> getInspectionEquipment(InspectionEquipmentDto inspectionEquipmentDto);

    DevicesNumberVo getDevicesNumber();

    IPage<LargerScreenVo> getLargerScreen(Page<LargerScreenVo> page, @Param("branchCode")String branchCode,
                                          @Param("terminalCode") String terminalCode,@Param("selfBankCode") String selfBankCode);

    PrinterVo getPrinterByCode(String terminalCode);
}
