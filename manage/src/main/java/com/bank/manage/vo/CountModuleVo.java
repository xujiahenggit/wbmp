package com.bank.manage.vo;

import com.bank.manage.dto.CountModuleTempDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "CountModule对象", description = "统计模块传入数据 ")
public class CountModuleVo implements Serializable {
    private static final long serialVersionUID = -2975187420686657162L;

    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    private Integer moduleYear;
    /**
     * 一级模块
     */
    @ApiModelProperty(value = "一级模块")
    private String oneModule;
    /**
     * 总分数
     */
    @ApiModelProperty(value = "总分数")
    private Integer countScore;

    @ApiModelProperty(value = "总分数")
    private List<CountModuleTempDTO> countModuleTemp;


}
