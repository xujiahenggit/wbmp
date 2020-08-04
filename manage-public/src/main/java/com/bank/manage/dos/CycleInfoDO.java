package com.bank.manage.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CycleInfoDO implements Serializable {
    private String id;
    private String termNum;
    private Integer settleCycle;
    private Integer cdmRefillCashAmt;
    private Integer cwdCount;
    private Integer cwdAmount;
    private Integer depCount;
    private Integer depAmount;
    private LocalDateTime dtStart;
}
