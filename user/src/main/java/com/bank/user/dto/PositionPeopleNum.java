package com.bank.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/12 10:32
 */
@Data
@ApiModel("岗位人数信息")
public class PositionPeopleNum implements Serializable {
    /**
     * 岗位名称
     */
    private String positionName;
    /**
     * 岗位人数
     */
    private String peopleNum;
}
