package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/5 9:39
 */
@ApiModel("消息列表删除用")
@Data
public class CardSuppleDeleteVo implements Serializable {

    @ApiModelProperty(value = "消息编号")
    private Integer id;
    @ApiModelProperty(value = "消息类型",notes = "CARD_SUPPLE 补卡申请，WORK_SUPPLE 加班申请")
    private String type;
}
