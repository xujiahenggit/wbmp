package com.bank.manage.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "活动沙龙切换模型")
public class CutActivitieSalonVo implements Serializable {

    private static final long serialVersionUID = 4293954863070290125L;

    @ApiModelProperty("设备编号")
    private String deviceNo;

    @ApiModelProperty("机构号")
    private String orgId;

    @ApiModelProperty("活动沙龙ID")
    private Integer activitieSalonId;

}
