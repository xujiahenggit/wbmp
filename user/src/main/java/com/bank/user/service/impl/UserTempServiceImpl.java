package com.bank.user.service.impl;

import com.bank.user.dao.UserTempDao;
import com.bank.user.dos.UserTempDO;
import com.bank.user.service.UserTempService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Andy
 * @Date: 2020/4/8 17:10
 */
@Service
public class UserTempServiceImpl extends ServiceImpl<UserTempDao, UserTempDO> implements UserTempService {

    @Autowired
    private UserTempDao userTempDao;

    /**
     * 清空用户 零时表的数据
     */
    @Override
    public void clearnTmepUserData() {
        userTempDao.clearnTmepUserData();
    }
}
