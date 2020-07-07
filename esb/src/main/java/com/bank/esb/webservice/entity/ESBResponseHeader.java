package com.bank.esb.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

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

    @XmlElement(name = "ReturnCode")
    private String ReturnCode;

    @XmlElement(name = "ProviderChannelId")
    private String ProviderChannelId;

    @XmlElement(name = "ResponseTime")
    private String ResponseTime;

    @XmlElement(name = "ReturnMessage")
    private String ReturnMessage;

    @XmlElement(name = "ProviderReference")
    private String ProviderReference;

    @XmlElement(name = "ProviderWorkingDate")
    private String ProviderWorkingDate;

}
