package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "Versions对象", description = "应用版本维护")
public class TellerOnlineInfo {
    @ApiModelProperty(value = "柜员号")
    private String tellerId;
    @ApiModelProperty(value = "在线时长：单位秒")
    private Integer onlineTime;
    @ApiModelProperty(value = "排名")
    private Integer score;
}
