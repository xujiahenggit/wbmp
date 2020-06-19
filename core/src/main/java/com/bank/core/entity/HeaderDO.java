package com.bank.core.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@Data
public class HeaderDO {

    private String ServiceCode;
    private String AuthorizerID;
    private String ChannelId="811";
    private String ExternalReference= UUID.randomUUID().toString();
    private String RequestTime= DateUtil.format(LocalDateTime.now(),"yyyyMMddHHmmssSSS");
    private String TradeDate= DateUtil.format(LocalDateTime.now(),"yyyyMMdd");
    private String Version="1.0";
    private String RequestBranchCode;
    private String RequestOperatorId;
    private String RequestOperatorType;
    private String Uuid;
    private String CliPhysicAddr;
    private String TermType="00000";
    private String TermNo="0000000000";
    private String Encrypt="0";
    private String RequestType="1";
}
