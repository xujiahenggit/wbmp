package com.bank.manage.service;

import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dao.InspectionEquipmentDto;

import com.bank.manage.dao.LargerScreenDto;
import com.bank.manage.dos.ManageWorkOrderDO;
import com.bank.manage.dto.*;
import com.bank.manage.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RepairService extends IService<ManageWorkOrderDO> {
    int saveRepair(WorkOrderDto repairDto);

    RepairVo getRepairById(String repairCode);

    List<EquipmentVo> getEquipmentByCode(String terminalCode);

    List<InspectionEquipmentVo> getInspectionEquipmentByCode(InspectionEquipmentDto inspectionEquipmentDto);

    DevicesNumberVo getDevicesNumber(String orgId);

    IPage<LargerScreenVo> getLargerScreen(LargerScreenDto largerScreenDto);

    PrinterVo getPrinterByCode(String terminalCode);

    Map<String, Object> getWorkOrder(WorkOrdersDto workOrdersDto);

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


    /**
     * 工单流转历史
     * @param repairCode
     * @return
     */
    List<RepairHistoryListVo> getRepairHistoryList(@Param("repairCode") String repairCode);

    List<BuffetLineVo> getBranchVoList();

    List<BuffetLineVo> getSubbranchList(String code);

    RepairVo getComplaintsRepairById(String repairCode);

    InspectionRepairVo getInspectionRepairById(String repairCode);

    RepairVo getWOrkSystemByCode(String repairCode);

    String getUserByCode(String userId,String repairCode);

    List<WorkOrderVO> getWorkOrderBySystemList(WorkOrdersDto workOrdersDto);

    List<WorkOrderVO> getWorkOrderList(WorkOrdersDto workOrdersDto);

    boolean wordOperation(TokenUserInfo tokenUserInfo,RepairCommentVo repairCommentVo);

    int getUserRoleById(String userId, String s);

    List<PictureVo> getPictureByCode(String repairCode);

    String getAccompaniedByCode( String userId, String repairCode);
}
