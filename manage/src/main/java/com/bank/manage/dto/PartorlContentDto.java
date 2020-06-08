package com.bank.manage.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/22 15:20
 */
@ApiModel("巡查内容")
@Data
public class PartorlContentDto implements Serializable {

    /**
     * 巡查记录 内容编号
     */
    private Integer partorlRecordItemId;
    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private Integer partorlContentId;

    /**
     * 巡查内容
     */
    @ApiModelProperty("巡查内容")
    private String partorlContent;

    /**
     * 巡查内容排序
     */
    @ApiModelProperty("巡查内容排序")
    private Integer partorlContentSort;

    /**
     * 巡查结果 0：是；1：否
     */
    @ApiModelProperty(value = "巡查结果 0：是；1：否")
    private String partorlResult;

    /**
     * 巡查备注
     */
    @ApiModelProperty(value = "巡查备注")
    private String partorlMark;

    /**
     * 证明文件
     */
    @ApiModelProperty(value = "巡查证明文件")
    private List<PartorlProveDto> listProve;
}
