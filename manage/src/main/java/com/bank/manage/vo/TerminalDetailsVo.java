package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * 终端详情
 */
@Data
@ApiModel
public class TerminalDetailsVo {
    @ApiModelProperty(value = "存款箱状态")
    private String depositStatus;
    @ApiModelProperty(value = "取款箱状态")
    private String withDrawalsStatus;
    @ApiModelProperty(value = "总读卡器状态")
    private String totalReaderStatus;
    @ApiModelProperty(value = "总打印机状态")
    private String totalPrinterStatus;
    @ApiModelProperty(value = "密码箱状态")
    private String passWordStatus;
    @ApiModelProperty(value = "'服务状态")
    private String serviceStatus;
    @ApiModelProperty(value = "'打印机状态集合")
    private List<PrinterListVo> printerListVoList;
    @ApiModelProperty(value = "'读卡器状态集合")
    private List<ReaderStatusList> readerStatusListList;
}
