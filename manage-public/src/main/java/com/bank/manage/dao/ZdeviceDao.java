package com.bank.manage.dao;

import com.bank.manage.dos.TransDO;
import com.bank.manage.dto.HomePageTransHnumDTO;
import com.bank.manage.dto.SsbRankingDTO;
import com.bank.manage.dto.SsbTransRankBarDTO;
import com.bank.manage.dto.ZdeviceDictDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *
 * @author
 * @date 2020-7-29
 */
public interface ZdeviceDao extends BaseMapper<TransDO> {

    /**
     * 设备类型统计
     * @return
     */
    List<ZdeviceDictDTO> listDeviceTypeCount(@Param("branchnum") String branchnum);

    /**
     * 设备厂商统计
     * @return
     */
    List<ZdeviceDictDTO> listDevmanuCount(@Param("branchnum") String branchnum);

    /**
     * 设备类型统计Slave
     * @return
     */
    List<ZdeviceDictDTO> listDeviceTypeCountSlave(@Param("branchnum") String branchnum);

    /**
     * 设备厂商统计Slave
     * @return
     */
    List<ZdeviceDictDTO> listDevmanuCountSlave(@Param("branchnum") String branchnum);
}
