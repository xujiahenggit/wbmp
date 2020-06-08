package com.bank.manage.service.impl;

import com.bank.manage.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: Andy
 * @Date: 2020/4/9 9:48
 * 行员信息管理 服务
 */
@Service
@Slf4j
public class HomeServiceImpl<HomeDao> implements HomeService {

    @Resource

    private com.bank.manage.dao.HomeDao homeDao;




    @Override
    public Map<String, Object> getHomePanleData(int day) {
        return homeDao.getHomePanleData(day);
    }

    @Override
    public List<Map<String, Object>> getNewMaterial(Integer day, String orgid) {
        return homeDao.getNewMaterial(day,orgid);
    }
}
