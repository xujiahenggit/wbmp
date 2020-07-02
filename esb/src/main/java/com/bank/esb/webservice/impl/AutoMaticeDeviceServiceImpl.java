package com.bank.esb.webservice.impl;

import com.bank.esb.dto.*;
import com.bank.esb.vo.*;
import com.bank.esb.webservice.AutoMaticeDeviceService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service
@WebService(name = "automaticedeviceservice", targetNamespace = "http://webservice.wbmp.com")
public class AutoMaticeDeviceServiceImpl implements AutoMaticeDeviceService {
    @Override
    public ResponseDto getOrders(OrderNumVo orderNumVo) {
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }

    @Override
    public OrderDealWithDto orderDealWith(OrderDealWithVo orderDealWithVo) {
        OrderDealWithDto orderDealWithDto =new OrderDealWithDto();
        return orderDealWithDto;
    }

    @Override
    public ResponseInstitutionsDto getInstitutions(InstitutionsVo institutionsVo) {
        ResponseInstitutionsDto responseInstitutionsDto =new ResponseInstitutionsDto();
        return responseInstitutionsDto;
    }

    @Override
    public ResponseInspectionSheetDto getInspectionSheet(InspectionSheetVo inspectionSheetVo) {
        ResponseInspectionSheetDto responseInspectionSheetDto = new ResponseInspectionSheetDto();
        return responseInspectionSheetDto;
    }

    @Override
    public InspectionSheetsDto getInspectionSheets(InspectionSheetsVo inspectionSheetsVo) {
        InspectionSheetsDto inspectionSheetsDto =new InspectionSheetsDto();
        return inspectionSheetsDto;
    }

    @Override
    public ResponseEngineerDto getEngineer(EngineerVo engineerVo) {
        ResponseEngineerDto responseEngineerDto =new ResponseEngineerDto();
        return responseEngineerDto;
    }

    @Override
    public RepairOrderDispatchDto getRepairOrderDispatch(RepairOrderDispatchVo repairOrderDispatchVo) {
        RepairOrderDispatchDto repairOrderDispatchDto =new RepairOrderDispatchDto();
        return repairOrderDispatchDto;
    }

    @Override
    public StateChangesDto stateChanges(StateChangesVo stateChangesVo) {
        StateChangesDto stateChangesDto =new StateChangesDto();
        return stateChangesDto;
    }

    @Override
    public OrderSubmissionDto orderSubmission(OrderSubmissionVo orderSubmissionVo) {
        OrderSubmissionDto orderSubmissionDto =new OrderSubmissionDto();
        return orderSubmissionDto;
    }

    @Override
    public RepairOrderBDto repairOrder(RepairOrderBVo repairOrderBVo) {
        RepairOrderBDto repairOrderBDto = new RepairOrderBDto();
        return repairOrderBDto;
    }

    @Override
    public ResponseEquipmentDetailDto getEquipmentDetail(EquipmentDetailVo equipmentDetailVo) {
        ResponseEquipmentDetailDto responseEquipmentDetailDto = new ResponseEquipmentDetailDto();
        return responseEquipmentDetailDto;
    }

    @Override
    public ResonseTransferInformationDto getTransferInformation(TransferInformationVo transferInformationVo) {
        ResonseTransferInformationDto resonseTransferInformationDto =new ResonseTransferInformationDto();
        return resonseTransferInformationDto;
    }

    @Override
    public ResponServiceInformationDto getServiceInformation(ServiceInformationVo serviceInformationVo) {
        ResponServiceInformationDto responServiceInformationDto =new ResponServiceInformationDto();
        return responServiceInformationDto;
    }


}
