package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * 读卡器状态集合
 */
@Data
@ApiModel
public class ReaderStatusList {
    @ApiModelProperty(value = "读卡器状态")
    private String readerStatus;
    @ApiModelProperty(value = "读卡器名称")
    private String readerName;
    @ApiModelProperty(value = "射频读卡器状态")
    private String radioFrequencyReaderStatus;
    @ApiModelProperty(value = "射频读卡器名称")
    private String radioFrequencyReaderName;
    @ApiModelProperty(value = "个性化读卡器状态")
    private String personalizedReaderStatus;
    @ApiModelProperty(value = "个性化读卡器名称")
    private String personalizedReaderName;

}
