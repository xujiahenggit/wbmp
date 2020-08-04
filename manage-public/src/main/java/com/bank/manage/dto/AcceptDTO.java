package com.bank.manage.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AcceptDTO implements Serializable {
    private String STRVMNAME;
    private Integer IHDWSTATUS;
    private String STRVMDETAILEDSTATUS;
    private String STRVMDETAILEDSTATUS2;
    private String STRVMDETAILEDSTATUS3;
    private String modelName;
    private String modelStatusDesc;
}
