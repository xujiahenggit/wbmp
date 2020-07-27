package com.bank.esb.webservice.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

/**
 *
 * ClassName: ESBRequestHeader
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/07 09:43:44
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class ESBRequestHeader {

    @XmlElement(name = "ServiceCode")
    private String ServiceCode;

    @XmlElement(name = "ChannelId")
    private String ChannelId;

    @XmlElement(name = "ExternalReference")
    private String ExternalReference;

    @XmlElement(name = "OriginalChannelId")
    private String OriginalChannelId;

    @XmlElement(name = "OriginalReference")
    private String OriginalReference;

    @XmlElement(name = "RequestTime")
    private String RequestTime;

    @XmlElement(name = "TradeDate")
    private String TradeDate;

    @XmlElement(name = "Version")
    private String Version;

    @XmlElement(name = "RequestBranchCode")
    private String RequestBranchCode;

    @XmlElement(name = "RequestOperatorId")
    private String RequestOperatorId;

    @XmlElement(name = "RequestOperatorType")
    private String RequestOperatorType;

    @XmlElement(name = "BankNoteBoxID")
    private String BankNoteBoxID;

    @XmlElement(name = "AuthorizerID")
    private String AuthorizerID;

    @XmlElement(name = "TermType")
    private String TermType;

    @XmlElement(name = "TermNo")
    private String TermNo;

    @XmlElement(name = "RequestType")
    private String RequestType;

    @XmlElement(name = "Encrypt")
    private String Encrypt;

}
