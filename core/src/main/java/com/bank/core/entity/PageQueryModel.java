package com.bank.core.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "分页查询模型")
public class PageQueryModel implements Serializable {

    private static final long serialVersionUID = 3506755076982495238L;

    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    @ApiModelProperty("排序字段")
    private String sort;

    @ApiModelProperty("排序方式")
    private String order;

    @ApiModelProperty("查询参数：默认空")
    private Map<String, Object> queryParam = new HashMap<>();
}
