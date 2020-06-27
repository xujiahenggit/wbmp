package com.bank.user.service;

import com.bank.user.dos.UserTempDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: Andy
 * @Date: 2020/4/8 17:08
 */
public interface UserTempService extends IService<UserTempDO> {
    /**
     * 清空用户 零时表的数据
     */
    void clearnTmepUserData();
}
