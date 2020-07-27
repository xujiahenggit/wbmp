package com.bank.esb.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *
 * ClassName: ESBResponseHeader
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/07 09:44:16
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class ESBResponseHeader {

    @JSONField(name = "ReturnCode")
    private String ReturnCode;

    @JSONField(name = "ProviderChannelId")
    private String ProviderChannelId;

    @JSONField(name = "ResponseTime")
    private String ResponseTime;

    @JSONField(name = "ReturnMessage")
    private String ReturnMessage;

    @JSONField(name = "ProviderReference")
    private String ProviderReference;

    @JSONField(name = "ProviderWorkingDate")
    private String ProviderWorkingDate;

}
