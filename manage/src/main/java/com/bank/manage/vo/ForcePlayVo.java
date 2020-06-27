package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/22 17:15
 */
@ApiModel("素材审核 修改强制状态用")
@Data
public class ForcePlayVo implements Serializable {

    /**
     * 素材主键
     */
    @ApiModelProperty(value = "素材编号")
    private Integer materialId;

    /**
     * 是否强制播放 00是 01否
     */
    @ApiModelProperty(value = "是否强制播放 00是 01否")
    private String forcePlay;
}
