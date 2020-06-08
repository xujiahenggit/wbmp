package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@ApiModel(description = "分组信息")
public class GroupDTO {

    /**
     * 分组主键
     */
    @ApiModelProperty(value = "分组主键" )
    private Integer groupId;

    /**
     * 分组名称
     */
    @ApiModelProperty(value = "分组名称" )
    private String groupName;

    /**
     * 分组类型
     */
    @ApiModelProperty(value = "分组类型" )
    private String groupType;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人" )
    private String createdUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间" )
    private LocalDateTime createdTime;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID" )
    private Integer parentId;

}
