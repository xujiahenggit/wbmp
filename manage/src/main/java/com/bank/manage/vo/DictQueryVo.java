package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/9 17:35
 */
@Data
@ApiModel("字典查询用")
public class DictQueryVo implements Serializable {
    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 字典描述
     */
    @ApiModelProperty(value = "字典描述")
    private String dictDescribe;
}
