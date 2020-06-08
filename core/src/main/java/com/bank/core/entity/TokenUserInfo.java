package com.bank.core.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * 当前用户信息
 * ClassName: UserInfo
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/21 15:15:01
 */
@Data
@Builder
public class TokenUserInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4327069279129841674L;

    private String userId;

    private String userName;

    private String orgId;

    private String orgName;
}
