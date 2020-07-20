package com.bank.esb.dao;

import com.bank.esb.dos.DatBranchDO;
import com.bank.esb.dos.DatSelfsvcbankDO;
import com.bank.esb.dos.DatSubbranchDO;
import com.bank.esb.dto.CSInfoDto;
import com.bank.esb.dto.EngineerDto;
import com.bank.esb.dto.ManagerDto;
import com.bank.esb.dto.OrderDto;
import com.bank.esb.vo.OrderNumVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EsbDao {

    List<EngineerDto> getEngineer(@Param("id") String engineerMId, @Param("seachTxt") String seachTxt);

    List<ManagerDto> getCSMaster(@Param("id")String deviceid);

    Map<String, Object> getDeviceInfo(@Param("id")String deviceId);

    List<CSInfoDto> getCSInfo();

    List<OrderDto> getEsbErrOrder(@Param("o") OrderNumVo orderNumVo);

    Map<String, String> getEngineerInfo(@Param("engineerId") String engineerId);

    List<DatBranchDO> getBranch(@Param("orgid") String orgId);

    List<DatSubbranchDO> getSubBranch(@Param("orgid")String orgId);

    List<DatSelfsvcbankDO> getSelfBranch(@Param("orgid")String orgId);
}
