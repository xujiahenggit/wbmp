package com.bank.manage.dao;

import com.bank.manage.dos.TermStatusDO;
import com.bank.manage.dto.DeviceTermDTO;
import com.bank.manage.dto.HomePageTermStatusDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;

import java.util.List;

/**
 * 终端状态
 *
 * @author
 * @date 2020-7-27
 */
public interface TermStatusDao extends BaseMapper<TermStatusDO> {

    List<DeviceTermDTO> listDeviceTermDTO(@Param("deviceClass") String deviceClass, @Param("branchnum") String branchnum);

    List<HomePageTermStatusDTO> selectHomePageDeviceTerm(@Param("branchnum") String branchnum);

void updateByTime(@Param("model") LocalDateTime agentTime);
}
