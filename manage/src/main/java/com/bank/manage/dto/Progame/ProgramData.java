package com.bank.manage.dto.Progame;

import com.bank.manage.dto.Progame.ProgramContent;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/24 17:00
 */
@Data
public class ProgramData implements Serializable {

    private String layoutId;

    private String areaType;

    private String areaName;

    List<ProgramContent> contentList;
}
