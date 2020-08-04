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
//    private Integer ssbType;
//    private Integer status;
//    private LocalDateTime dtStart;
//    private String Memo;
//    private String Desc;
//    private String Address;
//    private Integer atmCount;
//    private Integer cdtCount;
//    private Integer crsCount;
//    private Integer inquiryCount;
//    private Integer otherCount;

//    private String orgCode;
//    private String virtualOrg;
    /* {
            "id": 1865,
            "strSsbNum": "800700010001",
            "strSsbName": "湘江新区支行营业部自助",
            "ssbType": 2,
            "status": 7,
            "dtStart": "2018-07-20 16:50:02",
            "strMemo": "",
            "strDesc": "湘江新区支行营业部",
            "strAddress": "|开福区|湘江新区|888|",
            "atmCount": 5,
            "cdtCount": 5,
            "crsCount": 5,
            "inquiryCount": 5,
            "otherCount": 5,
            "strBankNum": "1001",
            "strBranchNum": "8007",
            "strSubBranchNum": "80070001",
            "key": "800700010001",
            "type": "SSB",
            "parentKey": "80070001",
            "title": "湘江新区支行营业部自助"
        },*/
}
