package com.bank.manage.dos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDO implements Serializable {
    private String id;
    private String strSsbNum;
    private String strSsbName;
    private String strBankNum;
    private String strBankName;
    //    private String strBankNameEn;
    private String strBranchNum;
    private String strBranchName;
    private String strSubBranchNum;
    private String strSubBranchName;
    private String title;
    private String type;
    private String key;
    private String parentKey;
}
