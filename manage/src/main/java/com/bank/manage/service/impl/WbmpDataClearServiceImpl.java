package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.manage.dao.WbmpDataClearDao;
import com.bank.manage.service.WbmpDataClearService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class WbmpDataClearServiceImpl implements WbmpDataClearService {

    @Resource
    private WbmpDataClearDao wbmpDataClearDao;

    @Override
    public Boolean bakWbmpAbsTransinfo(String date) {
      Boolean flag = false;
      int result =  wbmpDataClearDao.bakWbmpAbsTransinfo(date);
      if(result >=0){
          flag = true;
          log.info("【备份日期为："+date+",wbmp_abs_transinfo 成功！】");
      }else {
            throw new BizException("备份日期为："+date+"wbmp_abs_transinfo 失败！");
        }
        return flag;
    }


    @Override
    public Boolean deleteWbmpAbsTransinfo(String date) {
        return null;
    }

    @Override
    public Boolean deleteWbmpAbsTransinfoH(String date) {
        return null;
    }
}
