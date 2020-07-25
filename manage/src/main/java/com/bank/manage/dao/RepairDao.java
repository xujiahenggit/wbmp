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

    ServiceInfoVo getServiceInfoList(@Param("repairCode") String repairCode);

    /**
     * 工单流转历史
     * @param repairCode
     * @return
     */
    List<RepairHistoryListVo> getRepairHistoryList(@Param("repairCode") String repairCode);

    /**
     * 根据工单id获取附件地址
     * @param repairCode
     * @return
     */
    List<String> getOrderAttachList(@Param("repairCode") String repairCode);

    List<BuffetLineVo> getBuffetLineList(@Param("model")BuffetLineDto buffetLineDto);

    List<BuffetLineVo> getBranchVoList();

    List<BuffetLineVo> getSubbranchList(@Param("code") String code);

    List<EngineerListVo> getEngineerList(String repairCode);

    List<DirectorVo> getDirectorList(String repairCode);

    RepairVo getComplaintsRepairById(String repairCode);

    InspectionRepairVo getInspectionRepairById(String repairCode);

    RepairVo getWOrkSystemByCode(String repairCode);

    String getUserByCode(String userId);

    List<WorkOrderVO> getWorkOrderBySystemList(@Param("model") WorkOrdersDto workOrdersDto);

    List<WorkOrderVO> getWorkOrderList(@Param("model")WorkOrdersDto workOrdersDto);

    String getUserNameById(String createId);

    void saveWorkOrderWater(@Param("model") WordOrderWaterDto wordOrderWaterDto);

    ServiceInfoVo getServiceInfoListBySys(@Param("repairCode") String repairCode,@Param("vendor") String vendor);

    List<EngineerListVo> getEngineerListBySyS(String vendor);

    List<DirectorVo> getDirectorListBySyS(String vendor);
}
