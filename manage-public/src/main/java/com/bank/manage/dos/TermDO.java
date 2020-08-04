package com.bank.manage.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TermDO implements Serializable {
    private String id;
    private String strTermNum;
    private String deviceid;
    private String strBankNum;
    private String strBankName;
    private String strBranchNum;
    private String strBranchName;
    private String strSubBranchNum;
    private String strSubBranchName;
    private String strSsbNum;
    private String strSsbName;
    private String strNetAddr;
    private String strTellerNum;

    private Integer svcStatus;
    private Integer ihdwStatus;
    private Integer iagentStatus;
    private String cashDispenserInfo;
    private String cashAcceptorInfo;
    private Integer idevType;
    private String strDevMenu;
    private Integer forVerification;
    private String aptlId;
    private String strDevType;

//    private String strpinkey;
//    private String strmackey;
//    private String struistype;
//
//    private String strDevModel;
//    private String strSsbNameDesc;
//    private String icardReaderStatus;
//    private String irfCardReaderStatus;
//    private String ipinPadStatus;
//    private String icashAcceptorStatus;
//    private String icashDispenserStatus;
//    private String ijournalPrinterStatus;
//    private String ireceiptPrinterStatus;
//    private String ipassbookPrinterStatus;
//    private String istatementPrinterStatus;
//    private String iinvoicePrinterStatus;
//    private String iotherPrinterStatus;
//    private String icashAcceptorNum;
//    private String icashDispenserNum;
//    private Integer isVerufyTerm;
//    private Integer isUpgrad;
//    private Integer usingStatus;
//    private Integer deviceType;
//    private Integer deviceClass;
//    private Integer ssbtype;
}
