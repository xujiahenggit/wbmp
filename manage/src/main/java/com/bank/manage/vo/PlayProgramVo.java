package com.bank.manage.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlayProgramVo implements Serializable {

    private static final long serialVersionUID = -349874836889549715L;

    private String areaName;
    private String areaNum;
    private String playTime;
    private String sort;
    private String materialType;
    private String materialPath;
    private String materialId;
    private String materialName;
    private String areaType;
    private String text;

}
