package com.bank.core.entity;

import java.time.LocalDateTime;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HeaderDO {

    private String ServiceCode;

    private String AuthorizerID;

    private String ChannelId = "812";

    //设置流水号为简单模式
    private String ExternalReference = UUID.randomUUID().toString(true);

    private String RequestTime = DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmssSSS");

    private String TradeDate = DateUtil.format(LocalDateTime.now(), "yyyyMMdd");

    private String Version = "1.0";

    private String RequestBranchCode;

    private String RequestOperatorId;

    private String RequestOperatorType;

    private String Uuid;

    private String CliPhysicAddr;

    private String TermType = "00000";

    private String TermNo = "0000000000";

    private String Encrypt = "0";

    private String RequestType = "1";
}
