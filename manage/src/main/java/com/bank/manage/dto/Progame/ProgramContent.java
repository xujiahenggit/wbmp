package com.bank.manage.dto.Progame;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/24 17:08
 */
@Data
public class ProgramContent implements Serializable {
    private String playTime;
    private Integer materialid;
    private String materialtype;
    private String materialname;
    private String path;
    private String text;
    private String sort;
}
