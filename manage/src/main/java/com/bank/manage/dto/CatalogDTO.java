package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@ApiModel(description = "素材目录信息")
public class CatalogDTO implements Serializable {
    private static final long serialVersionUID = -3229633531938557387L;

    @ApiModelProperty(value = "目录主键" )
    private Integer catalogId;

    @ApiModelProperty(value = "目录名称" )
    private String catalogName;

    @ApiModelProperty(value = "目录类型" )
    private String catalogType;

    @ApiModelProperty(value = "创建人" )
    private String createdUser;

    @ApiModelProperty(value = "创建时间" )
    private LocalDate createdTime;

    @ApiModelProperty(value = "父级ID" )
    private Integer parentId;

}
