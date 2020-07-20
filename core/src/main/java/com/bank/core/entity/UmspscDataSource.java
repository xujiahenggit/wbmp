package com.bank.core.entity;

import org.apache.commons.lang.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;

import cscb.password.ThreeDes;
import lombok.extern.slf4j.Slf4j;

/**
 * 处理数据源密码加密
 * ClassName: UmspscDataSource
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/20 14:52:05
 */
@Slf4j
public class UmspscDataSource extends DruidDataSource {

    /**
     *
     */
    private static final long serialVersionUID = 8563677587331336094L;

    @Override
    public String getPassword() {

        String encPassword = super.getPassword();
        if (StringUtils.isBlank(encPassword)) {
            throw new BizException("请正确配置数据源密码！");
        }

        log.info("数据库密码密文：{}", encPassword);
        String password = ThreeDes.dePassword(encPassword);
        log.info("数据库密码解密后明文：{}", password);
        return password;
    }

}
