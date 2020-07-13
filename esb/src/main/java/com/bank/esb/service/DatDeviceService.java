package com.bank.esb.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.esb.dos.DatDeviceDO;
import com.bank.esb.vo.DatDeviceVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 设备信息表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatDeviceService extends IService<DatDeviceDO> {

    /**
     * 自定义分页
     * @param page
     * @param datDevice
     * @return
     */
    IPage<DatDeviceDO> listPage(PageQueryModel pageQueryModel);

}
