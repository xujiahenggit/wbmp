package com.bank.manage.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountModuleDetailVo implements Serializable {
    private static final long serialVersionUID = 5039401241261206335L;

    private Integer moduleYear;

    private String oneModule;

    private Integer countScore;

    private String twoModule;

    private Integer twoModuleScore;

}
