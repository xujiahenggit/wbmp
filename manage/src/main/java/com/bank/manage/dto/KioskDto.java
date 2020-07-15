package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * 自助设备列表查询
 */
@Data
@ApiModel
public class KioskDto {

    /*    @ApiModelProperty(value = "终端编号")
    private String terminalCode;
    @ApiModelProperty(value = "状态")
    private String status;*/

    @ApiModelProperty(value = "所属机构号")
    private String organization;

    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

//    @ApiModelProperty("排序字段")
//    private String sort;

//    @ApiModelProperty("排序方式")
//    private String order;
}
