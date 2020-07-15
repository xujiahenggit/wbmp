package com.bank.manage.dao;


import com.bank.manage.dto.KioskDto;
import com.bank.manage.vo.SsarunDeviceVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SsarunDeviceDao {

    IPage<SsarunDeviceVo> ssarunDeviceList(Page<SsarunDeviceVo> page, @Param("model") KioskDto KioskDto);

}
