package com.bank.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bank.core.config.DataSource;
import com.bank.core.config.DynamicDataSourceSwitcher;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.utils.MapUtils;
import com.bank.manage.dao.TermStatusDao;
import com.bank.manage.dao.TransDao;
import com.bank.manage.dao.ZdeviceDao;
import com.bank.manage.dos.TermStatusDO;
import com.bank.manage.dto.*;
import com.bank.manage.service.ZhomePageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 逾期催收时限
 *
 * @author
 * @date 2020-7-7
 */
@Service
@Slf4j
public class ZhomePageServiceImpl extends ServiceImpl<TermStatusDao, TermStatusDO> implements ZhomePageService {

    @Autowired
    private TermStatusDao termStatusDao;

    @Autowired
    private TransDao transDao;

    @Autowired
    private ZdeviceDao zdeviceDao;

    @Override
    public List<HomePageTermStatusDTO> selectHomePageDeviceTerm(String branchnum) {
        List<HomePageTermStatusDTO> homePageTermStatusDTOS = this.termStatusDao.selectHomePageDeviceTerm(branchnum);
        return homePageTermStatusDTOS;
    }

     @Override
    public List<DeviceTermDTO> listDeviceTermDTO(String deviceClass, String branchnum) {
        List<DeviceTermDTO> homePageDeviceTermDTOS = this.termStatusDao.listDeviceTermDTO(deviceClass, branchnum);
        return homePageDeviceTermDTOS;
    }

    @Override
    public List<SsbTransRankBarDTO> listSsbTransRankBar(String deviceClass, String branchnum) {
        List<SsbTransRankBarDTO> ssbTransRankBarDTOS = this.transDao.listSsbTransRankBar(deviceClass, branchnum);
        return ssbTransRankBarDTOS;
    }

    @Override
    public List<SsbRankingDTO> listSsbRanking(String deviceClass, String branchnum) {
        List<SsbRankingDTO> ssbRankingDTOS = this.transDao.listSsbRanking(deviceClass, branchnum);
        return ssbRankingDTOS;
    }

    @Override
    public List<HomePageTransHnumDTO>  selectTransGross(String branchnum) {
        List<HomePageTransHnumDTO>  homePageTransHnumDTO = this.transDao.selectTransGross(branchnum);
        return homePageTransHnumDTO;
    }

    @Override
    public List<ZdeviceDictDTO> listDeviceTypeCount(String branchnum) {
        List<ZdeviceDictDTO> zdeviceDictDTOS = this.zdeviceDao.listDeviceTypeCount(branchnum);
        return zdeviceDictDTOS;
    }

    @Override
    public List<ZdeviceDictDTO> listDevmanuCount(String branchnum) {
        List<ZdeviceDictDTO> zdeviceDictDTOS = this.zdeviceDao.listDevmanuCount(branchnum);
        return zdeviceDictDTOS;
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<ZdeviceDictDTO> listDeviceTypeCountSlave(String branchnum) {
        List<ZdeviceDictDTO> zdeviceDictDTOS = this.zdeviceDao.listDeviceTypeCountSlave(branchnum);
        return zdeviceDictDTOS;
    }

    @Override
    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    public List<ZdeviceDictDTO> listDevmanuCountSlave(String branchnum) {
        List<ZdeviceDictDTO> zdeviceDictDTOS = this.zdeviceDao.listDevmanuCountSlave(branchnum);
        return zdeviceDictDTOS;
    }

}
