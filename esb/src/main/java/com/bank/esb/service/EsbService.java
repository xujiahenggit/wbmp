package com.bank.esb.service;

import com.bank.esb.dos.DatBranchDO;
import com.bank.esb.dos.DatSelfsvcbankDO;
import com.bank.esb.dos.DatSubbranchDO;
import com.bank.esb.dto.CSInfoDto;
import com.bank.esb.dto.EngineerDto;
import com.bank.esb.dto.ManagerDto;
import com.bank.esb.dto.OrderDto;
import com.bank.esb.vo.OrderNumVo;

import java.util.List;
import java.util.Map;

public interface EsbService {

    List<EngineerDto> getEngineer(String engineerMId, String seachTxt);

    List<ManagerDto> getManager(Object deviceId);

    Map<String, Object> getDeviceInfo(String toString);

    List<CSInfoDto> getCSInfo();

    List<OrderDto> getEsbErrOrder(OrderNumVo orderNumVo);

    Map<String, String> getEngineerInfo(String engineerId);

    List<DatBranchDO> getBranch(String orgId);

    List<DatSubbranchDO> getSubBranch(String orgId);

    List<DatSelfsvcbankDO> getSelfBranch(String orgId);

    Map<String, Object> getXjdInfo(String deviceNo);
}
