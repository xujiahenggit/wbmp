package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.TermStatusDO;
import com.bank.manage.dto.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ZhomePageService extends IService<TermStatusDO> {


    /**
     * 查询设备运行状态 1.全部 2.现金 3.非现金
     * @return
     */
    List<HomePageTermStatusDTO> selectHomePageDeviceTerm(String branchnum);

    /**
     * 首页设备运行状态查询
     * @param deviceClass
     * @return
     */
    List<DeviceTermDTO> listDeviceTermDTO(String deviceClass, String branchnum);

    /**
     * 首页月度业务量统计
     * @param deviceClass
     * @return
     */
    List<SsbTransRankBarDTO> listSsbTransRankBar(String deviceClass, String branchnum);

    /**
     * 首页当月网点业务量排行榜
     * @param deviceClass
     * @return
     */
    List<SsbRankingDTO> listSsbRanking(String deviceClass, String branchnum);

    /**
     * 首页今日交易总量
     * @return
     */
    List<HomePageTransHnumDTO>  selectTransGross(String branchnum);

    /**
     * 设备类型统计
     * @return
     */
    List<ZdeviceDictDTO> listDeviceTypeCount(String branchnum);

    /**
     * 设备厂商统计
     * @return
     */
    List<ZdeviceDictDTO> listDevmanuCount(String branchnum);

    /**
     * 设备类型统计Slave
     * @return
     */
    List<ZdeviceDictDTO> listDeviceTypeCountSlave(String branchnum);

    /**
     * 设备厂商统计Slave
     * @return
     */
    List<ZdeviceDictDTO> listDevmanuCountSlave(String branchnum);

}
