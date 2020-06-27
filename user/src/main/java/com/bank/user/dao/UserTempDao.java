package com.bank.user.dao;

import com.bank.user.dos.UserTempDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Author: Andy
 * @Date: 2020/4/8 17:09
 */
public interface UserTempDao extends BaseMapper<UserTempDO> {

    /**
     * 清空用户零时表 数据
     */
    void clearnTmepUserData();
}
