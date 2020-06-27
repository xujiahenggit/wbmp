package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/22 15:00
 */
@Data
@ApiModel("流程详详细信息")
public class NewProcessInfoDto extends NewProcessDTO implements Serializable {

    @ApiModelProperty("历史记录")
    private List<NewProcessHistoryDto> listHistory;
}
