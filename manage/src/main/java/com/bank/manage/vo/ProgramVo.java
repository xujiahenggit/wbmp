package com.bank.manage.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProgramVo implements Serializable {

    private static final long serialVersionUID = 243517891291834733L;

    private Integer programId;
    private String programName;
    private String programType;
    private String terminalNum;
    private String startTime;
    private String endTime;
    private String areaName;
    private String areaNum;
    private String playTime;
    private String sort;
    private String type;
    private String style;
    private String materialPath;
    private String materialName;
    private String mac;
    private String orgId;
    private Integer materialId;
    private String text;
    private String areaType;
    private String layoutWidth;
    private String layoutHeight;
    private String layoutBackground;




}
