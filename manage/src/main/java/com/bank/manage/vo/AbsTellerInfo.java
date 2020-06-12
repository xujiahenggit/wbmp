package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhaozhongyuan
 * @date 2020-06-11 18:39:59
 * @email
 */
@Data
@ApiModel("柜员信息表")
public class AbsTellerInfo {

    @ApiModelProperty(value = "机构号")
    private String orgId;

    @ApiModelProperty(value = "柜员号")
    private String tellerId;

    @ApiModelProperty(value = "柜员名称")
    private String tellerName;

    @ApiModelProperty(value = "柜员状态 0-离线，1-在线")
    private String tellerInd;

    @ApiModelProperty(value = "累计叫号数")
    private String callNum;

    @ApiModelProperty(value = "当日交易量")
    private String tradeVolume;

    @ApiModelProperty(value = "该网点当日交易总数")
    private String tradeVolumeSum;


    @ApiModelProperty(value = "在线时长，单位：秒")
    private Integer onLineTime;

}
