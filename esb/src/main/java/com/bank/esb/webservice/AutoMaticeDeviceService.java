package com.bank.esb.webservice;

import com.bank.esb.dto.*;
import com.bank.esb.vo.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@WebService
public interface AutoMaticeDeviceService {

    @WebMethod
    @WebResult(name = "Response")
    ResponseDto getOrders(@WebParam(name = "Request") OrderNumVo orderNumVo );

    @WebMethod
    @WebResult(name = "Response")
    OrderDealWithDto orderDealWith(@WebParam(name = "Request") OrderDealWithVo orderDealWithVo );

    @WebMethod
    @WebResult(name = "Response")
    ResponseInstitutionsDto getInstitutions(@WebParam(name = "Request") InstitutionsVo institutionsVo );

    @WebMethod
    @WebResult(name = "Response")
    ResponseInspectionSheetDto getInspectionSheet(@WebParam(name = "Request") InspectionSheetVo inspectionSheetVo );

    @WebMethod
    @WebResult(name = "Response")
    InspectionSheetsDto getInspectionSheets(@WebParam(name = "Request") InspectionSheetsVo inspectionSheetsVo );

    @WebMethod
    @WebResult(name = "Response")
    ResponseEngineerDto getEngineer(@WebParam(name = "Request") EngineerVo engineerVo );

    @WebMethod
    @WebResult(name = "Response")
    RepairOrderDispatchDto getRepairOrderDispatch(@WebParam(name = "Request") RepairOrderDispatchVo repairOrderDispatchVo );


    @WebMethod
    @WebResult(name = "Response")
    StateChangesDto stateChanges(@WebParam(name = "Request") StateChangesVo stateChangesVo );

    @WebMethod
    @WebResult(name = "Response")
    OrderSubmissionDto orderSubmission(@WebParam(name = "Request") OrderSubmissionVo orderSubmissionVo );

    @WebMethod
    @WebResult(name = "Response")
    RepairOrderBDto repairOrder(@WebParam(name = "Request") RepairOrderBVo repairOrderBVo );

    @WebMethod
    @WebResult(name = "Response")
    ResponseEquipmentDetailDto getEquipmentDetail(@WebParam(name = "Request") EquipmentDetailVo equipmentDetailVo );

    @WebMethod
    @WebResult(name = "Response")
    ResonseTransferInformationDto getTransferInformation(@WebParam(name = "Request") TransferInformationVo transferInformationVo );


    @WebMethod
    @WebResult(name = "Response")
    ResponServiceInformationDto getServiceInformation(@WebParam(name = "Request") ServiceInformationVo serviceInformationVo );



}
