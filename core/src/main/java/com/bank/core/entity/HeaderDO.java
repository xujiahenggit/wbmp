package com.bank.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeaderDO {
    private String ServiceCode;
    private String ChannelId;
    private String ExternalReference;
    private String RequestTime;
    private String TradeDate;
    private String Version;
    private String RequestBranchCode;
    private String RequestOperatorId;
    private String Encrypt;
    private String Uuid;
    private String CliPhysicAddr;
}
